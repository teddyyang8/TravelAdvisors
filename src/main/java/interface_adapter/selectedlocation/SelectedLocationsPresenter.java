package interface_adapter.selectedlocation;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsState;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.selected_locations.SelectedLocationsOutputBoundary;
import use_case.selected_locations.SelectedLocationsOutputData;

public class SelectedLocationsPresenter implements SelectedLocationsOutputBoundary {
    private final SelectedLocationsViewModel selectedLocationsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LocationViewModel locationViewModel;
    private final UserProfileViewModel userProfileViewModel;

    public SelectedLocationsPresenter(SelectedLocationsViewModel selectedLocationsViewModel,
                                      ViewManagerModel viewManagerModel,
                                      LocationViewModel locationViewModel,
                                      UserProfileViewModel userProfileViewModel) {
        this.selectedLocationsViewModel = selectedLocationsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.locationViewModel = locationViewModel;
        this.userProfileViewModel = userProfileViewModel;
    }

    @Override
    public void prepareSuccessView(SelectedLocationsOutputData outputData) {
        final SelectedLocationsState selectedLocationsState =
                selectedLocationsViewModel.getState();
        selectedLocationsState.setPlaceToCoordinates(outputData.getLocationCoordinatesMap());
        this.selectedLocationsViewModel.setState(selectedLocationsState);
        this.selectedLocationsViewModel.firePropertyChanged();
        this.viewManagerModel.setState(selectedLocationsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToLocationView() {
        viewManagerModel.setState(locationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToProfileView() {
        viewManagerModel.setState(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
