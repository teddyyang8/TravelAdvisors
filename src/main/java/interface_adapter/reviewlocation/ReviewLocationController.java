package interface_adapter.reviewlocation.;


import entity.Place;
import entity.SavedPlace;
import use_case.DataAccessException;
import use_case.review_locations.ReviewLocationsInputBoundary;
import use_case.review_locations.ReviewLocationsInputData;

import java.util.List;
import java.util.Map;

public class ReviewLocationController {

    private final ReviewLocationsInputBoundary reviewLocationsInteractor;

    public ReviewLocationsController(ReviewLocationsInputBoundary reviewLocationsInteractor) {
        this.reviewLocationsInteractor = reviewLocationsInteractor;
    }

    public void execute(Map<Place, List<String>> reviewMap) throws DataAccessException {

    }

}