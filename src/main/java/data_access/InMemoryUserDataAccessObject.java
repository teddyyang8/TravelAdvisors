package data_access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Place;
import entity.SavedPlace;
import entity.User;
import use_case.DataAccessException;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.user_profile.UserProfileDataAccessInterface;

/**
 * In-memory implementation for storing user data for both sign-up and user profile.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface, UserProfileDataAccessInterface {

    private final Map<String, User> userDatabase = new HashMap<>();

    @Override
    public void save(User user) {
        userDatabase.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return userDatabase.get(username);
    }

    @Override
    public User getUser(String username) throws DataAccessException {
        final User user = userDatabase.get(username);
        if (user == null) {
            throw new DataAccessException("User not found.");
        }
        return user;
    }

    @Override
    public boolean existsByName(String username) {
        return userDatabase.containsKey(username);
    }

    @Override
    public void savePlaces(String username, Map<String, List<SavedPlace>> places) throws DataAccessException {
        final User user = getUser(username);
        if (user != null) {
            user.getSavedPlaces().putAll(places);
        }
        else {
            throw new DataAccessException("User not found.");
        }
    }

    @Override
    public Map<String, List<SavedPlace>> getSavedPlaces(String username) throws DataAccessException {
        final User user = getUser(username);
        if (user != null) {
            return user.getSavedPlaces();
        }
        else {
            throw new DataAccessException("User not found.");
        }
    }

    // This method is added to fulfill the requirement of SignupUserDataAccessInterface
    @Override
    public User getSignupUser(String username) throws DataAccessException {
        return getUser(username);
    }
}
