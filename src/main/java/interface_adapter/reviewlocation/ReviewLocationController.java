package interface_adapter.reviewlocation;

import entity.Place;
import entity.SavedPlace;
import use_case.DataAccessException;
import use_case.review_locations.ReviewLocationsInputBoundary;
import use_case.review_locations.ReviewLocationsInputData;

import java.util.List;
import java.util.Map;

public class ReviewLocationController {

    private final ReviewLocationsInputBoundary reviewLocationsInteractor;

    public ReviewLocationController(ReviewLocationsInputBoundary reviewLocationsInteractor) {
        this.reviewLocationsInteractor = reviewLocationsInteractor;
    }

    public void execute(Map<SavedPlace, List<String>> addedReviews, String listName) throws DataAccessException {
        final ReviewLocationsInputData reviewLocationsInputData = new ReviewLocationsInputData(addedReviews, listName);
        reviewLocationsInteractor.execute(reviewLocationsInputData);
    }
}
