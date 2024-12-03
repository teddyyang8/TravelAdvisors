package app;

import java.awt.*;

import javax.swing.*;

import data_access.DBCoordinatesDataAccessObject;
import data_access.DBLocationDataAccessObject;
import data_access.InMemoryCalendarDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.SuggestedPlaceFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_calendar.AddToCalendarViewModel;
import interface_adapter.location.LocationViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.reviewlocation.ReviewLocationViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import view.CalendarView;
import view.LocationView;
import view.LoginView;
import view.SelectedLocationView;
import view.SignupView;
import view.SuggestedLocationsView;
import view.UserProfileView;
import view.ViewManager;

import interface_adapter.user_profile.UserProfileViewModel;

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

        final LoginViewModel loginViewModel = new LoginViewModel();
        final SignupViewModel signupViewModel = new SignupViewModel();
        final LocationViewModel locationViewModel = new LocationViewModel();
        final SuggestedLocationsViewModel suggestedLocationsViewModel = new SuggestedLocationsViewModel();
        final AddToCalendarViewModel calendarViewModel = new AddToCalendarViewModel();
        final SelectedLocationsViewModel selectedLocationsViewModel = new SelectedLocationsViewModel();
        final UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
        final ReviewLocationViewModel reviewLocationViewModel = new ReviewLocationViewModel();
        // add any future view models here in the same way

        final DBLocationDataAccessObject locationDataAccessObject = new DBLocationDataAccessObject(
                new SuggestedPlaceFactory());
        final InMemoryCalendarDataAccessObject calendarDataAccessObject = new InMemoryCalendarDataAccessObject();
        final DBCoordinatesDataAccessObject coordinatesDataAccessObject = new DBCoordinatesDataAccessObject();
        final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

        final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel,
                signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.getViewName());

        final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,
                userProfileViewModel, locationViewModel, userDataAccessObject, signupViewModel);
        views.add(loginView, loginView.getViewName());

        final LocationView locationView = LocationUseCaseFactory.create(viewManagerModel, locationViewModel,
                suggestedLocationsViewModel, locationDataAccessObject, userDataAccessObject);
        views.add(locationView, locationView.getViewName());

        final SuggestedLocationsView suggestedLocationsView = SuggestedLocationsUseCaseFactory.create(viewManagerModel,
                suggestedLocationsViewModel, calendarViewModel, selectedLocationsViewModel, coordinatesDataAccessObject,
                calendarDataAccessObject, locationViewModel, userProfileViewModel);
        views.add(suggestedLocationsView, suggestedLocationsView.getViewName());

        final SelectedLocationView selectedLocationView = SelectedLocationsUseCaseFactory.create(viewManagerModel,
                 selectedLocationsViewModel, coordinatesDataAccessObject, locationViewModel, userProfileViewModel);
        views.add(selectedLocationView, selectedLocationView.getViewName());

        final CalendarView calendarView = CalendarUseCaseFactory.create(viewManagerModel, calendarViewModel,
                calendarDataAccessObject, locationViewModel);
        views.add(calendarView, calendarView.getViewName());

        final UserProfileView userProfileView = UserProfileUseCaseFactory.create(viewManagerModel,
                userProfileViewModel, userDataAccessObject, reviewLocationViewModel);
        views.add(userProfileView, userProfileView.getViewName());

        final ReviewLocationsView reviewLocationView = ReviewLocationsUseCaseFactory.create(viewManagerModel,
                reviewLocationViewModel, userProfileViewModel);
        views.add(reviewLocationView, reviewLocationView.getViewName());

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

