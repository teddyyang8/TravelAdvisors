package app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;


/**
 * Builder for the Suggested Locations Application.
 */
public class SuggestedLocationsApplication {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;


    public JFrame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Suggested Locations Application");
        frame.setSize(WIDTH, HEIGHT);

        frame.add(noteView);

        // refresh so that the note will be visible when we start the program
        noteInteractor.executeRefresh();

        return frame;

    }
}


