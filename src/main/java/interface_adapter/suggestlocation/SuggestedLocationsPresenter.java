package interface_adapter.suggestlocation;

import interface_adapter.ViewManagerModel;
import use_case.locations.LocationsOutputData;
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
        // On success, switch to the TODO: next view.

        final SuggestedLocationsState suggestedLocationsState = suggestedLocationsViewModel.getState();
        suggestedLocationsState.setSuggestedLocations(response.getSuggestedLocations());
        this.suggestedLocationsViewModel.setState(suggestedLocationsState);
        this.suggestedLocationsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(suggestedLocationsViewModel.getViewName());
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
