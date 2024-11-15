package app;

import javax.swing.*;

import entity.Place;
import entity.PlaceFactory;
import interface_adapter.suggestedLocation.SuggestedLocationPresenter;
import interface_adapter.suggestedLocation.SuggestedLocationViewModel;
import use_case.suggest_locations.LocationDataAccessInterface;
import use_case.suggest_locations.SuggestLocationsInteractor;
import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import use_case.suggest_locations.*;
import view.SuggestedLocationsView;

import java.awt.*;
import java.util.List;

/**
 * Builder for the Suggested Locations Application.
 */
public class SuggestedLocationsAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;
    private LocationDataAccessInterface locationDAO;
    private SuggestedLocationViewModel suggestedLocationViewModel = new SuggestedLocationViewModel();
    private SuggestedLocationsView suggestedLocationsView;
    private SuggestLocationsInteractor suggestLocationsInteractor;
    private PlaceFactory placeFactory;

    /**
     * Sets the LocationDAO to be used in this application.
     * @param locationDataAccess the DAO to use
     * @return this builder
     */
    public SuggestedLocationsAppBuilder addLocationDAO(LocationDataAccessInterface locationDataAccess) {
        locationDAO = locationDataAccess;
        return this;
    }

    /**
     * Creates the objects for the Location Use Case and connects the
     * SuggestedLocationsView to its
     * controller.
     * <p>This method must be called after addSuggestedLocationsView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before
     * addSuggestedLocationsView
     */
    public SuggestedLocationsAppBuilder addSuggestedLocationUseCase() {
        final SuggestLocationsOutputBoundary suggestLocationsOutputBoundary = new SuggestedLocationPresenter(suggestedLocationViewModel);
        suggestLocationsInteractor = new SuggestLocationsInteractor(
                locationDAO, suggestLocationsOutputBoundary, placeFactory);

        if (suggestedLocationsView == null) {
            throw new RuntimeException("addSuggestedLocationsView must be " + "called before" + " addLocationUseCase");
        }
        return this;
    }

    /**
     * Creates the SuggestedLocationView and underlying LocationViewModel.
     * @return this builder
     */
    public SuggestedLocationsAppBuilder addSuggestedLocationsView(List<Place> locations) {
        suggestedLocationViewModel = new SuggestedLocationViewModel();
        suggestedLocationsView = new SuggestedLocationsView(suggestedLocationViewModel);
        return this;
    }

    /**
     * Builds the application.
     * @return the JFrame for the application
     */
    public JFrame build() throws DataAccessException {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Suggested Locations Application");
        frame.setSize(WIDTH, HEIGHT);

        frame.add(suggestedLocationsView, BorderLayout.CENTER);

        return frame;
    }
}