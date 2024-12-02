package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationController;
import interface_adapter.location.LocationPresenter;
import interface_adapter.location.LocationViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import use_case.locations.LocationDataAccessInterface;
import use_case.locations.LocationsInputBoundary;
import use_case.locations.LocationsInteractor;
import use_case.locations.LocationsOutputBoundary;
import use_case.user_profile.UserProfileDataAccessInterface;
import view.LocationView;

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
     * @param userDataAccessObject the UserProfileDataAccessInterface to inject
     * @return the LocationView created for the provided input classes.
     */
    public static LocationView create(
            ViewManagerModel viewManagerModel,
            LocationViewModel locationViewModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            LocationDataAccessInterface locationDataAccessObject,
            UserProfileDataAccessInterface userDataAccessObject) {

        final LocationController locationController = createLocationUseCase(viewManagerModel, locationViewModel,
                suggestedLocationsViewModel, locationDataAccessObject, userDataAccessObject);

        return new LocationView(locationViewModel, locationController);
    }

    private static LocationController createLocationUseCase(
            ViewManagerModel viewManagerModel,
            LocationViewModel locationViewModel,
            SuggestedLocationsViewModel suggestedLocationsViewModel,
            LocationDataAccessInterface locationDataAccessObject,
            UserProfileDataAccessInterface userDataAccessObject) {

        final LocationsOutputBoundary locationsOutputBoundary = new LocationPresenter(locationViewModel,
                suggestedLocationsViewModel, viewManagerModel);
        final LocationsInputBoundary locationInteractor = new LocationsInteractor(locationDataAccessObject,
                locationsOutputBoundary, userDataAccessObject);

        return new LocationController(locationInteractor);
    }

}
