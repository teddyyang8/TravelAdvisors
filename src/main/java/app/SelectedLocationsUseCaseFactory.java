package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.selectedlocation.SelectedLocationsController;
import interface_adapter.selectedlocation.SelectedLocationsPresenter;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import use_case.selected_locations.SelectedLocationsOutputBoundary;
import view.SelectedLocationView;

/**
 * This class contains the static factory function for creating the SelectedLocationView.
 */
public class SelectedLocationsUseCaseFactory {

    /** Prevent instantiation. */
    private SelectedLocationsUseCaseFactory() {

    }

    /**
     * Factory function for creating the LocationView.
     * @param viewManagerModel the ViewManagerModel to inject
     * @param suggestedLocationsViewModel the SuggestedLocationsViewModel to
     *                                    inject
     * @param selectedLocationsViewModel the SelectedLocationsViewModel to
     *                                   inject
     * @return the LocationView created for the provided input classes.
     */
    public static SelectedLocationView create(
            ViewManagerModel viewManagerModel, SuggestedLocationsViewModel suggestedLocationsViewModel,
            SelectedLocationsViewModel selectedLocationsViewModel) {

        final SelectedLocationsController selectedLocationsController =
            createSelectedLocationUseCase(viewManagerModel,
                    suggestedLocationsViewModel,
                                          selectedLocationsViewModel);
        return new SelectedLocationView(selectedLocationsViewModel,
                                        selectedLocationsController);
}

    private static SelectedLocationsController createSelectedLocationUseCase(
            ViewManagerModel viewManagerModel, SuggestedLocationsViewModel suggestedLocationsViewModel,
            SelectedLocationsViewModel selectedLocationsViewModel) {
    }
}
