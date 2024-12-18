package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_calendar.AddToCalendarPresenter;
import interface_adapter.location.LocationViewModel;
import interface_adapter.selectedlocation.SelectedLocationsPresenter;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import interface_adapter.add_to_calendar.AddToCalendarController;
import interface_adapter.add_to_calendar.AddToCalendarViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsController;
import interface_adapter.suggestlocation.SuggestedLocationsPresenter;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.add_to_calendar.AddToCalendarDataAccessInterface;
import use_case.add_to_calendar.AddToCalendarInputBoundary;
import use_case.add_to_calendar.AddToCalendarInteractor;
import use_case.add_to_calendar.AddToCalendarOutputBoundary;
import use_case.selected_locations.CoordinateDataAccessInterface;
import use_case.selected_locations.SelectedLocationsInputBoundary;
import use_case.selected_locations.SelectedLocationsInteractor;
import use_case.selected_locations.SelectedLocationsOutputBoundary;
import use_case.suggested_locations.SuggestedLocationsInteractor;
import use_case.suggested_locations.SuggestedLocationsInputBoundary;
import interface_adapter.selectedlocation.SelectedLocationsController;
import use_case.suggested_locations.SuggestedLocationsOutputBoundary;
import view.SuggestedLocationsView;

/**
 * This class contains the static factory function for creating the SuggestedLocationView.
 */
public class SuggestedLocationsUseCaseFactory {

    /** Prevent instantiation. */
    private SuggestedLocationsUseCaseFactory() {

    }

    /**
     * Factory function for creating the LocationView.
     * @param viewManagerModel the ViewManagerModel to inject
     * @param suggestedLocationsViewModel the SuggestedLocationsViewModel to inject
     * @param selectedLocationsViewModel the SelectedLocationsViewModel to inject
     * @param calendarViewModel the CalendarViewModel to inject
     * @param coordinateDataAccessObject the CoordinateDataAccessInterface to inject
     * @param calendarDataAccessObject the AddToCalendarDataAccessInterface to inject
     * @param locationViewModel the LocationViewModel to inject
     * @return the LocationView created for the provided input classes.
     */
    public static SuggestedLocationsView create(
            ViewManagerModel viewManagerModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            AddToCalendarViewModel calendarViewModel,
            SelectedLocationsViewModel selectedLocationsViewModel,
            CoordinateDataAccessInterface coordinateDataAccessObject,
            AddToCalendarDataAccessInterface calendarDataAccessObject,
            LocationViewModel locationViewModel,
            UserProfileViewModel userProfileViewModel) {

        final SelectedLocationsController selectedLocationController = createSelectedLocationUseCase(viewManagerModel,
                selectedLocationsViewModel, coordinateDataAccessObject, locationViewModel, userProfileViewModel);

        final SuggestedLocationsController suggestedLocationsController = createSuggestedLocationUseCase(
                viewManagerModel,
                suggestedLocationsViewModel,
                calendarViewModel,
                locationViewModel);

        final AddToCalendarController calendarController = createAddToCalendarUseCase(viewManagerModel,
                calendarViewModel, calendarDataAccessObject, locationViewModel);

        return new SuggestedLocationsView(suggestedLocationsViewModel, suggestedLocationsController, calendarViewModel,
                calendarController, selectedLocationsViewModel, selectedLocationController);
    }

    private static SuggestedLocationsController createSuggestedLocationUseCase(
            ViewManagerModel viewManagerModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            AddToCalendarViewModel calendarViewModel,
            LocationViewModel locationViewModel) {

        final SuggestedLocationsOutputBoundary suggestedLocationsOutputBoundary = new SuggestedLocationsPresenter(
                viewManagerModel, suggestedLocationsViewModel, calendarViewModel, locationViewModel);

        final SuggestedLocationsInputBoundary suggestedLocationsInteractor = new SuggestedLocationsInteractor(
                suggestedLocationsOutputBoundary);

        return new SuggestedLocationsController(suggestedLocationsInteractor);
    }

    private static SelectedLocationsController createSelectedLocationUseCase(
            ViewManagerModel viewManagerModel,
            SelectedLocationsViewModel selectedLocationsViewModel,
            CoordinateDataAccessInterface coordinatesDataAccessObject,
            LocationViewModel locationViewModel, UserProfileViewModel userProfileViewModel) {
        final SelectedLocationsOutputBoundary selectedLocationsOutputBoundary = new SelectedLocationsPresenter(
                selectedLocationsViewModel, viewManagerModel, locationViewModel, userProfileViewModel);
        final SelectedLocationsInputBoundary selectedLocationsInteractor = new SelectedLocationsInteractor(
                selectedLocationsOutputBoundary, coordinatesDataAccessObject);

        return new SelectedLocationsController(selectedLocationsInteractor);
    }

    private static AddToCalendarController createAddToCalendarUseCase(
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


