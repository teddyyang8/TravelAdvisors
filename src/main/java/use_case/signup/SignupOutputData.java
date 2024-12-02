package use_case.signup;

/**
 * Data structure for holding signup output information.
 */
public class SignupOutputData {
    private final String username;
    private final boolean newUser;

    public SignupOutputData(String username, boolean newUser) {
        this.username = username;
        this.newUser = newUser;
    }

    public String getUsername() {
        return username;
    }

    public boolean isNewUser() {
        return newUser;
    }
}
