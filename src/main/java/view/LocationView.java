package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.location.LocationController;
import interface_adapter.location.LocationState;
import interface_adapter.location.LocationViewModel;
import use_case.suggest_locations.DataAccessException;

/**
 * The View for when the user is viewing a location in the program.
 */
public class LocationView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String locationViewName = "Search Locations";
    private final LocationViewModel locationViewModel;
    private final LocationController locationController;

    private final JTextField addressField = new JTextField(20);

    private final JTextField[] locationTypeFields = new JTextField[5];

    private final JButton suggestLocationsButton;

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
        for (int i = 0; i < locationTypeFields.length; i++) {
            locationTypeFields[i] = new JTextField(20);
            locationTypePanel.add(new LabelTextPanel(new JLabel("Location Type " + (i + 1)), locationTypeFields[i]));
        }

        final JPanel buttons = new JPanel();
        suggestLocationsButton = new JButton("Suggest Locations");
        buttons.add(suggestLocationsButton);

        suggestLocationsButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

        suggestLocationsButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(suggestLocationsButton)) {
                        final LocationState currentState = locationViewModel.getState();

                        try {
                            this.locationController.execute(
                                    currentState.getAddress(),
                                    currentState.getLocationType()
                            );
                        }
                        catch (DataAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

        this.add(title);
        this.add(addressInfo);
        this.add(locationTypePanel);
        this.add(buttons);

    }

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

