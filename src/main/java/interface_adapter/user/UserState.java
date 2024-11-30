package interface_adapter.user;

import java.util.List;

/**
 * The state representing user profile-related data.
 */
public class UserState {

    private String username = "";
    private String password = "";
    private List<String> interests;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}
