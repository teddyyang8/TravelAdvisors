package use_case.review_locations;

import entity.SavedPlace;

import java.util.List;

public class ReviewLocationsInputData {
    private final List<SavedPlace> placeList;

    public ReviewLocationsInputData(List<SavedPlace> placeList) {
        this.placeList = placeList;
    }

    public List<SavedPlace> getPlaceList() {
        return placeList;
    }
}
