package use_case.review_locations;

import entity.SavedPlace;

import java.util.List;

public class ReviewLocationsOutputData {

    private final List<SavedPlace> savedPlaces;
    private final String listName;

    public ReviewLocationsOutputData(List<SavedPlace> savedPlaces, String listName) {
        this.savedPlaces = savedPlaces;
        this.listName = listName;
    }

    public List<SavedPlace> getSavedPlaces() {
        return savedPlaces;
    }

    public String getListName() {
        return listName;
    }

}
