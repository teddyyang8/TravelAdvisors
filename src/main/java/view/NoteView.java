package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import interface_adapter.note.NoteController;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;

/**
 * The View for when the user is viewing a note in the program.
 */
public class NoteView extends JPanel implements ActionListener, PropertyChangeListener {

    private static final int TEXT_FIELD_WIDTH = 30;
    private static final int KEYWORD_FIELD_WIDTH = 10;
    private final NoteViewModel noteViewModel;
    private final JLabel noteName = new JLabel("Home Screen");
    private final JTextField cityField;
    private final JTextField addressField;
    private final JTextField keyword1Field;
    private final JTextField keyword2Field;
    private final JTextField keyword3Field;
    private final JTextField keyword4Field;
    private final JTextField keyword5Field;
    private JPanel suggestedLocations;
    private final JButton suggestButton = new JButton("Suggest Locations");
    private NoteController noteController;

    public NoteView(NoteViewModel noteViewModel) {
        cityField = new JTextField(TEXT_FIELD_WIDTH);
        addressField = new JTextField(TEXT_FIELD_WIDTH);
        keyword1Field = new JTextField(KEYWORD_FIELD_WIDTH);
        keyword2Field = new JTextField(KEYWORD_FIELD_WIDTH);
        keyword3Field = new JTextField(KEYWORD_FIELD_WIDTH);
        keyword4Field = new JTextField(KEYWORD_FIELD_WIDTH);
        keyword5Field = new JTextField(KEYWORD_FIELD_WIDTH);
        noteName.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.noteViewModel = noteViewModel;
        this.noteViewModel.addPropertyChangeListener(this);

        final JPanel panel = new JPanel();
        panel.add(new JLabel("Choose City:"));
        panel.add(cityField);
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
                final String city = cityField.getText().trim();
                final String address = addressField.getText().trim();
                final List<String> keywords = getKeywords();
                final String joinedKeywords = String.join(";", keywords);
                final String inputText = String.join(";", city, address, joinedKeywords);
                noteController.execute(inputText);
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(noteName);
        this.add(panel);
        this.setVisible(true);
    }

    public void setNoteController(NoteController controller) {
        this.noteController = controller;
    }

    public String getCity() {
        return cityField.getText();
    }

    public String getAddress() {
        return addressField.getText();
    }

    public List<String> getKeywords() {
        final List<String> keywords = new ArrayList<>();

        final String keyword1 = keyword1Field.getText().trim();
        if (!keyword1.isEmpty()) {
            keywords.add(keyword1);
        }
        final String keyword2 = keyword2Field.getText().trim();
        if (!keyword2.isEmpty()) {
            keywords.add(keyword2);
        }
        final String keyword3 = keyword3Field.getText().trim();
        if (!keyword3.isEmpty()) {
            keywords.add(keyword3);
        }
        final String keyword4 = keyword4Field.getText().trim();
        if (!keyword4.isEmpty()) {
            keywords.add(keyword4);
        }
        final String keyword5 = keyword5Field.getText().trim();
        if (!keyword5.isEmpty()) {
            keywords.add(keyword5);
        }
        return keywords;
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
        final NoteState state = (NoteState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (state.getSuggestedLocations() != null) {
            showSuggestedLocations(state.getSuggestedLocations());
        }
    }

    private void showSuggestedLocations(List<String> locations) {
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
    }

    private void setFields(NoteState state) {
        cityField.setText(state.getCity());
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

