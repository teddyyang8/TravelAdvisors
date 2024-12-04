package use_case.selected_locations;

import entity.Place;

import java.util.HashMap;
import java.util.Map;

public class SelectedLocationsOutputData {

    private final Map<Place, String> locationCoordinatesMap;

    public SelectedLocationsOutputData(Map<Place, String> locationCoordinatesMap) {
        this.locationCoordinatesMap = locationCoordinatesMap;
    }

    public Map<Place, String> getLocationCoordinatesMap() {
        return locationCoordinatesMap;
    }

}
