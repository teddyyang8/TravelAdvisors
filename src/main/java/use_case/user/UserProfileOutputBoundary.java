package use_case.user;

import entity.User;

/**
 * Output boundary for the user profile use case.
 */
public interface UserProfileOutputBoundary {
    void prepareSuccessView(User user);

    void prepareFailView(String errorMessage);
}
