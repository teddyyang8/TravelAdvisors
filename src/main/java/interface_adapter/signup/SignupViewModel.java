package interface_adapter.signup;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the signup process, holding state for the signup view.
 */
public class SignupViewModel {
    private String username;
    private String errorMessage;
    private final PropertyChangeSupport support;

    public SignupViewModel() {
        this.support = new PropertyChangeSupport(this);
    }

    public String getUsername() {
        return username;
    }

    /**
     * Sets the username and notifies listeners of the change.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        final String oldUsername = this.username;
        this.username = username;
        support.firePropertyChange("username", oldUsername, username);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message and notifies listeners of the change.
     *
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        final String oldErrorMessage = this.errorMessage;
        this.errorMessage = errorMessage;
        support.firePropertyChange("errorMessage", oldErrorMessage, errorMessage);
    }

    /**
     * Adds a property change listener.
     *
     * @param listener the listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener.
     *
     * @param listener the listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
