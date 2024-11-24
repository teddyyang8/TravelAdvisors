package use_case.locations;


import use_case.DataAccessException;

/**
 * The Suggest Locations Interactor.
 */
public class LocationsInteractor implements LocationsInputBoundary {
    private final LocationDataAccessInterface placeDataAccessObject;
    private final LocationsOutputBoundary placePresenter;

    public LocationsInteractor(LocationDataAccessInterface suggestLocationsPlaceDataAccessInterface,
                               LocationsOutputBoundary locationsOutputBoundary) {
        this.placeDataAccessObject = suggestLocationsPlaceDataAccessInterface;
        this.placePresenter = locationsOutputBoundary;
    }

    @Override
    public void execute(LocationsInputData locationsInputData) throws DataAccessException {
        final LocationsOutputData locationsOutputData = new LocationsOutputData(placeDataAccessObject.searchLocation(
                locationsInputData.getAddress(),
                locationsInputData.getLocationType()),
                false);
        placePresenter.prepareSuccessView(locationsOutputData);
    }
}
