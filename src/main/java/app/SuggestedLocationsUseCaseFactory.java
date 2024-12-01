package app;


import interface_adapter.ViewManagerModel;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
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

        // didnt pass in the card layout and parent panel (since teddy idk if ur doing it in the view)
        return new SuggestedLocationsView(suggestedLocationsViewModel,
                suggestedLocationsController, selectedLocationsViewModel);
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
}


