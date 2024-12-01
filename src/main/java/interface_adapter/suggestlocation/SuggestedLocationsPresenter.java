package interface_adapter.suggestlocation;

import
import interface_adapter.ViewManagerModel;
import interface_adapter.selectedlocation.SelectedLocationsState;
import use_case.suggested_locations.SuggestedLocationsOutputBoundary;
import use_case.suggested_locations.SuggestedLocationsOutputData;

/**
 * The presenter for the suggested locations use case.
 */
public class SuggestedLocationsPresenter implements SuggestedLocationsOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SuggestedLocationsViewModel suggestedLocationsViewModel;

    public SuggestedLocationsPresenter(ViewManagerModel viewManagerModel,
                                       SuggestedLocationsViewModel suggestedLocationsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.suggestedLocationsViewModel = suggestedLocationsViewModel;
    }

    @Override
    public void prepareSuccessView(SuggestedLocationsOutputData response) {
        // On success, switch to the suggested locations view.
        final SelectedLocationsState selectedLocationsState = selectedLocationsViewModel.getState();
        selectedLocationsState.setSelectedLocations(response.getLocations());
        this.selectedLocationsViewModel.setState(selectedLocationsState);
        this.viewManagerModel.firePropertyChanged();

        this.viewManagerModel.setState(selectedLocationsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SuggestedLocationsState suggestedLocationState = suggestedLocationsViewModel.getState();
        suggestedLocationState.setError(error);
        suggestedLocationsViewModel.firePropertyChanged();
    }
//
//    // change to switch to next view
//    @Override
//    public void switchToSuggestedLocationsView() {
//
//  }

}
