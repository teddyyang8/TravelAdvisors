package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.location.LocationController;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;
import use_case.DataAccessException;

/**
 * The View for when the user is viewing a location in the program.
 */
public class LocationView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String locationViewName = "Search Locations";
    private final LocationViewModel locationViewModel;
    private final LocationController locationController;

    private final JTextField addressField = new JTextField(20);
    private final int five = 5;
    private final JTextField[] locationTypeFields = new JTextField[five];

    private final JButton suggestLocationsButton;

    private final JComboBox<Object> filtersDropDown;
    private String currentFilter = "";

    public LocationView(LocationViewModel locationViewModel, LocationController locationController) {
        this.locationViewModel = locationViewModel;
        this.locationViewModel.addPropertyChangeListener(this);
        this.locationController = locationController;
        final JLabel title = new JLabel("Suggest Locations");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel addressInfo = new LabelTextPanel(
                new JLabel("Address"), addressField);

        final JPanel locationTypePanel = new JPanel();
        locationTypePanel.setLayout(new BoxLayout(locationTypePanel, BoxLayout.Y_AXIS));
        final int twenty = 20;
        for (int i = 0; i < locationTypeFields.length; i++) {
            locationTypeFields[i] = new JTextField(twenty);
            locationTypePanel.add(new LabelTextPanel(new JLabel("Location Type " + (i + 1)), locationTypeFields[i]));
        }

        final JPanel buttons = new JPanel();
        suggestLocationsButton = new JButton("Suggest Locations");
        buttons.add(suggestLocationsButton);
        final JLabel filterLabel = new JLabel("Filters:");
        final String[] filters = {"None", "Remove Disliked Locations", "Remove Saved Locations"};
        filtersDropDown = new JComboBox<>(filters);
        buttons.add(filterLabel);
        buttons.add(filtersDropDown);
        suggestLocationsButton.addActionListener(this);
        filtersDropDown.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(addressInfo);
        this.add(locationTypePanel);
        this.add(buttons);

        addressField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LocationState currentState = locationViewModel.getState();
                currentState.setAddress(addressField.getText());
                locationViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        for (JTextField locationTypeField : locationTypeFields) {
            locationTypeField.getDocument().addDocumentListener(new DocumentListener() {

                private void documentListenerHelper() {
                    final LocationState currentState = locationViewModel.getState();
                    // make into one string
                    final StringBuilder locationTypes = new StringBuilder();
                    for (JTextField field : locationTypeFields) {
                        if (!field.getText().isEmpty()) {
                            if (locationTypes.length() > 0) {
                                locationTypes.append(", ");
                            }
                            locationTypes.append(field.getText());
                        }
                    }
                    currentState.setLocationType(locationTypes.toString());
                    locationViewModel.setState(currentState);
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    documentListenerHelper();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    documentListenerHelper();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    documentListenerHelper();
                }
            });
        }

        suggestLocationsButton.addActionListener(evt -> {
            if (evt.getSource().equals(suggestLocationsButton)) {
                currentFilter = filtersDropDown.getSelectedItem().toString();
                final LocationState currentState = locationViewModel.getState();
                try {
                    locationController.execute(
                            currentState.getAddress(), currentState.getLocationType(), currentFilter);
                }
                catch (DataAccessException err) {
                    throw new RuntimeException(err);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(suggestLocationsButton)) {
            final LocationState currentState = locationViewModel.getState();
            try {
                locationController.execute(currentState.getAddress(), currentState.getLocationType(), currentFilter);
            }
            catch (DataAccessException error) {
                throw new RuntimeException(error);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LocationState state = (LocationState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFields(LocationState state) {
        addressField.setText(state.getAddress());
        final String[] locationTypes = state.getLocationType().split(", ");
        for (int i = 0; i < locationTypeFields.length; i++) {
            if (i < locationTypes.length) {
                locationTypeFields[i].setText(locationTypes[i]);
            }
            else {
                locationTypeFields[i].setText("");
            }
        }
    }

    public String getViewName() {
        return locationViewName;
    }
}

