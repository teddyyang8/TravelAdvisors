package use_case.user;

/**
 * Output boundary for the User Profile use case.
 */
public interface UserProfileOutputBoundary {
    /**
     * Prepares the view with a success message.
     *
     * @param message the message
     */
    void prepareSuccessView(String message);

    /**
     * Prepares the view with an error message.
     *
     * @param errorMessage the error message
     */

    void prepareFailView(String errorMessage);
}
