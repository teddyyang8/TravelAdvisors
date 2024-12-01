package view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import interface_adapter.user.UserProfileController;
import interface_adapter.user.UserViewModel;

/**
 * View for the user profile.
 */
public class UserProfileView extends JPanel {
    private final JTextField placeNameField = new JTextField(20);
    private final JTextField placeDescriptionField = new JTextField(20);
    private final JButton addPlaceButton = new JButton("Add Place");
    private final JTextArea placesListArea = new JTextArea(10, 30);
    private final JButton logOutButton = new JButton("Log Out");
    private final UserProfileController userProfileController;

    public UserProfileView(UserProfileController userProfileController, UserViewModel userViewModel) {
        this.userProfileController = userProfileController;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add UI elements for adding a place
        add(new JLabel("Place Name:"));
        add(placeNameField);
        add(new JLabel("Place Description:"));
        add(placeDescriptionField);
        add(addPlaceButton);

        addPlaceButton.addActionListener(evt -> {
            final String placeName = placeNameField.getText();
            final String placeDescription = placeDescriptionField.getText();

            final Map<String, String> newPlace = new HashMap<>();
            newPlace.put(placeName, placeDescription);

            userProfileController.savePlaces(userViewModel.getState().getUsername(), newPlace);
        });

        // Display list of saved places
        add(new JLabel("Saved Places:"));
        add(new JScrollPane(placesListArea));

        userViewModel.addPropertyChangeListener(evt -> {
            if ("savedPlaces".equals(evt.getPropertyName())) {
                final Map<String, String> savedPlaces = (Map<String, String>) evt.getNewValue();
                final StringBuilder placesString = new StringBuilder();
                for (Map.Entry<String, String> entry : savedPlaces.entrySet()) {
                    placesString.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                }
                placesListArea.setText(placesString.toString());
            }
        });

        // Log out button
        add(logOutButton);
        logOutButton.addActionListener(evt -> userProfileController.logOut());
    }
}
