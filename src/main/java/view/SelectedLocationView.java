package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;
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

        updateSelectedLocations(selectedLocationsViewModel.getState());
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
                    selectedLocationsViewModel.getState().getPlaceToCoordinates();
            actionButton.addActionListener(evt -> {
                if (actionButton.isSelected()) {
                        String coordinates = map.get(location);
                        String url = "https://maps.google.com/?q=" + coordinates;
                        JOptionPane.showMessageDialog(this, url, "Directions", JOptionPane.INFORMATION_MESSAGE);

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