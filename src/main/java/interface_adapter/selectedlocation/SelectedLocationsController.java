package interface_adapter.selectedlocation;


import entity.Place;
import use_case.DataAccessException;
import use_case.selected_locations.SelectedLocationsInputBoundary;
import use_case.selected_locations.SelectedLocationsInputData;

import java.util.List;

public class SelectedLocationsController {

    private final SelectedLocationsInputBoundary selectedLocationsInteractor;

    public SelectedLocationsController(SelectedLocationsInputBoundary selectedLocationsInteractor) {
        this.selectedLocationsInteractor = selectedLocationsInteractor;
    }

    public void execute(List<Place> selectedLocations) throws DataAccessException {
        final SelectedLocationsInputData selectedLocationsInputData =
                new SelectedLocationsInputData(selectedLocations);
        selectedLocationsInteractor.execute(selectedLocationsInputData);
    }

    /**
     * Switches to the location view.
     */
    public void switchToLocationView() {
        selectedLocationsInteractor.switchToLocationView();
    }

    public void switchToUserProfileView() {
        selectedLocationsInteractor.switchToProfileView();
    }
}
