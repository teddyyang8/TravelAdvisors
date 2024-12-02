package interface_adapter.user_profile;

import interface_adapter.ViewModel;

/**
 * View model for the user profile.
 */
public class UserProfileViewModel extends ViewModel<UserProfileState> {

    public UserProfileViewModel() {
        super("User Profile");
        setState(new UserProfileState());
    }
//    private String successMessage;
//    private String errorMessage;
//
//    public UserProfileViewModel() {
//        super("User Profile");
//    }
//
//    public String getSuccessMessage() {
//        return successMessage;
//    }
//
//    public void setSuccessMessage(String successMessage) {
//        this.successMessage = successMessage;
//    }
//
//    public String getErrorMessage() {
//        return errorMessage;
//    }
//
//    public void setErrorMessage(String errorMessage) {
//        this.errorMessage = errorMessage;
//    }
}
