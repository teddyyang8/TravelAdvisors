package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
    private final JTextArea noteInputField = new JTextArea();

    private final JPanel reviewLocationPanel;

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
        final List<SavedPlace> savedPlaces = state.getPlaceList();
        for (SavedPlace location : savedPlaces) {
            final JLabel nameLabel = new JLabel(location.getName());
            final JLabel addressLabel = new JLabel(location.getAddress());

            final Map<Place, String> map =
                    state.getPlaceToCoordinates();
            actionButton.addActionListener(evt -> {
                        if (evt.getSource() == actionButton) {
                            try {
                                // Define the URL to open
                                final String coordinates = map.get(location);
                                final URI url = new URI("https://maps.google.com/?q=" + coordinates );

                                // Check if Desktop is supported
                                if (Desktop.isDesktopSupported()) {
                                    final Desktop desktop = Desktop.getDesktop();

                                    // Check if browsing is supported
                                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                                        desktop.browse(url);
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(this, "Browsing is not supported on your system.");
                                    }
                                }
                                else {
                                    JOptionPane.showMessageDialog(this, "Desktop is not supported on your system.");
                                }
                            }
                            catch (IOException | URISyntaxException ex) {
                                JOptionPane.showMessageDialog(this, "Failed to open the URL: " + ex.getMessage());
                            }
                        }
                    }
            );;

            reviewLocationPanel.revalidate();
            reviewLocationPanel.repaint();
        }
    }

    /**
     * This method is not used in this class.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public String getViewName() {
        return viewName;
    }
}