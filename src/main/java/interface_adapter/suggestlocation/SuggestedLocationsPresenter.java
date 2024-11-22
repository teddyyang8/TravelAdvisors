package interface_adapter.suggestlocation;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;
import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import use_case.suggest_locations.SuggestLocationsOutputData;

/**
 * The presenter for the suggested locations use case.
 */
public class SuggestedLocationsPresenter implements SuggestLocationsOutputBoundary {

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
    public void prepareSuccessView(SuggestLocationsOutputData response) {
        // On success, switch to the logged in view.

        final SuggestedLocationsState loggedInState = suggestedLocationsViewModel.getState();
        loggedInState.setSuggestedLocations(response.getLocations());
        this.suggestedLocationsViewModel.setState(loggedInState);
        this.suggestedLocationsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(suggestedLocationsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LocationState loginState = locationViewModel.getState();
        loginState.setError(error);
        locationViewModel.firePropertyChanged();
    }

}
