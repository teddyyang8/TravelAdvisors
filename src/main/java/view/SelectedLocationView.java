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
import interface_adapter.selectedlocation.SelectedLocationsController;
import interface_adapter.selectedlocation.SelectedLocationsState;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import use_case.DataAccessException;

/**
 * The View for when the user has selected a location in the program.
 */
public class SelectedLocationView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Selected Locations";
    private final SelectedLocationsViewModel selectedLocationsViewModel;
    private final SelectedLocationsController selectedLocationsController;

    private final JButton newSearchButton;
    private final JPanel selectedLocationPanel;

    public SelectedLocationView(SelectedLocationsViewModel selectedLocationsViewModel,
                                SelectedLocationsController selectedLocationsController) {
        this.selectedLocationsViewModel = selectedLocationsViewModel;
        this.selectedLocationsViewModel.addPropertyChangeListener(this);
        this.selectedLocationsController = selectedLocationsController;

        final JLabel title = new JLabel("Selected Locations:");
        title.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        this.selectedLocationPanel = new JPanel();
        this.selectedLocationPanel.setLayout(new BoxLayout(selectedLocationPanel, BoxLayout.Y_AXIS));

        this.add(title);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(selectedLocationPanel);

        this.newSearchButton = new JButton("New Search");
        newSearchButton.addActionListener(this);
        newSearchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(newSearchButton);

        updateSelectedLocations(selectedLocationsViewModel.getState());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(newSearchButton)) {
            selectedLocationsController.switchToLocationView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateSelectedLocations((SelectedLocationsState) evt.getNewValue());
        }
    }

    private void updateSelectedLocations(SelectedLocationsState state) {
        selectedLocationPanel.removeAll();
        final List<Place> selectedLocations = state.getSelectedLocations();
        for (Place location : selectedLocations) {
            final JLabel nameLabel = new JLabel(location.getName());
            final JLabel addressLabel = new JLabel(location.getAddress());
            final JButton actionButton = new JButton("Get Directions to " + location.getName());
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
            );
            selectedLocationPanel.add(nameLabel);
            selectedLocationPanel.add(addressLabel);
            selectedLocationPanel.add(actionButton);
            selectedLocationPanel.add(Box.createVerticalStrut(10));

            selectedLocationPanel.revalidate();
            selectedLocationPanel.repaint();
        }
    }

    public String getViewName() {
        return viewName;
    }
}