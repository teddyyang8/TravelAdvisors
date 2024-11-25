package use_case.suggested_locations;

import use_case.DataAccessException;

/**
 * The Suggested Locations Interactor.
 */
public class SuggestedLocationsInteractor implements SuggestedLocationsInputBoundary {
    private final SuggestedLocationsOutputBoundary placePresenter;

    public SuggestedLocationsInteractor(SuggestedLocationsOutputBoundary suggestedLocationsOutputBoundary) {
        this.placePresenter = suggestedLocationsOutputBoundary;
    }

    @Override
    public void execute(SuggestedLocationsInputData suggestedLocationsInputData) throws DataAccessException {
        final SuggestedLocationsOutputData suggestedLocationsOutputData = new SuggestedLocationsOutputData(
                suggestedLocationsInputData.getSuggestedLocations(), false);
        placePresenter.prepareSuccessView(suggestedLocationsOutputData);
    }
}
