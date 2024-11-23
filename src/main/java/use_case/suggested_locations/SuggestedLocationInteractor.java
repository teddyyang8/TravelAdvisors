package use_case.suggested_locations;

import interface_adapter.suggestlocation.SuggestedLocationsPresenter;

/**
 * The Suggested Locations Interactor.
 */
public class SuggestedLocationInteractor implements SuggestedLocationInputBoundary {

    private final SuggestedLocationOutputBoundary suggestedLocationsPresenter;

    public SuggestedLocationInteractor(SuggestedLocationOutputBoundary suggestedLocationOutputBoundary) {
        this.suggestedLocationsPresenter = suggestedLocationOutputBoundary;
    }

    @Override
    public void execute(SuggestedLocationInputData suggestedLocationInputData) {
        final SuggestedLocationOutputData suggestedLocationOutputData = new SuggestedLocationOutputData(
                suggestedLocationInputData.getPlaces()
        );
        suggestedLocationOutputBoundary.prepareSuccessView(suggestedLocationOutputData);
    }
}