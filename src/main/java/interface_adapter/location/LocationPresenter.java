package interface_adapter.location;

import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import use_case.suggest_locations.SuggestLocationsOutputData;
import view.LocationView;

/**
 * The presenter for our Note viewing and editing program.
 */
public class LocationPresenter implements SuggestLocationsOutputBoundary {

    private LocationView view;
    private LocationViewModel viewModel;

    public LocationPresenter(LocationViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setView(LocationView view) {
        this.view = view;
    }

    @Override
    public void prepareSuccessView(SuggestLocationsOutputData response) {

    }

    @Override
    public void prepareFailView(String error) {
        throw new LocationSearchFailed(error);
    }
}
