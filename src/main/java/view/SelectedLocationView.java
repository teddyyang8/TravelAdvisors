package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import entity.Place;
import interface_adapter.selectedlocation.SelectedLocationsController;
import interface_adapter.selectedlocation.SelectedLocationsState;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import interface_adapter.suggestlocation.SelectedLocationsState;

/**
 * The View for when the user has selected a location in the program.
 */
public class SelectedLocationView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Selected Location";
    private final SelectedLocationsViewModel selectedLocationsViewModel;
    private final SelectedLocationsController selectedLocationsController;
    private final SelectedLocationsState selectedLocationsState;

    private final JPanel selectedLocationPanel;

    public SelectedLocationView(SelectedLocationsViewModel selectedLocationsViewModel,
                                SelectedLocationsController selectedLocationsController,
                                SelectedLocationsState selectedLocationsState) {
        this.selectedLocationsViewModel = selectedLocationsViewModel;
        this.selectedLocationsViewModel.addPropertyChangeListener(this);
        this.selectedLocationsController = selectedLocationsController;
        this.selectedLocationsState = selectedLocationsState;

        this.selectedLocationPanel = new JPanel();
        this.selectedLocationPanel.setLayout(new BoxLayout(selectedLocationPanel, BoxLayout.Y_AXIS));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(selectedLocationPanel);

        updateSelectedLocations(selectedLocationsState.getSelectedLocations());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO: Implement action for button click
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