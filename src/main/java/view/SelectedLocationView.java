package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import entity.Place;
import interface_adapter.selectedlocation.SelectedLocationsController;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import use_case.selected_locations.SelectedLocationsOutputData;

/**
 * The View for when the user has selected a location in the program.
 */
public class SelectedLocationView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Selected Location";
    private final SelectedLocationsViewModel selectedLocationsViewModel;
    private final SelectedLocationsController selectedLocationsController;

    private final JPanel selectedLocationPanel;

    public SelectedLocationView(SelectedLocationsViewModel selectedLocationsViewModel,
                                SelectedLocationsController selectedLocationsController) {
        this.selectedLocationsViewModel = selectedLocationsViewModel;
        this.selectedLocationsViewModel.addPropertyChangeListener(this);
        this.selectedLocationsController = selectedLocationsController;

        this.selectedLocationPanel = new JPanel();
        this.selectedLocationPanel.setLayout(new BoxLayout(selectedLocationPanel, BoxLayout.Y_AXIS));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(selectedLocationPanel);

        updateSelectedLocations(selectedLocationsViewModel.getState().getSelectedLocations());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final JButton sourceButton = (JButton) e.getSource();
        final String locationName = sourceButton.getText().replace("Get " + "Address for ", "");
        final List<Place> selectedLocations = selectedLocationsViewModel.getState().getSelectedLocations();

        for (Place location : selectedLocations) {
            if (location.getName().equals(locationName)) {
                try {
                    String coordinates =
                            SelectedLocationsOutputData.getLocationCoordinatesMap().get(location);
                    String message =
                            "https://maps.google.com/?q=" + coordinates[0] + "," + coordinates[1];
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Failed to fetch coordinates for " + locationName, "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("selectedLocations".equals(evt.getPropertyName())) {
            updateSelectedLocations((List<Place>) evt.getNewValue());
        }
    }

    private void updateSelectedLocations(List<Place> selectedLocations) {
        selectedLocationPanel.removeAll();
        for (Place location : selectedLocations) {
            final JLabel nameLabel = new JLabel(location.getName());
            final JLabel addressLabel = new JLabel(location.getAddress());
            final JButton actionButton =
                    new JButton("Get Address for " + location.getName());
            actionButton.addActionListener(this);

            selectedLocationPanel.add(nameLabel);
            selectedLocationPanel.add(addressLabel);
            selectedLocationPanel.add(actionButton);
            selectedLocationPanel.add(Box.createVerticalStrut(10));
        }
        selectedLocationPanel.revalidate();
        selectedLocationPanel.repaint();
    }

    public String getViewName() {
        return viewName;
    }
}