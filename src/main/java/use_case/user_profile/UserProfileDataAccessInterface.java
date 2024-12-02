package use_case.user_profile;

import java.util.List;
import java.util.Map;

import entity.Place;
import entity.User;
import use_case.DataAccessException;

/**
 * Interface for accessing user profile data.
 */
public interface UserProfileDataAccessInterface {
    /**
     * Get the user with the given username.
     *
     * @param username the username
     * @return the user
     * @throws DataAccessException if the user does not exist
     */
    User getUser(String username) throws DataAccessException;

}
