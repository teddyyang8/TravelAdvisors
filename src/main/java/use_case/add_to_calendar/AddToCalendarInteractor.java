package use_case.add_to_calendar;

import java.util.Map;

import entity.Place;
import use_case.DataAccessException;

/**
 * The Add to Calendar Interactor.
 */
public class AddToCalendarInteractor implements AddToCalendarInputBoundary {
    private final AddToCalendarDataAccessInterface calendarDataAccessObject;
    private final AddToCalendarOutputBoundary calendarPresenter;

    public AddToCalendarInteractor(AddToCalendarDataAccessInterface calendarDataAccessObject,
                                   AddToCalendarOutputBoundary calendarPresenter) {
        this.calendarDataAccessObject = calendarDataAccessObject;
        this.calendarPresenter = calendarPresenter;
    }

    @Override
    public void execute(AddToCalendarInputData addToCalendarInputData) throws DataAccessException {
        final Map<Place, String> addToCalendarPlace = addToCalendarInputData.getAddToCalendarPlace();
        for (Map.Entry<Place, String> entry : addToCalendarPlace.entrySet()) {
            final Place location = entry.getKey();
            final String time = entry.getValue();
            if (calendarDataAccessObject.hasTime(time)) {
                calendarPresenter.prepareFailView(time + " is already full.");
            }
            else {
                calendarDataAccessObject.save(location, time);
                final AddToCalendarOutputData addToCalendarOutputData = new AddToCalendarOutputData(addToCalendarPlace);
                calendarPresenter.prepareSuccessView(addToCalendarOutputData);
            }
        }
    }

    @Override
    public void switchToLocationView() {
        calendarPresenter.switchToLocationView();
    }
}
