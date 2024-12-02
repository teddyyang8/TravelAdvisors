package app;

import java.awt.*;

import javax.swing.*;

import data_access.DBLocationDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import data_access.InMemoryCalendarDataAccessObject;
import entity.SuggestedPlaceFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_calendar.AddToCalendarViewModel;
import interface_adapter.location.LocationViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.reviewlocation.ReviewLocationViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import interface_adapter.user_profile.UserProfileViewModel;
import view.*;

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
        final UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
        final AddToCalendarViewModel calendarViewModel = new AddToCalendarViewModel();
        final ReviewLocationViewModel reviewLocationViewModel = new ReviewLocationViewModel();
        // add any future view models here in the same way

        final DBLocationDataAccessObject locationDataAccessObject = new DBLocationDataAccessObject(
                new SuggestedPlaceFactory());
        final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

        final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel,
                signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.getViewName());

        final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,
                userProfileViewModel, locationViewModel, userDataAccessObject);
        // logged in = user profile view
        views.add(loginView, loginView.getViewName());
        final InMemoryCalendarDataAccessObject calendarDataAccessObject = new InMemoryCalendarDataAccessObject();

        final LocationView locationView = LocationUseCaseFactory.create(viewManagerModel, locationViewModel,
                suggestedLocationsViewModel, locationDataAccessObject);
        views.add(locationView, locationView.getViewName());

        final SuggestedLocationsView suggestedLocationsView = SuggestedLocationsUseCaseFactory.create(viewManagerModel,
                suggestedLocationsViewModel, calendarViewModel);
        views.add(suggestedLocationsView, suggestedLocationsView.getViewName());

        final UserProfileView userProfileView = UserProfileUseCaseFactory.create(viewManagerModel,
                userProfileViewModel, userDataAccessObject, reviewLocationViewModel);
        views.add(userProfileView, userProfileView.getViewName());

        final ReviewLocationsView reviewLocationView = ReviewLocationsUseCaseFactory.create(viewManagerModel,
                reviewLocationViewModel, userProfileViewModel);
        views.add(reviewLocationView, reviewLocationView.getViewName());

        viewManagerModel.setState(signupView.getViewName());
        final CalendarView calendarView = CalendarUseCaseFactory.create(viewManagerModel, calendarViewModel,
                calendarDataAccessObject);
        views.add(calendarView, calendarView.getViewName());

        viewManagerModel.setState(locationView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

