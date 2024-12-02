package interface_adapter.reviewlocation;

import interface_adapter.ViewManagerModel;
import use_case.review_locations.ReviewLocationsOutputBoundary;
import use_case.review_locations.ReviewLocationsOutputData;

public class ReviewLocationPresenter implements ReviewLocationsOutputBoundary {

    private final ReviewLocationViewModel reviewLocationViewModel;
    private final ViewManagerModel viewManagerModel;

    public ReviewLocationPresenter(ReviewLocationViewModel reviewLocationViewModel, ViewManagerModel viewManagerModel) {
        this.reviewLocationViewModel = reviewLocationViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ReviewLocationsOutputData outputData) {
        final ReviewLocationState reviewLocationState = reviewLocationViewModel.getState();
        reviewLocationState.setReviewMap(outputData);
        this.reviewLocationViewModel.setState(reviewLocationState);
        this.reviewLocationViewModel.firePropertyChanged();

        this.viewManagerModel.setState(reviewLocationViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
