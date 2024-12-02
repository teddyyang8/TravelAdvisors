package data_access;

import java.io.IOException;

import entity.Place;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import use_case.DataAccessException;
import use_case.selected_locations.CoordinateDataAccessInterface;

/**
 * The DAO for accessing places using Google Places API.
 */
public class DBCoordinatesDataAccessObject implements CoordinateDataAccessInterface {
    private static final String API_KEY = System.getenv("API_KEY");
    private static final String CONTENT_TYPE_JSON = "application/json";

    @Override
    public String searchCoordinates(Place place) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        HttpUrl url = HttpUrl.parse("https://maps.googleapis.com/maps/api/geocode/json").newBuilder()
                .addQueryParameter("key", API_KEY)
                .addQueryParameter("address", place.getAddress())
                .build();
        // POST METHOD
        final Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.has("results")) {
                final StringBuilder coordinates = new StringBuilder();
                final JSONArray jsonArray = responseBody.getJSONArray("results");
                final JSONObject firstResult = jsonArray.getJSONObject(0);
                final JSONObject geometry = firstResult.getJSONObject("geometry");
                final JSONObject location = geometry.getJSONObject("location");

                final double lat = location.getDouble("lat");
                final double lng = location.getDouble("lng");

                coordinates.append(lat).append(",").append(lng);
                return coordinates.toString();
            }
            else if (responseBody.has("error_message")) {
                throw new DataAccessException("API Error: " + responseBody.getJSONObject("error_message").getString("message"));
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
