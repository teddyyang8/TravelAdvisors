package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_calendar.AddToCalendarController;
import interface_adapter.add_to_calendar.AddToCalendarPresenter;
import interface_adapter.add_to_calendar.AddToCalendarViewModel;
import interface_adapter.location.LocationViewModel;
import use_case.add_to_calendar.AddToCalendarDataAccessInterface;
import use_case.add_to_calendar.AddToCalendarInputBoundary;
import use_case.add_to_calendar.AddToCalendarInteractor;
import use_case.add_to_calendar.AddToCalendarOutputBoundary;
import view.CalendarView;

/**
 * This class contains the static factory function for creating the CalendarView.
 */
public class CalendarUseCaseFactory {

    /** Prevent instantiation. */
    private CalendarUseCaseFactory() {

    }

    /**
     * Factory function for creating the CalendarView.
     * @param viewManagerModel the ViewManagerModel to inject
     * @param calendarViewModel the AddToCalendarViewModel to inject
     * @param calendarDataAccessObject the AddToCalendarDataAccessObject to inject
     * @param locationViewModel the LocationViewModel to inject
     * @return the LocationView created for the provided input classes.
     */
    public static CalendarView create(ViewManagerModel viewManagerModel,
                                      AddToCalendarViewModel calendarViewModel,
                                      AddToCalendarDataAccessInterface calendarDataAccessObject,
                                      LocationViewModel locationViewModel) {
        final AddToCalendarController calendarController = createCalendarUseCase(viewManagerModel,
                calendarViewModel, calendarDataAccessObject, locationViewModel);

        return new CalendarView(calendarViewModel, calendarController);
    }

    private static AddToCalendarController createCalendarUseCase(
            ViewManagerModel viewManagerModel,
            AddToCalendarViewModel calendarViewModel,
            AddToCalendarDataAccessInterface calendarDataAccessObject,
            LocationViewModel locationViewModel) {
        final AddToCalendarOutputBoundary calendarOutputBoundary = new AddToCalendarPresenter(calendarViewModel,
                viewManagerModel, locationViewModel);
        final AddToCalendarInputBoundary calendarInteractor = new AddToCalendarInteractor(calendarDataAccessObject,
                calendarOutputBoundary);

        return new AddToCalendarController(calendarInteractor);
    }

}
