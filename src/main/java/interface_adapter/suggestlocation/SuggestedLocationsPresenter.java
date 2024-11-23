package interface_adapter.suggestlocation;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;
import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import use_case.suggest_locations.SuggestLocationsOutputData;
import use_case.suggested_locations.SuggestedLocationOutputBoundary;
import use_case.suggested_locations.SuggestedLocationOutputData;

/**
 * The presenter for the suggested locations use case.
 */
public class SuggestedLocationsPresenter implements SuggestedLocationOutputBoundary {

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

        final SuggestedLocationsState suggestedLocationsState = suggestedLocationsViewModel.getState();
        suggestedLocationsState.setSuggestedLocations(response.getLocations());
        this.suggestedLocationsViewModel.setState(suggestedLocationsState);
        this.suggestedLocationsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(suggestedLocationsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(SuggestedLocationOutputData outputData) {

    }

    @Override
    public void prepareFailView(String error) {
        final LocationState locationState = locationViewModel.getState();
        locationState.setError(error);
        locationViewModel.firePropertyChanged();
    }

}
