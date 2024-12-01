package interface_adapter.add_to_calendar;

import interface_adapter.ViewModel;

/**
 * The View Model for the Add to Calendar View.
 */
public class AddToCalendarViewModel extends ViewModel<AddToCalendarState> {

    public AddToCalendarViewModel() {
        super("Calendar");
        setState(new AddToCalendarState());
    }
}
