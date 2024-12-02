package use_case.signup;

import entity.User;
import use_case.DataAccessException;

/**
 * Interface for accessing user data for sign-up.
 */
public interface SignupUserDataAccessInterface {
    /**
     * Get a user by username.
     * @param username the username to get
     * @return the user with the given username
     * @throws DataAccessException if the user does not exist
     */
    User getSignupUser(String username) throws DataAccessException;

    /**
     * Save a user to the data store.
     * @param user the user to save
     * @throws DataAccessException if the user already exists
     */
    void save(User user) throws DataAccessException;

    /**
     * Check if a user with the given username exists.
     * @param username the username to check
     * @return true if a user with the given username exists, false otherwise
     */
    boolean existsByName(String username);
}
