package use_case.suggested_locations;

/**
 * The Suggested Locations Interactor.
 */
public class SuggestedLocationInteractor implements SuggestedLocationInputBoundary {

    private final SuggestedLocationOutputBoundary outputBoundary;

    public SuggestedLocationInteractor(SuggestedLocationOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(SuggestedLocationInputData suggestedLocationInputData) {
        final SuggestedLocationOutputData suggestedLocationOutputData = new SuggestedLocationOutputData(
                suggestedLocationInputData.getAddress()
        );
        outputBoundary.prepareSuccessView(suggestedLocationOutputData);
    }
}