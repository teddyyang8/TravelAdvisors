package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Place;
import entity.PlaceFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import use_case.DataAccessException;
import use_case.locations.LocationDataAccessInterface;

/**
 * The DAO for accessing places using Google Places API.
 */
public class DBLocationDataAccessObject implements LocationDataAccessInterface {
    private static final String API_HEADER = "X-Goog-Api-Key";
    private static final String API_FIELD = "X-Goog-FieldMask";
    private static final String FIELDS = "places.displayName,places.formattedAddress";
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String API_KEY = System.getenv("API_KEY");
    private final PlaceFactory placeFactory;

    public DBLocationDataAccessObject(PlaceFactory placeFactory) {
        this.placeFactory = placeFactory;
    }

    @Override
    public List<Place> searchLocation(String address, String locationType) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        final JSONObject requestBody = new JSONObject();
        requestBody.put("textQuery", locationType + " near " + address);
        final RequestBody body = RequestBody.create(
                requestBody.toString(), MediaType.parse(CONTENT_TYPE_JSON));
        // POST METHOD
        final Request request = new Request.Builder()
                .url("https://places.googleapis.com/v1/places:searchText")
                .post(body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .addHeader(API_HEADER, API_KEY)
                .addHeader(API_FIELD, FIELDS)
                .build();
        try (Response response = client.newCall(request).execute()) {

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.has("places")) {
                final StringBuilder places = new StringBuilder();
                final JSONArray jsonArray = responseBody.getJSONArray("places");
                for (int i = 0; i < jsonArray.length(); i++) {
                    final JSONObject jsonObject =
                            jsonArray.getJSONObject(i);
                    places.append(jsonObject.getString("formattedAddress")).append(">").append(jsonObject
                            .getJSONObject("displayName").getString("text")).append("<");
                }
                final String placesString = places.toString();

                final String[] locationsList = placesString.split("<");
                final List<Place> suggestedPlaces = new ArrayList<>();
                for (String location : locationsList) {
                    final Place place = placeFactory.create(location.split(">")[1], location.split(">")[0]);
                    suggestedPlaces.add(place);
                }
                return suggestedPlaces;

            }
            else if (responseBody.has("error")) {
                throw new DataAccessException("API Error: " + responseBody.getJSONObject("error").getString("message"));
            }
            else {
                throw new DataAccessException("Unexpected API response format.");
            }
        }
        catch (IOException | JSONException | DataAccessException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

}
