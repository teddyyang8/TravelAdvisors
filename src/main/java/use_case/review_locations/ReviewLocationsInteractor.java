package use_case.review_locations;

import entity.SavedPlace;
import use_case.DataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReviewLocationsInteractor implements ReviewLocationsInputBoundary {

    private final ReviewLocationsOutputBoundary reviewPresenter;

    public ReviewLocationsInteractor(ReviewLocationsOutputBoundary reviewPresenter) {
        this.reviewPresenter = reviewPresenter;
    }

    @Override
    public void execute(ReviewLocationsInputData reviewLocationsInputData) throws DataAccessException {

        final Map<SavedPlace, List<String>> addedReviews = reviewLocationsInputData.getAddedReviews();
        final String listName = reviewLocationsInputData.getListName();
        final List<SavedPlace> savedPlaceList = new ArrayList<>();
        for (Map.Entry<SavedPlace, List<String>> entry : addedReviews.entrySet()) {
            final SavedPlace savedPlace = entry.getKey();
            final List<String> reviews = entry.getValue();
            final boolean liked = reviews.get(0).equals("like");

            final SavedPlace newSavedPlace = new SavedPlace(savedPlace.getName(), savedPlace.getAddress(), reviews.get(1), liked);
            savedPlaceList.add(newSavedPlace);
        }
        final ReviewLocationsOutputData reviewLocationsOutputData = new ReviewLocationsOutputData(savedPlaceList, listName);
        reviewPresenter.prepareSuccessView(reviewLocationsOutputData);

    }
}
