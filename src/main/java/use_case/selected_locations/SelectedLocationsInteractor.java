package use_case.selected_locations;

import use_case.DataAccessException;

public class SelectedLocationsInteractor implements SelectedLocationsInputBoundary {
    private final CoordinateDataAccessInterface coordinateDataAccessInterface;
    private final SelectedLocationsOutputBoundary selectedLocationsPresenter;

    public SelectedLocationsInteractor(SelectedLocationsOutputBoundary selectedLocationsOutputBoundary,
                                        CoordinateDataAccessInterface coordinateDataAccessInterface) {
        this.selectedLocationsPresenter = selectedLocationsOutputBoundary;
        this.coordinateDataAccessInterface = coordinateDataAccessInterface;
     }


    @Override
    public void execute(SelectedLocationsInputData selectedLocationsInputData) throws DataAccessException {
        final SelectedLocationsOutputData selectedLocationsOutputData =
                new SelectedLocationsOutputData(coordinateDataAccessInterface.getcoordinate(),
                selectedLocationsInputData.getSelectedLocations(),
                false);
        selectedLocationsPresenter.prepareSuccessView(selectedLocationsOutputData);
    }
}
}
