package app;

import data_access.DBCoordinatesDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_calendar.AddToCalendarViewModel;
import interface_adapter.selectedlocation.SelectedLocationsController;
import interface_adapter.selectedlocation.SelectedLocationsPresenter;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsController;
import interface_adapter.suggestlocation.SuggestedLocationsPresenter;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import use_case.selected_locations.SelectedLocationsInputBoundary;
import use_case.selected_locations.SelectedLocationsInteractor;
import use_case.selected_locations.SelectedLocationsOutputBoundary;
import use_case.suggested_locations.SuggestedLocationsInputBoundary;
import use_case.suggested_locations.SuggestedLocationsInteractor;
import use_case.suggested_locations.SuggestedLocationsOutputBoundary;
import view.SuggestedLocationsView;

/**
 * This class contains the static factory function for creating the SuggestedLocationView.
 */
public final class SuggestedLocationsUseCaseFactory {

    /** Prevent instantiation. */
    private SuggestedLocationsUseCaseFactory() {

    }

    /**
     * Factory function for creating the LocationView.
     * @param viewManagerModel the ViewManagerModel to inject
     * @param suggestedLocationsViewModel the SuggestedLocationsViewModel to inject
     * @param selectedLocationsViewModel the SelectedLocationsViewModel to inject
     * @param calendarViewModel the LocationViewModel to inject
     * @return the LocationView created for the provided input classes.
     */
    public static SuggestedLocationsView create(
            ViewManagerModel viewManagerModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            AddToCalendarViewModel calendarViewModel,
            SelectedLocationsViewModel selectedLocationsViewModel) {

        final SelectedLocationsController selectedLocationController = createSelectedLocationUseCase(viewManagerModel,
                selectedLocationsViewModel, new DBCoordinatesDataAccessObject());

        final SuggestedLocationsController suggestedLocationsController = createSuggestedLocationUseCase(
                viewManagerModel,
                suggestedLocationsViewModel,
                calendarViewModel, selectedLocationsViewModel);

        return new SuggestedLocationsView(suggestedLocationsViewModel, suggestedLocationsController,
                calendarViewModel, selectedLocationsViewModel, selectedLocationController);
    }

    private static SuggestedLocationsController createSuggestedLocationUseCase(
            ViewManagerModel viewManagerModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            AddToCalendarViewModel calendarViewModel,
            SelectedLocationsViewModel selectedLocationsViewModel) {

        final SuggestedLocationsOutputBoundary suggestedLocationsOutputBoundary = new SuggestedLocationsPresenter(
                viewManagerModel, suggestedLocationsViewModel, calendarViewModel, selectedLocationsViewModel);

        final SuggestedLocationsInputBoundary suggestedLocationsInteractor = new SuggestedLocationsInteractor(
                suggestedLocationsOutputBoundary);

        return new SuggestedLocationsController(suggestedLocationsInteractor);
    }

    private static SelectedLocationsController createSelectedLocationUseCase(
            ViewManagerModel viewManagerModel,
            SelectedLocationsViewModel selectedLocationsViewModel,
            DBCoordinatesDataAccessObject coordinatesDataAccessObject) {
        final SelectedLocationsOutputBoundary selectedLocationsOutputBoundary = new SelectedLocationsPresenter(
                selectedLocationsViewModel, viewManagerModel);
        final SelectedLocationsInputBoundary selectedLocationsInteractor = new SelectedLocationsInteractor(
                selectedLocationsOutputBoundary, coordinatesDataAccessObject);

        return new SelectedLocationsController(selectedLocationsInteractor);
    }
}
