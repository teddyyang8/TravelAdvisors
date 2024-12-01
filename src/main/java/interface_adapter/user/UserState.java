package interface_adapter.user;

/**
 * Represents the state of a user.
 */
public class UserState {
    private String username;

    public UserState(String username) {
        this.username = username;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }
}
