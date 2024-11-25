package interface_adapter.location;

import interface_adapter.ViewManagerModel;
import interface_adapter.suggestlocation.SuggestedLocationsState;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import use_case.locations.LocationsOutputBoundary;
import use_case.locations.LocationsOutputData;

/**
 * The Presenter for the Location Use Case.
 */
public class LocationPresenter implements LocationsOutputBoundary {

    private final LocationViewModel locationViewModel;
    private final SuggestedLocationsViewModel suggestedLocationsViewModel;
    private final ViewManagerModel viewManagerModel;

    public LocationPresenter(LocationViewModel locationViewModel,
                             SuggestedLocationsViewModel suggestedLocationsViewModel,
                             ViewManagerModel viewManagerModel) {
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
}

    // commenting out bc i dont think we need it; view switches in execute (see CAUSerLogin)
//    @Override
//    public void switchToSuggestedLocationsView() {
//        viewManagerModel.setState(suggestedLocationsViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//    }
//
