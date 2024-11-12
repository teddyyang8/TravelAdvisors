package app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import entity.PlaceFactory;
import interface_adapter.location.LocationController;
import interface_adapter.location.LocationPresenter;
import interface_adapter.location.LocationViewModel;
import use_case.suggest_locations.LocationDataAccessInterface;
import use_case.suggest_locations.SuggestLocationsInteractor;
import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import view.LocationView;

/**
 * Builder for the Note Application.
 */
public class LocationAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;
    private LocationDataAccessInterface locationDAO;
    private LocationViewModel locationViewModel = new LocationViewModel();
    private LocationView locationView;
    private SuggestLocationsInteractor locationInteractor;
    private PlaceFactory placeFactory;

    /**
     * Sets the NoteDAO to be used in this application.
     * @param locationDataAccess the DAO to use
     * @return this builder
     */
    public LocationAppBuilder addLocationDAO(LocationDataAccessInterface locationDataAccess) {
        locationDAO = locationDataAccess;
        return this;
    }

    /**
     * Creates the objects for the Note Use Case and connects the NoteView to its
     * controller.
     * <p>This method must be called after addNoteView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addNoteView
     */
    public LocationAppBuilder addLocationUseCase() {
        final SuggestLocationsOutputBoundary suggestLocationsOutputBoundary = new LocationPresenter(locationViewModel);
        locationInteractor = new SuggestLocationsInteractor(
                locationDAO, suggestLocationsOutputBoundary, placeFactory);

        final LocationController controller = new LocationController(locationInteractor);
        if (locationView == null) {
            throw new RuntimeException("addNoteView must be called before addNoteUseCase");
        }
        locationView.setLocationController(controller);
        return this;
    }

    /**
     * Creates the NoteView and underlying NoteViewModel.
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
    public JFrame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Location Application");
        frame.setSize(WIDTH, HEIGHT);

        frame.add(locationView);

        // refresh so that the note will be visible when we start the program
        locationInteractor.execute();

        return frame;
    }
}
