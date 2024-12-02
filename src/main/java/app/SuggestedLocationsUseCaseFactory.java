package app;


import data_access.DBCoordinatesDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.selectedlocation.SelectedLocationsPresenter;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsController;
import interface_adapter.suggestlocation.SuggestedLocationsPresenter;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
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
     * @param selectedLocationsViewModel the SelectedLocationsViewModel to
     *                                   inject
     * @return the LocationView created for the provided input classes.
     */
    public static SuggestedLocationsView create(
            ViewManagerModel viewManagerModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            SelectedLocationsViewModel selectedLocationsViewModel) {

        final SuggestedLocationsController suggestedLocationsController = createSuggestedLocationUseCase(viewManagerModel,
                suggestedLocationsViewModel, selectedLocationsViewModel);

        final SelectedLocationsController selectedLocationController = createSelectedLocationUseCase(viewManagerModel,
                selectedLocationsViewModel, new DBCoordinatesDataAccessObject());

        // didnt pass in the card layout and parent panel (since teddy idk if ur doing it in the view)
        return new SuggestedLocationsView(suggestedLocationsViewModel,
                suggestedLocationsController, selectedLocationsViewModel, selectedLocationController);
    }

    private static SuggestedLocationsController createSuggestedLocationUseCase(
            ViewManagerModel viewManagerModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            SelectedLocationsViewModel selectedLocationsViewModel) {

        final SuggestedLocationsOutputBoundary suggestedLocationsOutputBoundary = new SuggestedLocationsPresenter(
                viewManagerModel, suggestedLocationsViewModel, selectedLocationsViewModel);
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


