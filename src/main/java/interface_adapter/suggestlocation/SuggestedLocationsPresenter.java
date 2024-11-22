package interface_adapter.suggestlocation;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;
import use_case.suggest_locations.LocationsOutputBoundary;
import use_case.suggest_locations.LocationsOutputData;

/**
 * The presenter for the suggested locations use case.
 */
public class SuggestedLocationsPresenter implements LocationsOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SuggestedLocationsViewModel suggestedLocationsViewModel;
    private final LocationViewModel locationViewModel;

    public SuggestedLocationsPresenter(ViewManagerModel viewManagerModel,
                                       SuggestedLocationsViewModel suggestedLocationsViewModel,
                                       LocationViewModel locationsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.suggestedLocationsViewModel = suggestedLocationsViewModel;
        this.locationViewModel = locationsViewModel;
    }

    @Override
    public void prepareSuccessView(LocationsOutputData response) {
        // On success, switch to the logged in view.

        final SuggestedLocationsState suggestedLocationsState = suggestedLocationsViewModel.getState();
        suggestedLocationsState.setSuggestedLocations(response.getLocations());
        this.suggestedLocationsViewModel.setState(suggestedLocationsState);
        this.suggestedLocationsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(suggestedLocationsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LocationState locationState = locationViewModel.getState();
        locationState.setError(error);
        locationViewModel.firePropertyChanged();
    }

    @Override
    public void switchToSuggestedLocationsView() {

    }

}
