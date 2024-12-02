package use_case.review_locations;

import entity.SavedPlace;

import java.util.List;

public class ReviewLocationsInteractor implements ReviewLocationsInputBoundary {
    private final ReviewLocationsOutputBoundary reviewPresenter;

    public ReviewLocationsInteractor(ReviewLocationsOutputBoundary reviewPresenter) {
        this.reviewPresenter = reviewPresenter;
    }

    @Override
    public void execute(ReviewLocationsInputData inputData) {
        final List<SavedPlace> placeList = inputData.getPlaceList();
        final ReviewLocationsOutputData outputData = new ReviewLocationsOutputData(placeList);
        reviewPresenter.prepareSuccessView(outputData);
    }
}
