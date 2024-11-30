package interface_adapter.user;

import interface_adapter.ViewModel;

/**
 * View model for the user profile.
 */
public class UserViewModel extends ViewModel<UserState> {
    public UserViewModel() {
        super("User Profile");
        setState(null);
    }
}
