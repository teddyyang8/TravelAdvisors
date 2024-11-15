package interface_adapter.location;

import java.util.ArrayList;
import java.util.List;

import entity.Place;
import use_case.suggest_locations.SuggestLocationsInputData;

/**
 * The state representing location-related data, including city, address, keywords,
 * suggested locations, and any error messages.
 */
public class LocationState {
    private String address = "";
    private String locationType = "";
    private String error;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

