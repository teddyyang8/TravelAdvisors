package app;

import entity.PlaceFactory;
import interface_adapter.location.LocationViewModel;
import use_case.suggest_locations.LocationDataAccessInterface;
import use_case.suggest_locations.SuggestLocationsInteractor;
import view.LocationView;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * Builder for the Suggested Locations Application.
 */
public class SuggestedLocationsApplication {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;
    private LocationDataAccessInterface locationDAO;
    private LocationViewModel locationViewModel = new LocationViewModel();
    private LocationView locationView;
    private SuggestLocationsInteractor locationInteractor;
    private PlaceFactory placeFactory;




    /**
     * Builds the application.
     * @return the JFrame for the application
     */
    public JFrame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Suggested Locations Application");
        frame.setSize(WIDTH, HEIGHT);

        frame.add();

        locationInteractor.executeRefresh();

        return frame;

    }
}


