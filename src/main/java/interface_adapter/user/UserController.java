package interface_adapter.user;

import java.util.List;

import use_case.DataAccessException;
import use_case.user.UserProfileInputBoundary;
import use_case.user.UserProfileInputData;

/**
 * Controller for creating a user profile.
 */
public class UserController {
    private final UserProfileInputBoundary interactor;

    public UserController(UserProfileInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Creates a new user profile.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @param interests the interests of the user
     * @throws DataAccessException if the user profile can not be created for any reason
     */
    public void createUser(String username, String password, List<String> interests) throws DataAccessException {
        final UserProfileInputData inputData = new UserProfileInputData(username, password, interests);
        interactor.createUser(inputData);
    }
}
