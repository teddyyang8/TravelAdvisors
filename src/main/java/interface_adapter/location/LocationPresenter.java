package interface_adapter.location;

import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import use_case.suggest_locations.SuggestLocationsOutputData;

/**
 * The presenter for our Note viewing and editing program.
 */
public class LocationPresenter implements SuggestLocationsOutputBoundary {

    private final LocationViewModel locationViewModel;

    public LocationPresenter(LocationViewModel locationViewModel) {
        this.locationViewModel = locationViewModel;
    }

    /**
     * Prepares the success view for the Note related Use Cases.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(SuggestLocationsOutputData outputData) {
        locationViewModel.getState().setSuggestedLocations(outputData.getLocations());
        locationViewModel.getState().setError(null);
        locationViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        locationViewModel.getState().setError(errorMessage);
        locationViewModel.firePropertyChanged();
    }
}
