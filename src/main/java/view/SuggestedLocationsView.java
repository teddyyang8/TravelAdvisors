package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import entity.Place;
import interface_adapter.selectedlocation.SelectedLocationsController;
import interface_adapter.selectedlocation.SelectedLocationsState;
import interface_adapter.selectedlocation.SelectedLocationsViewModel;
import interface_adapter.add_to_calendar.AddToCalendarController;
import interface_adapter.add_to_calendar.AddToCalendarState;
import interface_adapter.add_to_calendar.AddToCalendarViewModel;
import interface_adapter.location.LocationController;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;
import interface_adapter.suggestlocation.SuggestedLocationsController;
import interface_adapter.suggestlocation.SuggestedLocationsState;
import interface_adapter.suggestlocation.SuggestedLocationsViewModel;
import use_case.DataAccessException;

/**
 * The View for when the user receives a list of suggested locations in the program.
 */
public class SuggestedLocationsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Suggested Locations";
//    private final LocationViewModel locationViewModel;
//    private final LocationController locationController;
    private final SuggestedLocationsViewModel suggestedLocationsViewModel;
    private final SuggestedLocationsController suggestedLocationsController;
    private final SelectedLocationsController selectedLocationsController;
    private final SelectedLocationsViewModel selectedLocationsViewModel;
    private final AddToCalendarViewModel calendarViewModel;
    private final AddToCalendarController calendarController;

    private final JPanel suggestedLocationsPanel;
    private final JButton saveSelectionButton;
    private final JButton newSearchButton;
    private final JButton saveToCalendarButton;

    private final List<Place> selectedLocations;
    private final Map<Place, String> calendarLocations;

    private final int numLocationsDisplayed = 5;
    private final int viewWidth = 800;
    private final int viewHeight = 1200;

    public SuggestedLocationsView(SuggestedLocationsViewModel suggestedLocationsViewModel,
                                  SuggestedLocationsController suggestedLocationsController,
                                  AddToCalendarViewModel calendarViewModel,
                                  AddToCalendarController calendarController,
                                  SelectedLocationsViewModel selectedLocationsViewModel,
                                  SelectedLocationsController selectedLocationsController) {

        this.suggestedLocationsViewModel = suggestedLocationsViewModel;
        this.suggestedLocationsViewModel.addPropertyChangeListener(this);
        this.suggestedLocationsController = suggestedLocationsController;
        this.selectedLocationsController = selectedLocationsController;
        this.selectedLocationsViewModel = selectedLocationsViewModel;
        this.calendarViewModel = calendarViewModel;
        this.calendarController = calendarController;

        this.selectedLocations = new ArrayList<>();
        this.calendarLocations = new HashMap<>();

        final JLabel title = new JLabel("List of Suggested Locations:");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.suggestedLocationsPanel = new JPanel();
        this.suggestedLocationsPanel.setLayout(new BoxLayout(suggestedLocationsPanel, BoxLayout.Y_AXIS));

        this.saveSelectionButton = new JButton("Save Selection");
        saveSelectionButton.addActionListener(this);
        saveSelectionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.newSearchButton = new JButton("New Search");
        newSearchButton.addActionListener(this);
        newSearchButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.saveToCalendarButton = new JButton("Save to Calendar");
        saveToCalendarButton.addActionListener(this);
        saveToCalendarButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(suggestedLocationsPanel);
        this.add(saveSelectionButton);
        this.add(newSearchButton);
        this.add(saveToCalendarButton);

        updateSuggestedLocations(suggestedLocationsViewModel.getState());
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
            for (int i = 0; i < Math.min(numLocationsDisplayed, suggestedLocations.size()); i++) {
                final Place location = suggestedLocations.get(i);
                final JPanel locationPanel = new JPanel();
                locationPanel.setLayout(new FlowLayout());
                final JCheckBox checkBox = new JCheckBox();
                checkBox.addActionListener(e -> {
                    if (checkBox.isSelected()) {
                        selectedLocations.add(location);
                    }
                    else {
                        selectedLocations.remove(location);
                    }
                });
                // array of string containing cities
                final String[] times = new String[]{"None", "7:00AM", "8:00AM", "9:00AM", "10:00AM", "11:00AM",
                    "12:00PM", "1:00PM", "2:00PM", "3:00PM", "4:00PM", "5:00PM", "6:00PM", "7:00PM", "8:00PM",
                    "9:00PM", "10:00PM"};
                // create checkbox
                final JComboBox<String> timeSelection = new JComboBox<>(times);
                // add ItemListener
                timeSelection.addItemListener(e -> {
                    // if the state combobox is changed
                    if (e.getSource() == timeSelection) {
                        if (timeSelection.getSelectedItem().equals("None")) {
                            calendarLocations.remove(location);
                        }
                        else {
                            calendarLocations.put(location, timeSelection.getSelectedItem().toString());
                        }
                    }
                });
                locationPanel.add(checkBox);
                locationPanel.add(new JLabel(location.getName()));
                locationPanel.add(new JLabel(location.getAddress()));
                locationPanel.add(Box.createVerticalStrut(numLocationsDisplayed));
                locationPanel.add(timeSelection);
                locationPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                suggestedLocationsPanel.add(locationPanel);
            }
            final SelectedLocationsState selectedLocationsState = selectedLocationsViewModel.getState();
            selectedLocationsState.setSelectedLocations(selectedLocations);
            selectedLocationsViewModel.setState(selectedLocationsState);

            final AddToCalendarState currentState = calendarViewModel.getState();
            currentState.setCalendarItems(calendarLocations);
            calendarViewModel.setState(currentState);
        }
        else {
            JOptionPane.showMessageDialog(this, "No suggested locations available.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        suggestedLocationsPanel.revalidate();
        suggestedLocationsPanel.repaint();
        this.setPreferredSize(new Dimension(viewWidth, viewHeight));
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == saveSelectionButton) {
            final SelectedLocationsState selectedLocationsState = selectedLocationsViewModel.getState();
            try {
                selectedLocationsController.execute(selectedLocationsState.getSelectedLocations());
            }
            catch (DataAccessException e) {
                throw new RuntimeException();
            }
        }
        if (evt.getSource().equals(newSearchButton)) {
//            final LocationState currentState = locationViewModel.getState();
//            try {
//                locationController.execute(currentState.getAddress(), currentState.getLocationType());
        }
        if (evt.getSource().equals(saveToCalendarButton)) {
            final AddToCalendarState currentState = calendarViewModel.getState();
            try {
                calendarController.execute(currentState.getCalendarItems());
            }
            catch (DataAccessException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public String getViewName() {
        return viewName;
    }
}
