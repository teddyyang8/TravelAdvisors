package use_case.user;

import entity.User;
import entity.UserFactory;
import use_case.DataAccessException;

/**
 * Interactor for creating a user profile.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {
    private final UserDataAccessInterface userDataAccess;
    private final UserProfileOutputBoundary presenter;
    private final UserFactory userFactory;

    public UserProfileInteractor(UserDataAccessInterface userDataAccess, UserProfileOutputBoundary presenter,
                                 UserFactory userFactory) {
        this.userDataAccess = userDataAccess;
        this.presenter = presenter;
        this.userFactory = userFactory;
    }

    @Override
    public void createUser(UserProfileInputData inputData) {
        try {
            User user = userFactory.create(inputData.getUsername(), inputData.getPassword(), inputData.getInterests());
            userDataAccess.saveUser(user);
            presenter.prepareSuccessView(user);
        }
        catch (DataAccessException e) {
            presenter.prepareFailView(e.getMessage());
        }
    }
}
