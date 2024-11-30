package view;

import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import interface_adapter.user.UserController;
import interface_adapter.user.UserState;
import interface_adapter.user.UserViewModel;
import use_case.DataAccessException;

/**
 * User profile view for creating a user profile.
 */
public class UserProfileView extends JPanel {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JComboBox<String> interestsBox1;
    private final JComboBox<String> interestsBox2;
    private final JComboBox<String> interestsBox3;
    private final JButton createProfileButton;

    public UserProfileView(UserController controller, UserViewModel userViewModel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(400, 300));

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        interestsBox1 = new JComboBox<>(new String[]{"Books", "Museums", "Cafes", "Parks", "Fine Dining", "Street Food", "Music", "Theater", "Sports"});
        interestsBox2 = new JComboBox<>(new String[]{"Books", "Museums", "Cafes", "Parks", "Fine Dining", "Street Food", "Music", "Theater", "Sports"});
        interestsBox3 = new JComboBox<>(new String[]{"Books", "Museums", "Cafes", "Parks", "Fine Dining", "Street Food", "Music", "Theater", "Sports"});
        createProfileButton = new JButton("Create Profile");

        addComponents();

        userViewModel.addPropertyChangeListener(evt -> handleStateChange(userViewModel.getState()));

        createProfileButton.addActionListener(evt -> handleCreateProfileAction(controller));
    }

    /**
     * Adds UI components to the panel.
     */
    private void addComponents() {
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Select up to 3 Interests:"));
        add(interestsBox1);
        add(interestsBox2);
        add(interestsBox3);
        add(createProfileButton);
    }

    /**
     * Handles the action of creating a user profile.
     *
     * @param controller The controller to manage user creation.
     */
    private void handleCreateProfileAction(UserController controller) {
        final String username = usernameField.getText();
        final String password = new String(passwordField.getPassword());
        try {
            controller.createUser(username, password, Arrays.asList(
                    interestsBox1.getSelectedItem().toString(),
                    interestsBox2.getSelectedItem().toString(),
                    interestsBox3.getSelectedItem().toString()
            ));
        }
        catch (DataAccessException dataAccessException) {
            System.err.println("Error creating user: " + dataAccessException.getMessage());
        }
    }

    /**
     * Handles changes in the user profile state.
     *
     * @param state The new user state.
     */
    private void handleStateChange(UserState state) {
        if (state != null) {
            usernameField.setText(state.getUsername());
            passwordField.setText("");
        }
    }
}
