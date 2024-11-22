package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationController;
import interface_adapter.location.LocationViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsPresenter;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import use_case.suggest_locations.LocationDataAccessInterface;
import use_case.suggest_locations.SuggestLocationsInputBoundary;
import use_case.suggest_locations.SuggestLocationsInteractor;
import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import view.LocationView;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the static factory function for creating the LocationView.
 */
public class LocationUseCaseFactory {

    /** Prevent instantiation. */
    private LocationUseCaseFactory() {

    }

    /**
     * Factory function for creating the LocationView.
     * @param viewManagerModel the ViewManagerModel to inject
     * @param locationViewModel the LocationViewModel to inject
     * @param suggestedLocationsViewModel the SuggestedLocationsViewModel to inject
     * @param locationDataAccessObject the LocationDataAccessInterface to inject
     * @return the LocationView created for the provided input classes.
     */
    public static LocationView create(
            ViewManagerModel viewManagerModel,
            LocationViewModel locationViewModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            LocationDataAccessInterface locationDataAccessObject) {

        final CardLayout cardLayout = new CardLayout();
        final JPanel parentPanel = new JPanel();
        final LocationController locationController = createLocationUseCase(viewManagerModel, locationViewModel,
                suggestedLocationsViewModel, locationDataAccessObject);

        return new LocationView(locationViewModel, locationController, cardLayout, parentPanel);
    }

    private static LocationController createLocationUseCase(
            ViewManagerModel viewManagerModel,
            LocationViewModel locationViewModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            LocationDataAccessInterface userDataAccessObject) {

        final SuggestLocationsOutputBoundary suggestLocationsOutputBoundary = new SuggestedLocationsPresenter(viewManagerModel,
                suggestedLocationsViewModel, locationViewModel);
        final SuggestLocationsInputBoundary locationInteractor = new SuggestLocationsInteractor(userDataAccessObject,
                suggestLocationsOutputBoundary);

        return new LocationController(locationInteractor);
    }

}
