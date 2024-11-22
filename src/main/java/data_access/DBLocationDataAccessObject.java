package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Place;
import entity.PlaceFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.suggest_locations.DataAccessException;
import use_case.suggest_locations.LocationDataAccessInterface;

/**
 * The DAO for accessing places using Google Places API.
 */
public class DBLocationDataAccessObject implements LocationDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final int CREDENTIAL_ERROR = 403;
    private static final String API_HEADER = "X-Goog-Api-Key";
    private static final String API_FIELD = "X-Goog-FieldMask";
    private static final String FIELDS = "places.displayName,places.formattedAddress";
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String MESSAGE = "message";
    private static final String API_KEY = System.getenv("API_KEY");
    private final PlaceFactory placeFactory;

    public DBLocationDataAccessObject(PlaceFactory placeFactory) {
        this.placeFactory = placeFactory;
    }

    @Override
    public List<Place> searchLocation(String address, String locationType) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put("textQuery", locationType + " near " + address);
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("https://places.googleapis.com/v1/places:searchText")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .addHeader(API_HEADER, API_KEY)
                .addHeader(API_FIELD, FIELDS)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final StringBuilder places = new StringBuilder();
                final JSONArray jsonArray = responseBody.getJSONArray("places");
                for (int i = 0; i < jsonArray.length(); i++) {
                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
                    places.append(jsonObject.getString("formattedAddress")).append(">").append(jsonObject
                            .getJSONObject("displayName").getString("text")).append("<");
                }
                final String placesString = places.toString();

                final String[] locationsList = placesString.split("<:>");
                final List<Place> suggestedPlaces = new ArrayList<>();
                for (String location : locationsList) {
                    final Place place = placeFactory.create(location.split(">")[1], location.split(">")[0]);
                    suggestedPlaces.add(place);
                }
                return suggestedPlaces;

            }
            else if (responseBody.getInt(STATUS_CODE_LABEL) == CREDENTIAL_ERROR) {
                throw new DataAccessException("Needs API Key");
            }
            else {
                throw new DataAccessException("database error: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException | DataAccessException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }
}
