package app;

import data_access.DBCoordinatesDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationViewModel;
import interface_adapter.selectedlocation.SelectedLocationsController;
import interface_adapter.selectedlocation.SelectedLocationsPresenter;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.selected_locations.SelectedLocationsInputBoundary;
import use_case.selected_locations.SelectedLocationsInteractor;
import use_case.selected_locations.SelectedLocationsOutputBoundary;
import view.SelectedLocationView;

/**
 * This class contains the static factory function for creating the SelectedLocationView.
 */
public class SelectedLocationsUseCaseFactory {

    /** Prevent instantiation. */
    private SelectedLocationsUseCaseFactory() {

    }

    /**
     * Factory function for creating the LocationView.
     * @param viewManagerModel the ViewManagerModel to inject
     *
     * @param selectedLocationsViewModel the SelectedLocationsViewModel to
     *                                   inject
     * @return the LocationView created for the provided input classes.
     */
    public static SelectedLocationView create(
            ViewManagerModel viewManagerModel,
            SelectedLocationsViewModel selectedLocationsViewModel,
            DBCoordinatesDataAccessObject coordinatesDataAccessObject,
            LocationViewModel locationViewModel,
            UserProfileViewModel userProfileViewModel) {

        final SelectedLocationsController selectedLocationsController =
            createSelectedLocationUseCase(viewManagerModel, selectedLocationsViewModel,
                    coordinatesDataAccessObject, locationViewModel, userProfileViewModel);
        return new SelectedLocationView(selectedLocationsViewModel,
                                        selectedLocationsController);
    }

    private static SelectedLocationsController createSelectedLocationUseCase(
            ViewManagerModel viewManagerModel,
            SelectedLocationsViewModel selectedLocationsViewModel,
            DBCoordinatesDataAccessObject coordinatesDataAccessObject,
            LocationViewModel locationViewModel, UserProfileViewModel userProfileViewModel) {
        final SelectedLocationsOutputBoundary selectedLocationsOutputBoundary = new SelectedLocationsPresenter(
                selectedLocationsViewModel, viewManagerModel, locationViewModel, userProfileViewModel);
        final SelectedLocationsInputBoundary selectedLocationsInteractor = new SelectedLocationsInteractor(
                selectedLocationsOutputBoundary, coordinatesDataAccessObject);

        return new SelectedLocationsController(selectedLocationsInteractor);
    }
}
