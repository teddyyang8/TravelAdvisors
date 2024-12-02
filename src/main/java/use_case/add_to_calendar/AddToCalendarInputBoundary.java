package use_case.add_to_calendar;

import use_case.DataAccessException;

/**
* The Add to Calendar Use Case.
*/
public interface AddToCalendarInputBoundary {

    /**
     * Execute the Add To Calendar Use Case.
     *
     * @param addToCalendarInputData the input data for this use case
     * @throws DataAccessException if data cannot be accessed at any time
     */
    void execute(AddToCalendarInputData addToCalendarInputData) throws DataAccessException;

    /**
     * Execute the switch to location view.
     */
    void switchToLocationView();

}
