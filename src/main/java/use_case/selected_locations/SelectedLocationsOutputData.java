package use_case.selected_locations;

import entity.Place;

import java.util.HashMap;

public class SelectedLocationsOutputData {

    private final HashMap<Place, String> locationCoordinatesMap;

    public SelectedLocationsOutputData(HashMap<Place, String> locationCoordinatesMap) {
        this.locationCoordinatesMap = locationCoordinatesMap;
    }

    public HashMap<Place, String> getLocationCoordinatesMap() {
        return locationCoordinatesMap;
    }

}
