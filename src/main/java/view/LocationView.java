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

/**
 * The View for when the user is viewing a location in the program.
 */
public class LocationView extends JPanel implements ActionListener, PropertyChangeListener {

    private final LocationViewModel locationViewModel;
    private final LocationController locationController;

    private final JLabel addressLabel = new JLabel("Address");

    private final JTextField addressField = new JTextField(20);

    private final JLabel locationTypeLabel = new JLabel("Location Type");

    private final JTextField locationTypeField = new JTextField(20);

    private final JButton suggestLocationsButton;

    public LocationView(LocationViewModel locationViewModel, LocationController locationController) {
        this.locationViewModel = locationViewModel;
        this.locationViewModel.addPropertyChangeListener(this);
        this.locationController = locationController;

        final JLabel title = new JLabel("Suggest Locations");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel addressInfo = new LabelTextPanel(
                new JLabel("Address"), addressField);

        final LabelTextPanel locationTypeInfo = new LabelTextPanel(
                new JLabel("Location Type"), locationTypeField);

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

        locationTypeField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LocationState currentState = locationViewModel.getState();
                currentState.setLocationType(locationTypeField.getText());
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

        suggestLocationsButton.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(suggestLocationsButton)) {
                        final LocationState currentState = locationViewModel.getState();

                        this.locationController.execute(
                                currentState.getAddress(),
                                currentState.getLocationType()
                        );
                    }
                }
        );

        this.add(title);
        this.add(addressInfo);
        this.add(addressLabel);
        this.add(locationTypeInfo);
        this.add(locationTypeLabel);
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
        locationTypeField.setText(state.getLocationType());
    }
}

