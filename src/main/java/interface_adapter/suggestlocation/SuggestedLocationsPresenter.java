package interface_adapter.suggestlocation;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationViewModel;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import interface_adapter.add_to_calendar.AddToCalendarState;
import interface_adapter.add_to_calendar.AddToCalendarViewModel;
import use_case.locations.LocationsOutputData;
import use_case.suggested_locations.SuggestedLocationsOutputBoundary;
import use_case.suggested_locations.SuggestedLocationsOutputData;
import interface_adapter.selectedlocation.SelectedLocationsState;

/**
 * The presenter for the suggested locations use case.
 */
public class SuggestedLocationsPresenter implements SuggestedLocationsOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final LocationViewModel locationViewModel;
    private final SuggestedLocationsViewModel suggestedLocationsViewModel;
    private final AddToCalendarViewModel addToCalendarViewModel;

    public SuggestedLocationsPresenter(ViewManagerModel viewManagerModel,
                                       SuggestedLocationsViewModel suggestedLocationsViewModel,
                                       AddToCalendarViewModel addToCalendarViewModel,
                                       LocationViewModel locationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.suggestedLocationsViewModel = suggestedLocationsViewModel;
        this.addToCalendarViewModel = addToCalendarViewModel;
        this.locationViewModel = locationViewModel;
    }

    @Override
    public void prepareSuccessView(SuggestedLocationsOutputData response) {
        // On success, switch to the Calendar view.

        final AddToCalendarState calendarState = addToCalendarViewModel.getState();
        this.addToCalendarViewModel.setState(calendarState);
        this.addToCalendarViewModel.firePropertyChanged();

        this.viewManagerModel.setState(addToCalendarViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SuggestedLocationsState suggestedLocationState = suggestedLocationsViewModel.getState();
        suggestedLocationState.setError(error);
        suggestedLocationsViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLocationView() {
        viewManagerModel.setState(locationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
