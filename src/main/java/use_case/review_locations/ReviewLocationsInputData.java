package use_case.review_locations;

import entity.Place;
import entity.SavedPlace;

import java.util.List;
import java.util.Map;

public class ReviewLocationsInputData {

    private final Map<SavedPlace, List<String>> addedReviews;
    private final String listName;

    public ReviewLocationsInputData(Map<SavedPlace, List<String>> addedReviews, String listName) {
        this.addedReviews = addedReviews;
        this.listName = listName;
    }

    public Map<SavedPlace, List<String>> getAddedReviews() {
        return addedReviews;
    }

    public String getListName() {
        return listName;
    }
}
