package app;

import data_access.DBCoordinatesDataAccessObject;
import data_access.DBLocationDataAccessObject;
import entity.SuggestedPlaceFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationViewModel;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import view.LocationView;
import view.SelectedLocationView;
import view.SuggestedLocationsView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

/**
 * The version of Main with an external database used to persist user data.
 */
public class MainWithDB {

    /**
     * The main method for starting the program with an external database used to persist user data.
     * @param args input to main
     */
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        final JFrame application = new JFrame("Location Suggester");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final CardLayout cardLayout = new CardLayout();

        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        final LocationViewModel locationViewModel = new LocationViewModel();
        final SuggestedLocationsViewModel suggestedLocationsViewModel = new SuggestedLocationsViewModel();
        final SelectedLocationsViewModel selectedLocationsViewModel = new SelectedLocationsViewModel();
        // add any future view models here in the same way

        final DBLocationDataAccessObject locationDataAccessObject = new DBLocationDataAccessObject(
                new SuggestedPlaceFactory());
        final DBCoordinatesDataAccessObject coordinatesDataAccessObject = new DBCoordinatesDataAccessObject();

        final LocationView locationView = LocationUseCaseFactory.create(viewManagerModel, locationViewModel,
                suggestedLocationsViewModel, locationDataAccessObject);
        views.add(locationView, locationView.getViewName());

        final SuggestedLocationsView suggestedLocationsView = SuggestedLocationsUseCaseFactory.create(viewManagerModel,
                suggestedLocationsViewModel, selectedLocationsViewModel);
        views.add(suggestedLocationsView, suggestedLocationsView.getViewName());

        final SelectedLocationView selectedLocationView = SelectedLocationsUseCaseFactory.create(viewManagerModel,
                 selectedLocationsViewModel, coordinatesDataAccessObject);
        views.add(selectedLocationView, selectedLocationView.getViewName());

        viewManagerModel.setState(locationView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

