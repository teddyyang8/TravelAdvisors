package app;


import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_calendar.AddToCalendarController;
import interface_adapter.add_to_calendar.AddToCalendarViewModel;
import interface_adapter.location.LocationController;
import interface_adapter.location.LocationViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsController;
import interface_adapter.suggestlocation.SuggestedLocationsPresenter;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import use_case.suggested_locations.SuggestedLocationsInteractor;
import use_case.suggested_locations.SuggestedLocationsInputBoundary;
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
     * @param calendarViewModel the LocationViewModel to inject
     * @return the LocationView created for the provided input classes.
     */
    public static SuggestedLocationsView create(
            ViewManagerModel viewManagerModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            AddToCalendarViewModel calendarViewModel) {

        final SuggestedLocationsController suggestedLocationsController = createSuggestedLocationUseCase(
                viewManagerModel,
                suggestedLocationsViewModel,
                calendarViewModel);

        return new SuggestedLocationsView(suggestedLocationsViewModel, suggestedLocationsController, calendarViewModel);
    }

    private static SuggestedLocationsController createSuggestedLocationUseCase(
            ViewManagerModel viewManagerModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            AddToCalendarViewModel calendarViewModel) {

        final SuggestedLocationsOutputBoundary suggestedLocationsOutputBoundary = new SuggestedLocationsPresenter(
                viewManagerModel, suggestedLocationsViewModel, calendarViewModel);
        final SuggestedLocationsInputBoundary suggestedLocationsInteractor = new SuggestedLocationsInteractor(
                suggestedLocationsOutputBoundary);

        return new SuggestedLocationsController(suggestedLocationsInteractor);
    }
}


