package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.Place;
import interface_adapter.suggest_locations.SuggestLocationsController;
import interface_adapter.suggest_locations.SuggestLocationsState;
import interface_adapter.suggest_locations.SuggestLocationsViewModel;

/**
 * The View for when the user receives a list of suggested locations in the program.
 */
public class SuggestedLocationsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final SuggestLocationsViewModel suggestLocationsViewModel;
    private final SuggestLocationsController suggestLocationsController;
    private final ViewManager viewManager;

    private final JPanel suggestedLocationsPanel;
    private final JButton newSearchButton;

    public SuggestedLocationsView(SuggestLocationsViewModel suggestLocationsViewModel,
                                  SuggestLocationsController suggestLocationsController,
                                  ViewManager viewManager) {
        this.suggestLocationsViewModel = suggestLocationsViewModel;
        this.suggestLocationsViewModel.addPropertyChangeListener(this);
        this.suggestLocationsController = suggestLocationsController;
        this.viewManager = viewManager;

        final JLabel title = new JLabel("List of Suggested Locations:");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.suggestedLocationsPanel = new JPanel();
        this.suggestedLocationsPanel.setLayout(new BoxLayout(suggestedLocationsPanel, BoxLayout.Y_AXIS));

        this.newSearchButton = new JButton("New Search");
        newSearchButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(suggestedLocationsPanel);
        this.add(newSearchButton);

        updateSuggestedLocations(suggestLocationsViewModel.getState());
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(newSearchButton)) {
            viewManager.showLocationView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateSuggestedLocations((SuggestLocationsState) evt.getNewValue());
        }
    }

    private void updateSuggestedLocations(SuggestLocationsState state) {
        suggestedLocationsPanel.removeAll();
        for (Place location : state.getSuggestedLocations()) {
            suggestedLocationsPanel.add(new JLabel(location.getName()));
            suggestedLocationsPanel.add(new JLabel(location.getAddress()));
        }
    }
}
