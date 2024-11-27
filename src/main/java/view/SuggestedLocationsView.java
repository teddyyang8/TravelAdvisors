package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
        System.out.println(suggestedLocations.size());
        if (suggestedLocations != null) {
            for (int i = 0; i < Math.min(10, suggestedLocations.size()); i++) {
                final Place location = suggestedLocations.get(i);
                suggestedLocationsPanel.add(new JLabel(location.getName()));
                suggestedLocationsPanel.add(new JLabel(location.getAddress()));
            }
        } else {
            JOptionPane.showMessageDialog(this, "No suggested locations available.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        suggestedLocationsPanel.revalidate();
        suggestedLocationsPanel.repaint();
    }

    public String getViewName() {
        return viewName;
    }
}
