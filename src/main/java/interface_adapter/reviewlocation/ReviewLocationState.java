package interface_adapter.reviewlocation;

import entity.Place;
import entity.SavedPlace;

import java.util.List;
import java.util.Map;

public class ReviewLocationState {
    private List<SavedPlace> savedPlaces;
    private String listName;
    private Map<SavedPlace, List<String>> addedReviews;

    public Map<SavedPlace, List<String>> getAddedReviews() {
        return addedReviews;
    }

    public void setAddedReviews(Map<SavedPlace, List<String>> addedReviews) {
        this.addedReviews = addedReviews;
    }

    public List<SavedPlace> getSavedPlaces() {
        return savedPlaces;
    }

    public void setSavedPlaces(List<SavedPlace> savedPlaces) {
        this.savedPlaces = savedPlaces;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
