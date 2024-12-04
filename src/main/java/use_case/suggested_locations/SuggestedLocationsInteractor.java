package use_case.suggested_locations;

import use_case.DataAccessException;
import use_case.selected_locations.SelectedLocationsInputData;

/**
 * The Suggested Locations Interactor.
 */
public class SuggestedLocationsInteractor implements SuggestedLocationsInputBoundary {
    private final SuggestedLocationsOutputBoundary placePresenter;

    public SuggestedLocationsInteractor(SuggestedLocationsOutputBoundary suggestedLocationsOutputBoundary) {
        this.placePresenter = suggestedLocationsOutputBoundary;
    }

    @Override
    public void execute(SuggestedLocationsInputData suggestedLocationsInputData) {
//        final SuggestedLocationsOutputData suggestedLocationsOutputData = new SuggestedLocationsOutputData(
//                suggestedLocationsInputData.getSuggestedLocations(), false);
//        placePresenter.prepareSuccessView(suggestedLocationsOutputData)
        System.out.println("got here somehow");
    }

    @Override
    public void switchToLocationView() {
        placePresenter.switchToLocationView();
    }
}
