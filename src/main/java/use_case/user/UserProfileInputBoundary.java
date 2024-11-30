package use_case.user;

import use_case.DataAccessException;

/**
 * Input boundary for creating a user profile.
 */
public interface UserProfileInputBoundary {
    /**
     * Creates a new user profile.
     *
     * @param inputData the input data for the user profile
     * @throws DataAccessException if the user profile can not be created for any reason
     */
    void createUser(UserProfileInputData inputData) throws DataAccessException;
}
