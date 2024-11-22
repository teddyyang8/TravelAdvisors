package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsController;
import interface_adapter.suggestlocation.SuggestedLocationsPresenter;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import use_case.suggested_locations.SuggestedLocationInputBoundary;
import use_case.suggested_locations.SuggestedLocationInteractor;
import use_case.suggested_locations.SuggestedLocationOutputBoundary;
import use_case.suggested_locations.SuggestedLocationOutputData;
import view.SuggestedLocationsView;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the static factory function for creating the SuggestedLocationView.
 */
public class SuggestedLocationUseCaseFactory {
    /** Prevent instantiation. */
    private SuggestedLocationUseCaseFactory() {

    }

    /**
     * Factory function for creating the SuggestedLocationView.
     * @param viewManagerModel the ViewManagerModel to inject
     * @param locationViewModel the LocationViewModel to inject
     * @param suggestedLocationsViewModel the SuggestedLocationsViewModel to inject
     * @return the SuggestedLocationView created for the provided input classes.
     */
    public static SuggestedLocationsView create(
            ViewManagerModel viewManagerModel,
            LocationViewModel locationViewModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel) {
        final CardLayout cardLayout = new CardLayout();
        final JPanel parentPanel = new JPanel();
        final SuggestedLocationsController suggestedLocationController = createSuggestedLocationUseCase(
                viewManagerModel, locationViewModel,
                suggestedLocationsViewModel);

        return new SuggestedLocationsView(suggestedLocationsViewModel,
                suggestedLocationController, cardLayout, parentPanel);
}

    private static SuggestedLocationsController createSuggestedLocationUseCase(
            ViewManagerModel viewManagerModel,
            LocationViewModel locationViewModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel) {
        final SuggestedLocationsPresenter suggestedLocationOutputBoundary = new SuggestedLocationsPresenter(viewManagerModel,
                suggestedLocationsViewModel, locationViewModel);
        final SuggestedLocationInputBoundary locationInteractor = new SuggestedLocationInteractor(suggestedLocationOutputBoundary);
        return new SuggestedLocationsController(locationInteractor);
    }
    }
