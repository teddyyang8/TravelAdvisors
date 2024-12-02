package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Place;
import entity.SavedPlace;
import interface_adapter.reviewlocation.ReviewLocationController;
import interface_adapter.reviewlocation.ReviewLocationState;
import interface_adapter.reviewlocation.ReviewLocationViewModel;
import use_case.DataAccessException;

/**
 * The View for when the user has selected a location in the program.
 */
public class ReviewLocationsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Review Locations";
    private final ReviewLocationViewModel reviewLocationViewModel;
    private final ReviewLocationController reviewLocationController;

    private final JPanel reviewLocationPanel;
    private final Map<SavedPlace, JTextArea> reviewFields = new HashMap<>();
    private final Map<SavedPlace, JCheckBox> ratingFields = new HashMap<>();
    private final JButton submitButton = new JButton("Submit Reviews");

    public ReviewLocationsView(ReviewLocationViewModel reviewLocationViewModel,
                               ReviewLocationController reviewLocationController) {
        this.reviewLocationViewModel = reviewLocationViewModel;
        this.reviewLocationViewModel.addPropertyChangeListener(this);
        this.reviewLocationController = reviewLocationController;

        final JLabel title = new JLabel("Review Locations:");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        this.reviewLocationPanel = new JPanel();
        this.reviewLocationPanel.setLayout(new BoxLayout(reviewLocationPanel, BoxLayout.Y_AXIS));

        this.add(title);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(reviewLocationPanel);
        this.add(submitButton);

        submitButton.addActionListener(this);

        updateReviewLocations(reviewLocationViewModel.getState());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateReviewLocations((ReviewLocationState) evt.getNewValue());
        }
    }

    private void updateReviewLocations(ReviewLocationState state) {
        reviewLocationPanel.removeAll();
        reviewFields.clear();
        ratingFields.clear();

        final List<SavedPlace> savedPlaces = state.getSavedPlaces();
        for (SavedPlace location : savedPlaces) {
            final JLabel nameLabel = new JLabel(location.getName());
            final JLabel addressLabel = new JLabel(location.getAddress());
            final JTextArea reviewInputField = new JTextArea(3, 20);
            final JCheckBox ratingCheckBox = new JCheckBox("Liked");

            reviewFields.put(location, reviewInputField);
            ratingFields.put(location, ratingCheckBox);

            final JPanel locationPanel = new JPanel();
            locationPanel.setLayout(new BoxLayout(locationPanel, BoxLayout.Y_AXIS));
            locationPanel.add(nameLabel);
            locationPanel.add(addressLabel);
            locationPanel.add(new JLabel("Review:"));
            locationPanel.add(reviewInputField);
            locationPanel.add(ratingCheckBox);

            reviewLocationPanel.add(locationPanel);
        }

        reviewLocationPanel.revalidate();
        reviewLocationPanel.repaint();
    }

    /**
     * This method is not used in this class.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(submitButton)) {
            final Map<SavedPlace, List<String>> addedReviews = new HashMap<>();
            for (Map.Entry<SavedPlace, JTextArea> entry : reviewFields.entrySet()) {
                final SavedPlace place = entry.getKey();
                final String review = entry.getValue().getText();
                final boolean liked = ratingFields.get(place).isSelected();
                final List<String> reviewAndRating = List.of(review, liked ? "like" : "dislike");
                addedReviews.put(place, reviewAndRating);
            }

            final ReviewLocationState currentState = reviewLocationViewModel.getState();
            currentState.setAddedReviews(addedReviews);
            reviewLocationViewModel.setState(currentState);
        }
    }

    public String getViewName() {
        return viewName;
    }
}