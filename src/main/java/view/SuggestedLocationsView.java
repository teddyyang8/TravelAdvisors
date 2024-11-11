package view;

import java.util.List;

import javax.swing.*;

import entity.Place;

public class SuggestedLocationsView extends JPanel {

    private JPanel suggestedLocations;

    public SuggestedLocationsView(List<Place> locations) {
        if (suggestedLocations != null) {
            this.remove(suggestedLocations);
        }

        suggestedLocations = new JPanel();
        suggestedLocations.setLayout(new BoxLayout(suggestedLocations, BoxLayout.Y_AXIS));
        suggestedLocations.add(new JLabel("List of Suggested Locations:"));

        for (Place location : locations) {
            suggestedLocations.add(new JLabel(location.getName()));
            suggestedLocations.add(new JLabel(location.getAddress()));
        }
        this.add(suggestedLocations);
        this.setVisible(true);
    }
}
