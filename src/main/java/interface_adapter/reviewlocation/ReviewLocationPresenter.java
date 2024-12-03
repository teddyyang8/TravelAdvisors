package interface_adapter.reviewlocation;
import entity.SavedPlace;
import interface_adapter.ViewManagerModel;
import interface_adapter.user_profile.UserProfileViewModel;
import interface_adapter.user_profile.UserProfileState;
import use_case.review_locations.ReviewLocationsOutputBoundary;
import use_case.review_locations.ReviewLocationsOutputData;

import java.util.List;
import java.util.Map;


public class ReviewLocationPresenter implements ReviewLocationsOutputBoundary {

    private final ReviewLocationViewModel reviewLocationViewModel;
    private final ViewManagerModel viewManagerModel;
    private final UserProfileViewModel userProfileViewModel;

    public ReviewLocationPresenter(ReviewLocationViewModel reviewLocationViewModel, ViewManagerModel viewManagerModel, UserProfileViewModel userProfileViewModel) {
        this.reviewLocationViewModel = reviewLocationViewModel;
        this.viewManagerModel = viewManagerModel;
        this.userProfileViewModel = userProfileViewModel;
    }

    @Override
    public void prepareSuccessView(ReviewLocationsOutputData outputData) {
        final UserProfileState userProfileState = userProfileViewModel.getState();
        final Map<String, List<SavedPlace>> savedPlaces = userProfileState.getSavedPlaces();
        savedPlaces.replace(outputData.getListName(), outputData.getSavedPlaces());
        userProfileState.setSavedPlaces(savedPlaces);
        this.userProfileViewModel.setState(userProfileState);
        this.userProfileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(userProfileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
