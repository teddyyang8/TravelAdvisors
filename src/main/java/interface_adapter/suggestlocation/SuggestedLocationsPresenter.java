package interface_adapter.suggestlocation;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_calendar.AddToCalendarState;
import interface_adapter.add_to_calendar.AddToCalendarViewModel;
import use_case.locations.LocationsOutputData;
import use_case.suggested_locations.SuggestedLocationsOutputBoundary;
import use_case.suggested_locations.SuggestedLocationsOutputData;

/**
 * The presenter for the suggested locations use case.
 */
public class SuggestedLocationsPresenter implements SuggestedLocationsOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final SuggestedLocationsViewModel suggestedLocationsViewModel;
    private final AddToCalendarViewModel addToCalendarViewModel;

    public SuggestedLocationsPresenter(ViewManagerModel viewManagerModel,
                                       SuggestedLocationsViewModel suggestedLocationsViewModel,
                                       AddToCalendarViewModel addToCalendarViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.suggestedLocationsViewModel = suggestedLocationsViewModel;
        this.addToCalendarViewModel = addToCalendarViewModel;
    }

    @Override
    public void prepareSuccessView(SuggestedLocationsOutputData response) {
        // On success, switch to the Calendar view.
        // TODO: Need to implement with Sean's user case
        final AddToCalendarState calendarState = addToCalendarViewModel.getState();
        calendarState.setCalendarItems(response.getCalendarItems());
        this.addToCalendarViewModel.setState(calendarState);
        this.addToCalendarViewModel.firePropertyChanged();

        this.viewManagerModel.setState(addToCalendarViewModel.getViewName());
        // On success, switch to the selected locations view.

//        final SelectedLocationState selectedLocationState = selectedLocationViewModel.getState();
//        selectedLocationState.setSelectedLocations(response.getSelectedLocations());
//        this.selectedLocationViewModel.setState(selectedLocationState);
//        this.selectedLocationViewModel.firePropertyChanged();
//
//        this.viewManagerModel.setState(selectedLocationViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SuggestedLocationsState suggestedLocationState = suggestedLocationsViewModel.getState();
        suggestedLocationState.setError(error);
        suggestedLocationsViewModel.firePropertyChanged();
    }
}
