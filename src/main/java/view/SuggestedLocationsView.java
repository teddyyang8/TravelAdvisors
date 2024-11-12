package view;

import entity.Place;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;

import javax.swing.*;

/**
 * Suggested Locations View Panel
 */
public class SuggestedLocationsView extends JPanel {

    private JPanel suggestedLocationsPanel;

    public SuggestedLocationsView(LocationViewModel locationViewModel) {

        if (suggestedLocationsPanel != null) {
            this.remove(suggestedLocationsPanel);
        }
        final LocationState locationState = locationViewModel.getState();

        suggestedLocationsPanel.setLayout(new BoxLayout(suggestedLocationsPanel, BoxLayout.Y_AXIS));
        suggestedLocationsPanel.add(new JLabel("List of Suggested Locations:"));
        for (Place location : locationState.getSuggestedLocations()) {
            suggestedLocationsPanel.add(new JLabel(location.getName()));
            suggestedLocationsPanel.add(new JLabel(location.getAddress()));
        }
        this.add(suggestedLocationsPanel);
        this.setVisible(true);
    }
}
