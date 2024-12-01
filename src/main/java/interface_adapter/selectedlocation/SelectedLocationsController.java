package interface_adapter.selectedlocation;


import entity.Place;
import use_case.DataAccessException;
import use_case.selected_locations.SelectedLocationsInputBoundary;

public class SelectedLocationsController {

    private final SelectedLocationsInputBoundary selectedLocationsInteractor;

    public SelectedLocationsController(SelectedLocationsInputBoundary selectedLocationsInteractor) {
        this.selectedLocationsInteractor = selectedLocationsInteractor;
    }

    public void execute() throws DataAccessException {
        final SelectedLocationsInputData selectedLocationsInputData = new SelectedLocationsInputData();
    }

}
