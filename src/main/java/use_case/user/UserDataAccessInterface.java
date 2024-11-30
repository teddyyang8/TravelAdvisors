package use_case.user;

import entity.User;
import use_case.DataAccessException;

/**
 * Interface for accessing user data.
 */
public interface UserDataAccessInterface {
    User findUserByName(String name) throws DataAccessException;

    void saveUser(User user) throws DataAccessException;
}
