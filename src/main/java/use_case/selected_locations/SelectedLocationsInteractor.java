package use_case.selected_locations;

import entity.Place;
import use_case.DataAccessException;
import use_case.locations.CoordinateDataAccessInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        final List<Place> selectedLocations = selectedLocationsInputData.getSelectedLocations();
        final Map<Place, String> locationCoordinatesMap = new HashMap<>();

        for (Place location : selectedLocations) {
            try {
                final String coordinates = coordinateDataAccessInterface.searchCoordinates(location);
                locationCoordinatesMap.put(location, coordinates);
            }
            catch (DataAccessException e) {
                selectedLocationsPresenter.prepareFailView("Failed to fetch coordinates for: " + location.getName());
            }
        }

        final SelectedLocationsOutputData selectedLocationsOutputData =
                new SelectedLocationsOutputData(locationCoordinatesMap);
        selectedLocationsPresenter.prepareSuccessView(selectedLocationsOutputData);
    }

    @Override
    public void switchToLocationView() {
        selectedLocationsPresenter.switchToLocationView();
    }
}
