package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import entity.Place;
import interface_adapter.add_to_calendar.AddToCalendarController;
import interface_adapter.add_to_calendar.AddToCalendarState;
import interface_adapter.add_to_calendar.AddToCalendarViewModel;
import use_case.DataAccessException;

/**
 * The View for when the user is viewing their calendar.
 */
public class CalendarView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Calendar";
    private final AddToCalendarViewModel calendarViewModel;
    private final AddToCalendarController addToCalendarController;

    private final JPanel calendarPanel;
    private final JButton newSearchButton;

    private String[] calendarTimes = new String[]{"7:00AM", "8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00PM",
        "1:00PM", "2:00PM", "3:00PM", "4:00PM", "5:00PM", "6:00PM", "7:00PM", "8:00PM", "9:00PM", "10:00PM"};

    private final int viewWidth = 800;
    private final int viewHeight = 1200;

    public CalendarView(AddToCalendarViewModel calendarViewModel, AddToCalendarController addToCalendarController) {
        this.calendarViewModel = calendarViewModel;
        this.calendarViewModel.addPropertyChangeListener(this);
        this.addToCalendarController = addToCalendarController;

        this.setLayout(new BorderLayout());

        final JLabel title = new JLabel("Calendar");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);
        this.add(titlePanel, BorderLayout.NORTH);

        this.calendarPanel = new JPanel();
        this.calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
        this.add(calendarPanel, BorderLayout.WEST);

        this.newSearchButton = new JButton("New Search");
        newSearchButton.addActionListener(this);
        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(newSearchButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        System.out.println(calendarViewModel.getState().getCalendarItems());
        updateCalendarView(calendarViewModel.getState());
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(newSearchButton)) {
            addToCalendarController.switchToLocationView();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            System.out.println("update (calendar view)");
            updateCalendarView((AddToCalendarState) evt.getNewValue());
        }
    }

    private void updateCalendarView(AddToCalendarState state) {
        calendarPanel.removeAll();
        final Map<Place, String> calendarItems = state.getCalendarItems();
        System.out.println(calendarTimes.length);
        for (int i = 0; i < calendarTimes.length; i++) {
            final JPanel timePanel = new JPanel();
            final JTextField timeField = new JTextField(calendarTimes[i]);
            timePanel.add(timeField);
            for (Map.Entry<Place, String> entry : calendarItems.entrySet()) {
                if (entry.getValue().equals(calendarTimes[i])) {
                    final JTextField locationField = new JTextField(entry.getKey().getName());
                    final JTextField addressField = new JTextField(entry.getKey().getAddress());
                    timePanel.add(locationField, BorderLayout.EAST);
                    timePanel.add(addressField, BorderLayout.EAST);
                }
            }
//                timePanel.add(Box.createVerticalStrut(calendarTimes.length));
//                timePanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            calendarPanel.add(timePanel, BorderLayout.WEST);
        }
        calendarPanel.revalidate();
        calendarPanel.repaint();
        this.setPreferredSize(new Dimension(viewWidth, viewHeight));
    }

    public String getViewName() {
        return viewName;
    }
}
