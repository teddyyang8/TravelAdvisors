package app;

import javax.swing.*;

import data_access.DBLocationDataAccessObject;
import entity.PlaceFactory;
import entity.SuggestedPlaceFactory;
import interface_adapter.location.LocationController;
import interface_adapter.location.LocationPresenter;
import interface_adapter.location.LocationViewManagerModel;
import interface_adapter.location.LocationViewModel;
import use_case.suggest_locations.LocationDataAccessInterface;
import use_case.suggest_locations.SuggestLocationsInteractor;
import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import use_case.suggest_locations.*;
import view.LocationView;
import view.ViewManager;

import java.awt.*;

/**
 * Builder for the Location Application.
 */
public class LocationAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;
    public LocationDataAccessInterface locationDAO;
    public static LocationViewModel locationViewModel = new LocationViewModel();
    public LocationView locationView;
    public SuggestLocationsInteractor locationInteractor;
    public PlaceFactory placeFactory;

    /**
     * Sets the LocationDAO to be used in this application.
     * @param locationDataAccess the DAO to use
     * @return this builder
     */
    public LocationAppBuilder addLocationDAO(LocationDataAccessInterface locationDataAccess) {
        locationDAO = locationDataAccess;
        return this;
    }

    /**
     * Creates the objects for the Location Use Case and connects the
     * LocationView to its
     * controller.
     * <p>This method must be called after addLocationView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addLocationView
     */
    public LocationAppBuilder addLocationUseCase() {
        final SuggestLocationsOutputBoundary suggestLocationsOutputBoundary = new LocationPresenter(locationViewModel);
        locationInteractor = new SuggestLocationsInteractor(
                locationDAO, suggestLocationsOutputBoundary, placeFactory);

        final LocationController controller = new LocationController(locationInteractor);
        if (locationView == null) {
            throw new RuntimeException("addLocationView must be called before" + " addLocationUseCase");
        }
        locationView.setLocationController(controller);
        return this;
    }

    /**
     * Creates the LocationView and underlying LocationViewModel.
     * @return this builder
     */
    public LocationAppBuilder addLocationView() {
        locationViewModel = new LocationViewModel();
        locationView = new LocationView(locationViewModel);
        return this;
    }

    /**
     * Builds the application.
     * @return the JFrame for the application
     */
    public JFrame build() throws DataAccessException {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Location Application");
        frame.setSize(WIDTH, HEIGHT);

        final JTextField userInputField = new JTextField();
        frame.add(userInputField, BorderLayout.NORTH);

        frame.add(locationView, BorderLayout.CENTER);

        return frame;
    }

}
