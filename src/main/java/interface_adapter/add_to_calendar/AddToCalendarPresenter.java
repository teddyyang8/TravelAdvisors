package interface_adapter.add_to_calendar;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationViewModel;
import use_case.add_to_calendar.AddToCalendarOutputBoundary;
import use_case.add_to_calendar.AddToCalendarOutputData;

/**
 * The Presenter for the Add to Calendar Use Case.
 */
public class AddToCalendarPresenter implements AddToCalendarOutputBoundary {

    private final AddToCalendarViewModel calendarViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LocationViewModel locationViewModel;

    public AddToCalendarPresenter(AddToCalendarViewModel calendarViewModel, ViewManagerModel viewManagerModel,
                                  LocationViewModel locationViewModel) {
        this.calendarViewModel = calendarViewModel;
        this.viewManagerModel = viewManagerModel;
        this.locationViewModel = locationViewModel;
    }


    @Override
    public void prepareSuccessView(AddToCalendarOutputData outputData) {
        // On success, switch to the Calendar view.
        final AddToCalendarState calendarState = calendarViewModel.getState();
        calendarState.setCalendarItems(outputData.getcalendarItems());
        this.calendarViewModel.setState(calendarState);
        this.calendarViewModel.firePropertyChanged();

        this.viewManagerModel.setState(calendarViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final AddToCalendarState calendarState = calendarViewModel.getState();
        calendarState.setAddError(errorMessage);
        calendarViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLocationView() {
        viewManagerModel.setState(locationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
