package use_case.user_profile;

import java.util.List;

/**
 * The input data for creating a user profile.
 */
public class UserProfileInputData {
    private final String username;
    private final String password;

    public UserProfileInputData(String username, String password, List<String> interests) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
