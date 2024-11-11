package view;

import interface_adapter.location.LocationController;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;
import use_case.suggest_locations.DataAccessException;
import use_case.suggest_locations.SuggestLocationsInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class SuggestedLocationsView extends JPanel {

    private JPanel suggestedLocations;

    public SuggestedLocationsView(List<String> locations) {
        if (suggestedLocations != null) {
            this.remove(suggestedLocations);
        }

        suggestedLocations = new JPanel();
        suggestedLocations.setLayout(new BoxLayout(suggestedLocations, BoxLayout.Y_AXIS));
        suggestedLocations.add(new JLabel("List of Suggested Locations:"));
        for (String location : locations) {
            suggestedLocations.add(new JLabel(location));
        }
        this.add(suggestedLocations);
        this.setVisible(true);
    }
}
