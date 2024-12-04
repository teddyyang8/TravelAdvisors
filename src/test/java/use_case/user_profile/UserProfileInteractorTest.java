package use_case.user_profile;

import entity.SavedPlace;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import use_case.DataAccessException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserProfileInteractorTest {

    private UserProfileDataAccessInterface userProfileDataAccess;
    private UserProfileOutputBoundary userProfileOutputBoundary;
    private UserProfileInteractor interactor;

    @Before
    public void setUp() {
        userProfileDataAccess = new UserProfileDataAccessInterface() {
            @Override
            public User getUser(String username) throws DataAccessException {
                return null;
            }

            @Override
            public void savePlaces(String username, Map<String, List<SavedPlace>> places) throws DataAccessException {
                // Simulate successful save
            }

            @Override
            public Map<String, List<SavedPlace>> getSavedPlaces(String username) throws DataAccessException {
                return Map.of();
            }
        };

        userProfileOutputBoundary = new UserProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                assertEquals("Places saved successfully!", message);
            }

            @Override
            public void prepareFailView(String message) {
                fail("Use case failure is unexpected.");
            }
        };

        interactor = new UserProfileInteractor(userProfileDataAccess, userProfileOutputBoundary);
    }

    @Test
    public void testSavePlacesSuccess() {
        // Arrange
        SavedPlace place = new SavedPlace("Place1", "Address1", "Review1", true);
        Map<String, List<SavedPlace>> places = Collections.singletonMap("List1", List.of(place));

        // Act
        interactor.savePlaces("username", places);
    }

    @Test
    public void testSavePlacesFailure() {
        // Arrange
        userProfileDataAccess = new UserProfileDataAccessInterface() {
            @Override
            public User getUser(String username) throws DataAccessException {
                return null;
            }

            @Override
            public void savePlaces(String username, Map<String, List<SavedPlace>> places) throws DataAccessException {
                throw new DataAccessException("Failed to save places.");
            }

            @Override
            public Map<String, List<SavedPlace>> getSavedPlaces(String username) throws DataAccessException {
                return Map.of();
            }
        };

        userProfileOutputBoundary = new UserProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String message) {
                assertEquals("Failed to save places.", message);
            }
        };

        interactor = new UserProfileInteractor(userProfileDataAccess, userProfileOutputBoundary);

        // Act
        interactor.savePlaces("username", Collections.emptyMap());
    }

    @Test
    public void testLogOut() {
        // Arrange
        userProfileOutputBoundary = new UserProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String message) {
                assertEquals("Logging out is currently not implemented.", message);
            }
        };

        interactor = new UserProfileInteractor(userProfileDataAccess, userProfileOutputBoundary);

        // Act
        interactor.logOut();
    }
}