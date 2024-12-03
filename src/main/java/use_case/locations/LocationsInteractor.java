package use_case.locations;


import entity.Place;
import entity.SavedPlace;
import interface_adapter.user_profile.UserProfileState;
import use_case.DataAccessException;
import entity.User;
import use_case.user_profile.UserProfileDataAccessInterface;

import java.util.List;
import java.util.Map;

/**
 * The Suggest Locations Interactor.
 */
public class LocationsInteractor implements LocationsInputBoundary {
    private final LocationDataAccessInterface placeDataAccessObject;
    private final LocationsOutputBoundary placePresenter;
    private final UserProfileDataAccessInterface userDataAccessObject;

    public LocationsInteractor(LocationDataAccessInterface suggestLocationsPlaceDataAccessInterface,
                               LocationsOutputBoundary locationsOutputBoundary,
                               UserProfileDataAccessInterface userDataAccessObject) {
        this.placeDataAccessObject = suggestLocationsPlaceDataAccessInterface;
        this.placePresenter = locationsOutputBoundary;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(LocationsInputData locationsInputData, String currentFilter) throws DataAccessException {
        final List<Place> suggestedPlaces = placeDataAccessObject
                .searchLocation(locationsInputData.getAddress(), locationsInputData.getLocationType());

        if ("None".equals(currentFilter)) {
            final LocationsOutputData locationsOutputData = new LocationsOutputData(placeDataAccessObject
                    .searchLocation(locationsInputData.getAddress(), locationsInputData.getLocationType()),
                    false);
            placePresenter.prepareSuccessView(locationsOutputData);
        } else if ("Remove Saved Locations".equals(currentFilter)) {
            for (Place place : suggestedPlaces) {
                final User user = userDataAccessObject.getUser(locationsInputData.getUsername());
                for (Map.Entry<String, List<SavedPlace>> entry : user.getSavedPlaces().entrySet()) {
                    if (entry.getValue().contains(place)) {
                        suggestedPlaces.remove(place);
                    }
                }
            }
            final LocationsOutputData locationsOutputData = new LocationsOutputData(suggestedPlaces, false);
            placePresenter.prepareSuccessView(locationsOutputData);
        }
    }
}
