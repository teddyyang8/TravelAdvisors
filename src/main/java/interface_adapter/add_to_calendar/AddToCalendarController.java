package interface_adapter.add_to_calendar;

import java.util.Map;

import entity.Place;
import use_case.DataAccessException;
import use_case.add_to_calendar.AddToCalendarInputBoundary;
import use_case.add_to_calendar.AddToCalendarInputData;

/**
 * The AddToCalendarController class handles user input related to adding locations to calendar.
 * It interacts with the AddToCalendarInputBoundary to process the input and perform actions.
 */
public class AddToCalendarController {

    private final AddToCalendarInputBoundary addToCalendarInteractor;

    public AddToCalendarController(AddToCalendarInputBoundary calendarInteractor) {
        this.addToCalendarInteractor = calendarInteractor;
    }

    /**
     * Executes the show calendar.
     * @param addToCalendarData the locations and time map to add.
     * @throws DataAccessException if data cannot be accessed
     */
    public void execute(Map<Place, String> addToCalendarData) throws DataAccessException {
        final AddToCalendarInputData calendarInputData = new AddToCalendarInputData(addToCalendarData);

        addToCalendarInteractor.execute(calendarInputData);
    }

    public void switchToLocationView() {
        addToCalendarInteractor.switchToLocationView();
    }
}
