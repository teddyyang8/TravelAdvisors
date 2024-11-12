package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import entity.Place;
import interface_adapter.location.LocationController;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;
import use_case.suggest_locations.DataAccessException;

/**
 * The View for when the user is viewing a location in the program.
 */
public class LocationView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final int TEXT_FIELD_WIDTH = 30;
    private static final int KEYWORD_FIELD_WIDTH = 10;
    private final LocationViewModel locationViewModel;
    private final JLabel locationName = new JLabel("Home Screen");
    private final JTextField addressField;
    private final JTextField keyword1Field;
    private final JTextField keyword2Field;
    private final JTextField keyword3Field;
    private final JTextField keyword4Field;
    private final JTextField keyword5Field;
    private JPanel suggestedLocations;
    private final JButton suggestButton = new JButton("Suggest Locations");
    private LocationController locationController;

    public LocationView(LocationViewModel locationViewModel) {
        addressField = new JTextField(TEXT_FIELD_WIDTH);
        keyword1Field = new JTextField(KEYWORD_FIELD_WIDTH);
        keyword2Field = new JTextField(KEYWORD_FIELD_WIDTH);
        keyword3Field = new JTextField(KEYWORD_FIELD_WIDTH);
        keyword4Field = new JTextField(KEYWORD_FIELD_WIDTH);
        keyword5Field = new JTextField(KEYWORD_FIELD_WIDTH);
        locationName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.locationViewModel = locationViewModel;
        this.locationViewModel.addPropertyChangeListener(this);

        final JPanel panel = new JPanel();
        panel.add(new JLabel("Choose City:"));
        panel.add(new JLabel("Enter Address:"));
        panel.add(addressField);
        panel.add(new JLabel("Choose Interests:"));
        panel.add(keyword1Field);
        panel.add(keyword2Field);
        panel.add(keyword3Field);
        panel.add(keyword4Field);
        panel.add(keyword5Field);
        panel.add(suggestButton);

        suggestButton.addActionListener(evt -> {
            if (evt.getSource().equals(suggestButton)) {
                final LocationState currentState = locationViewModel.getState();
                try {
                    locationController.execute(currentState.getInputData());
                }
                catch (DataAccessException dataAccessException) {
                    JOptionPane.showMessageDialog(this, "Error accessing data: " + dataAccessException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(locationName);
        this.add(panel);
        this.setVisible(true);
    }

    public void setLocationController(LocationController controller) {
        this.locationController = controller;
    }

    public String getAddress() {
        return addressField.getText();
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LocationState state = (LocationState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
//        if (state.getSuggestedLocations() != null) {
//            showSuggestedLocations(state.getSuggestedLocations());
//        }
    }

//    private void showSuggestedLocations(List<Place> locations) {
//        if (suggestedLocations != null) {
//            this.remove(suggestedLocations);
//        }
//
//        suggestedLocations = new JPanel();
//        suggestedLocations.setLayout(new BoxLayout(suggestedLocations, BoxLayout.Y_AXIS));
//        suggestedLocations.add(new JLabel("List of Suggested Locations:"));
//        for (Place location : locations) {
//            suggestedLocations.add(new JLabel(location));
//        }
//        this.add(suggestedLocations);
//    }

    private void setFields(LocationState state) {
        addressField.setText(state.getAddress());
        keyword1Field.setText(state.getKeyword1());
        keyword2Field.setText(state.getKeyword2());
        keyword3Field.setText(state.getKeyword3());
        keyword4Field.setText(state.getKeyword4());
        keyword5Field.setText(state.getKeyword5());
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

