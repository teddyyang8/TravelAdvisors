package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import entity.Place;
import interface_adapter.suggestlocation.SuggestedLocationsController;
import interface_adapter.suggestlocation.SuggestedLocationsState;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;

/**
 * The View for when the user receives a list of suggested locations in the program.
 */
public class SuggestedLocationsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Suggested Locations";
    private final SuggestedLocationsViewModel suggestedLocationsViewModel;
    private final SuggestedLocationsController suggestedLocationsController;

    private final JPanel suggestedLocationsPanel;
    private final JButton newSearchButton;
    private final List<Place> selectedLocations;

    public SuggestedLocationsView(SuggestedLocationsViewModel suggestedLocationsViewModel,
                                  SuggestedLocationsController suggestedLocationsController) {
        this.suggestedLocationsViewModel = suggestedLocationsViewModel;
        this.suggestedLocationsViewModel.addPropertyChangeListener(this);
        this.suggestedLocationsController = suggestedLocationsController;

        final JLabel title = new JLabel("List of Suggested Locations:");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.suggestedLocationsPanel = new JPanel();
        this.suggestedLocationsPanel.setLayout(new BoxLayout(suggestedLocationsPanel, BoxLayout.Y_AXIS));

        this.newSearchButton = new JButton("New Search");
        newSearchButton.addActionListener(this);

        this.selectedLocations = new ArrayList<>();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(suggestedLocationsPanel);
        this.add(newSearchButton);

        updateSuggestedLocations(suggestedLocationsViewModel.getState());
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(newSearchButton)) {
            // Handle new search button click
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateSuggestedLocations((SuggestedLocationsState) evt.getNewValue());
        }
    }

    private void updateSuggestedLocations(SuggestedLocationsState state) {
        suggestedLocationsPanel.removeAll();
        final List<Place> suggestedLocations = state.getSuggestedLocations();
        if (suggestedLocations != null) {
            for (int i = 0; i < Math.min(10, suggestedLocations.size()); i++) {
                final Place location = suggestedLocations.get(i);
                final JPanel locationPanel = new JPanel();
                locationPanel.setLayout(new BoxLayout(locationPanel, BoxLayout.Y_AXIS));
                final JCheckBox checkBox = new JCheckBox();
                checkBox.addActionListener(e -> {
                    if (checkBox.isSelected()) {
                        selectedLocations.add(location);
                    }
                    else {
                        selectedLocations.remove(location);
                    }
                });
                locationPanel.add(checkBox);
                locationPanel.add(new JLabel(location.getName()));
                locationPanel.add(new JLabel(location.getAddress()));
                locationPanel.add(Box.createVerticalStrut(10));
                suggestedLocationsPanel.add(locationPanel);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "No suggested locations available.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        suggestedLocationsPanel.revalidate();
        suggestedLocationsPanel.repaint();
        this.setPreferredSize(new Dimension(800, 1200));

    }

    public List<Place> getSelectedLocations() {
        return selectedLocations;
    }

    public String getViewName() {
        return viewName;
    }
}
