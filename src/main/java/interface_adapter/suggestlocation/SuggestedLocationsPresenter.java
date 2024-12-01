package interface_adapter.suggestlocation;

import interface_adapter.ViewManagerModel;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import use_case.locations.LocationsOutputData;
import use_case.suggested_locations.SuggestedLocationsOutputBoundary;
import use_case.suggested_locations.SuggestedLocationsOutputData;
import interface_adapter.selectedlocation.SelectedLocationsState;

/**
 * The presenter for the suggested locations use case.
 */
public class SuggestedLocationsPresenter implements SuggestedLocationsOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SuggestedLocationsViewModel suggestedLocationsViewModel;
    private final SelectedLocationsViewModel selectedLocationViewModel;

    public SuggestedLocationsPresenter(ViewManagerModel viewManagerModel,
                                       SuggestedLocationsViewModel suggestedLocationsViewModel,
                                       SelectedLocationsViewModel selectedLocationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.suggestedLocationsViewModel = suggestedLocationsViewModel;
        this.selectedLocationViewModel = selectedLocationViewModel;
    }

    @Override
    public void prepareSuccessView(SuggestedLocationsOutputData response) {
        // On success, switch to the selected locations view.

        final SelectedLocationsState selectedLocationState =
                selectedLocationViewModel.getState();
        selectedLocationState.setSelectedLocations(response.getSelectedLocations());
        this.selectedLocationViewModel.setState(selectedLocationState);
        this.selectedLocationViewModel.firePropertyChanged();

        this.viewManagerModel.setState(selectedLocationViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SuggestedLocationsState suggestedLocationState = suggestedLocationsViewModel.getState();
        suggestedLocationState.setError(error);
        suggestedLocationsViewModel.firePropertyChanged();
    }
}
