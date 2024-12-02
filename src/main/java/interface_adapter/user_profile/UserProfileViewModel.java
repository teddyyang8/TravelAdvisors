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
}
