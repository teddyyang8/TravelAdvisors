package use_case.suggest_locations;

/**
 * The Suggest Locations Interactor.
 */
public class SuggestLocationsInteractor implements SuggestLocationsInputBoundary {
    private final LocationDataAccessInterface placeDataAccessObject;
    private final SuggestLocationsOutputBoundary placePresenter;

    public SuggestLocationsInteractor(LocationDataAccessInterface suggestLocationsPlaceDataAccessInterface,
                                      SuggestLocationsOutputBoundary suggestLocationsOutputBoundary) {
        this.placeDataAccessObject = suggestLocationsPlaceDataAccessInterface;
        this.placePresenter = suggestLocationsOutputBoundary;
    }

    @Override
    public void execute(SuggestLocationsInputData suggestLocationsInputData) {
        final String suggestedLocations = placeDataAccessObject.searchLocation(suggestLocationsInputData.getAddress(),
                suggestLocationsInputData.getLocationType());

        final SuggestLocationsOutputData suggestLocationsOutputData = new SuggestLocationsOutputData(suggestedLocations,
                false);
        placePresenter.prepareSuccessView(suggestLocationsOutputData);
    }
}
