package interface_adapter.location;

import java.util.List;
import java.util.stream.Collectors;

import entity.Place;
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
        final List<String> locationNames = outputData.getLocations().stream()
                .map(Place::getName)
                .collect(Collectors.toList());
        locationViewModel.updateSuggestedLocations(locationNames);
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param errorMessage the explanation of the failure
     */
    public void prepareFailView(String errorMessage) {
        locationViewModel.getState().setError(errorMessage);
        locationViewModel.firePropertyChanged();
    }
}
