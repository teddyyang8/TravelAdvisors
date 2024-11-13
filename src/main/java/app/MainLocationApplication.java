package app;

import data_access.DBLocationDataAccessObject;
import entity.PlaceFactory;
import entity.SuggestedPlaceFactory;
import interface_adapter.location.LocationController;
import interface_adapter.location.LocationPresenter;
import interface_adapter.location.LocationViewManagerModel;
import interface_adapter.location.LocationViewModel;
import interface_adapter.suggestedLocation.SuggestedLocationController;
import interface_adapter.suggestedLocation.SuggestedLocationPresenter;
import interface_adapter.suggestedLocation.SuggestedLocationViewModel;
import use_case.suggest_locations.LocationDataAccessInterface;
import use_case.suggest_locations.SuggestLocationsInputBoundary;
import use_case.suggest_locations.SuggestLocationsInteractor;
import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import view.LocationView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

import static app.LocationAppBuilder.locationViewModel;

public class MainLocationApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final JFrame application = new JFrame("Location Application");
            final CardLayout cardLayout = new CardLayout();
            final JPanel views = new JPanel(cardLayout);
            final LocationViewModel locationViewModel = new LocationViewModel();
            final LocationView locationView = new LocationView(locationViewModel);
            final SuggestedLocationViewModel suggestedLocationViewModel = new SuggestedLocationViewModel();
            views.add(locationView, "Location");
            application.add(views);

            new ViewManager(locationView, cardLayout, viewManagerModel);
            final LocationController locationController = createLocationUseCase();

            createViewsAndAddToPanel(locationViewModel, views, locationController);

            cardLayout.show(views, "Location");

            application.pack();
            application.setVisible(true);
        });
    }

    private static void createViewsAndAddToPanel(LocationViewModel locationViewModel,
                                                 JPanel views,
                                                 LocationController locationController) {
        final LocationView locationView = new LocationView(locationViewModel);
        views.add(locationView, "Location");
    }

    private static LocationController createLocationUseCase() {
        final LocationDataAccessInterface location = new DBLocationDataAccessObject();
        final SuggestLocationsOutputBoundary suggestLocationsOutputBoundary = new LocationPresenter(locationViewModel);
        final PlaceFactory placeFactory = new SuggestedPlaceFactory();
        final SuggestLocationsInputBoundary locationInteractor = new SuggestLocationsInteractor(location, suggestLocationsOutputBoundary, placeFactory);
        return new LocationController(locationInteractor);
    }

    private static SuggestedLocationController createSuggestedLocationUseCase() {
        final LocationDataAccessInterface location = new DBLocationDataAccessObject();
        final SuggestLocationsOutputBoundary suggestLocationsOutputBoundary = new SuggestedLocationPresenter(suggestedLocationViewModel);
        final PlaceFactory placeFactory = new SuggestedPlaceFactory();
        final SuggestLocationsOutputBoundary suggestLocationsInteractor = new SuggestLocationsInteractor(location, suggestLocationsOutputBoundary, placeFactory);
        return new SuggestedLocationController(suggestLocationsInteractor);
    }
}