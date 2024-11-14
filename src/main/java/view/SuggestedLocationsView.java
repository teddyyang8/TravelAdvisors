package view;

import entity.Place;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;
import interface_adapter.suggestedLocation.SuggestedLocationState;
import interface_adapter.suggestedLocation.SuggestedLocationViewModel;

import javax.swing.*;

/**
 * Suggested Locations View Panel
 */
public class SuggestedLocationsView extends JPanel {

    private JPanel suggestedLocationsPanel;

    public SuggestedLocationsView(SuggestedLocationViewModel suggestedLocationViewModel) {

        if (suggestedLocationsPanel != null) {
            this.remove(suggestedLocationsPanel);
        }
        final SuggestedLocationState suggestedLocationState = suggestedLocationViewModel.getState();

        suggestedLocationsPanel.setLayout(new BoxLayout(suggestedLocationsPanel, BoxLayout.Y_AXIS));
        suggestedLocationsPanel.add(new JLabel("List of Suggested Locations:"));
        for (Place location : suggestedLocationState.getSuggestedLocations()) {
            suggestedLocationsPanel.add(new JLabel(location.getName()));
            suggestedLocationsPanel.add(new JLabel(location.getAddress()));
        }
        this.add(suggestedLocationsPanel);
        this.setVisible(true);
    }
}
