package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import interface_adapter.reviewlocation.ReviewLocationState;
import interface_adapter.reviewlocation.ReviewLocationViewModel;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileViewModel;

/**
 * View for the user profile.
 */
public class UserProfileView extends JPanel implements PropertyChangeListener, ActionListener {

    private final String viewName = "User Profile";
    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileController userProfileController;
    private final ReviewLocationViewModel reviewLocationViewModel;

    private final JTextField placeNameField = new JTextField(20);
    private final JTextField placeAddressField = new JTextField(20);
    private final JTextField placeReviewField = new JTextField(20);
    private final JCheckBox placeRatingCheckBox = new JCheckBox("Liked");
    private final JButton addPlaceButton = new JButton("Add Place");
    private final JTextArea placesListArea = new JTextArea(10, 30);
    private final JButton logOutButton = new JButton("Log Out");

    public UserProfileView(UserProfileController userProfileController, UserProfileViewModel userProfileViewModel, ReviewLocationViewModel reviewLocationViewModel) {
        this.userProfileController = userProfileController;
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileViewModel.addPropertyChangeListener(this);
        this.reviewLocationViewModel = reviewLocationViewModel;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

//        // Add UI elements for adding a place
//        add(new JLabel("Place Name:"));
//        add(placeNameField);
//        add(new JLabel("Place Address:"));
//        add(placeAddressField);
//        add(new JLabel("Place Review:"));
//        add(placeReviewField);
//        add(placeRatingCheckBox);
//        add(addPlaceButton);
//
//        addPlaceButton.addActionListener(evt -> {
//            final String placeName = placeNameField.getText();
//            final String placeAddress = placeAddressField.getText();
//            final String placeReview = placeReviewField.getText();
//            final boolean rating = placeRatingCheckBox.isSelected();
//
//            final SavedPlace newPlace = new SavedPlace(placeName, placeAddress, placeReview, rating);
//            final List<Place> placeList = new ArrayList<>();
//            placeList.add(newPlace);
//
//            final Map<String, List<SavedPlace>> newPlaceMap = new HashMap<>();
//            newPlaceMap.put(placeName, placeList);
//
//            userProfileController.savePlaces(userProfileViewModel.getState().getName(), newPlaceMap);
//        });

        // Display list of saved places
        add(new JLabel("Saved Places:"));
        add(new JScrollPane(placesListArea));

        final Map<String, List<SavedPlace>> savedPlaces = userProfileViewModel.getState().getSavedPlaces();
        for (final Map.Entry<String, List<SavedPlace>> entry : savedPlaces.entrySet()) {
            final JPanel listNamePanel = new JPanel();
            final JLabel listName = new JLabel(entry.getKey());
            final JButton viewListButton = new JButton("View List");
            listNamePanel.add(listName);
            listNamePanel.add(viewListButton);
            //teddy u can add the action listener for the button here
            viewListButton.addActionListener(evt -> {
                final ReviewLocationState reviewLocationState = reviewLocationViewModel.getState();
                reviewLocationState.setListName(entry.getKey());
                reviewLocationState.setSavedPlaces(entry.getValue());
                reviewLocationViewModel.setState(reviewLocationState);
            });
        }

//        userProfileViewModel.addPropertyChangeListener(evt -> {
//            // if this doesn't work, try changing the "save places" to state, and in the presenter get rid of "save places"
//            if ("save places".equals(evt.getPropertyName())) {
//                final Map<String, List<Place>> savedPlaces = (Map<String, List<Place>>) evt.getNewValue();
//                final StringBuilder placesString = new StringBuilder();
//                for (Map.Entry<String, List<Place>> entry : savedPlaces.entrySet()) {
//                    placesString.append(entry.getKey()).append(":\n");
//                    for (Place place : entry.getValue()) {
//                        if (place instanceof SavedPlace) {
//                            final SavedPlace savedPlace = (SavedPlace) place;
//                            placesString.append("  - Address: ").append(savedPlace.getAddress()).append("\n");
//                            placesString.append("  - Review: ").append(savedPlace.getReview()).append("\n");
//                            placesString.append("  - Liked: ").append(savedPlace.getRating() ? "Yes" : "No").append("\n");
//                        }
//                    }
//                }
//                placesListArea.setText(placesString.toString());
//            }
//        });

        // Log out button
        add(logOutButton);
        logOutButton.addActionListener(evt -> userProfileController.logOut());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }

}
