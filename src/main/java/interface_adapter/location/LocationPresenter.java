package interface_adapter.location;

import interface_adapter.ViewManagerModel;
import interface_adapter.suggestlocation.SuggestedLocationsState;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import use_case.suggest_locations.LocationsOutputBoundary;
import use_case.suggest_locations.LocationsOutputData;

/**
 * The presenter for our Note viewing and editing program.
 */
public class LocationPresenter implements LocationsOutputBoundary {

    private final LocationViewModel locationViewModel;
    private final SuggestedLocationsViewModel suggestedLocationsViewModel;
    private final ViewManagerModel viewManagerModel;

    public LocationPresenter(LocationViewModel locationViewModel, SuggestedLocationsViewModel suggestedLocationsViewModel, ViewManagerModel viewManagerModel) {
        this.locationViewModel = locationViewModel;
        this.suggestedLocationsViewModel = suggestedLocationsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LocationsOutputData response) {
        // On success, switch to the suggested locations view.
        final SuggestedLocationsState suggestedLocationsState = suggestedLocationsViewModel.getState();
        suggestedLocationsState.setSuggestedLocations(response.getLocations());
        this.suggestedLocationsViewModel.setState(suggestedLocationsState);
        suggestedLocationsViewModel.firePropertyChanged();

        viewManagerModel.setState(suggestedLocationsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        throw new LocationSearchFailed(error);
    }

    @Override
    public void switchToSuggestedLocationsView() {
        viewManagerModel.setState(suggestedLocationsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
