package use_case.signup;

import entity.User;
import use_case.DataAccessException;

/**
 * Interface for accessing SignupUser data.
 */
public interface SignupUserDataAccessInterface {
    /**
     * Gets the SignupUser with the given username.
     *
     * @param username the username
     * @return the SignupUser with the given username
     * @throws DataAccessException if there is an issue accessing the data
     */
    User getSignupUser(String username) throws DataAccessException;

    /**
     * Saves the given SignupUser.
     *
     * @param user the SignupUser to save
     * @throws DataAccessException if there is an issue accessing the data
     */
    void save(User user) throws DataAccessException;

    /**
     * Checks if a SignupUser with the given username exists.
     *
     * @param username the username
     * @return true if a SignupUser with the given username exists, false otherwise
     * @throws DataAccessException if there is an issue accessing the data
     */
    boolean existsByName(String username) throws DataAccessException;
}
