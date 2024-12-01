package interface_adapter.user;

import interface_adapter.ViewModel;

/**
 * View model for the user profile.
 */
public class UserViewModel extends ViewModel<UserState> {
    private String successMessage;
    private String errorMessage;

    public UserViewModel() {
        super("User Profile");
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
