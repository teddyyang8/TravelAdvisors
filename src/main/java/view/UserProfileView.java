package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.Place;
import entity.SavedPlace;
import interface_adapter.user.UserProfileController;
import interface_adapter.user.UserViewModel;

/**
 * View for the user profile.
 */
public class UserProfileView extends JPanel {
    private final JTextField placeNameField = new JTextField(20);
    private final JTextField placeAddressField = new JTextField(20);
    private final JTextField placeReviewField = new JTextField(20);
    private final JCheckBox placeRatingCheckBox = new JCheckBox("Liked");
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
        add(new JLabel("Place Address:"));
        add(placeAddressField);
        add(new JLabel("Place Review:"));
        add(placeReviewField);
        add(placeRatingCheckBox);
        add(addPlaceButton);

        addPlaceButton.addActionListener(evt -> {
            final String placeName = placeNameField.getText();
            final String placeAddress = placeAddressField.getText();
            final String placeReview = placeReviewField.getText();
            final boolean rating = placeRatingCheckBox.isSelected();

            final SavedPlace newPlace = new SavedPlace(placeName, placeAddress, placeReview, rating);
            final List<Place> placeList = new ArrayList<>();
            placeList.add(newPlace);

            final Map<String, List<Place>> newPlaceMap = new HashMap<>();
            newPlaceMap.put(placeName, placeList);

            userProfileController.savePlaces(userViewModel.getState().getName(), newPlaceMap);
        });

        // Display list of saved places
        add(new JLabel("Saved Places:"));
        add(new JScrollPane(placesListArea));

        userViewModel.addPropertyChangeListener(evt -> {
            if ("savedPlaces".equals(evt.getPropertyName())) {
                final Map<String, List<Place>> savedPlaces = (Map<String, List<Place>>) evt.getNewValue();
                final StringBuilder placesString = new StringBuilder();
                for (Map.Entry<String, List<Place>> entry : savedPlaces.entrySet()) {
                    placesString.append(entry.getKey()).append(":\n");
                    for (Place place : entry.getValue()) {
                        if (place instanceof SavedPlace) {
                            final SavedPlace savedPlace = (SavedPlace) place;
                            placesString.append("  - Address: ").append(savedPlace.getAddress()).append("\n");
                            placesString.append("  - Review: ").append(savedPlace.getReview()).append("\n");
                            placesString.append("  - Liked: ").append(savedPlace.getRating() ? "Yes" : "No").append("\n");
                        }
                    }
                }
                placesListArea.setText(placesString.toString());
            }
        });

        // Log out button
        add(logOutButton);
        logOutButton.addActionListener(evt -> userProfileController.logOut());
    }
}
