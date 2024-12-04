package use_case.add_to_calendar;

/**
 * The output boundary for the Add to Calendar Use Case.
 */
public interface AddToCalendarOutputBoundary {

    /**
     * Prepares the success view for the AddToCalendar Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(AddToCalendarOutputData outputData);

    /**
     * Prepares the failure view for the AddToCalendar Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Execute the switch to location view.
     */
    void switchToLocationView();
}
