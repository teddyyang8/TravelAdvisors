package app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Builder for the Home Screen Application.
 */
public class HomeScreenApplication {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;


    public JFrame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Home Screen Application");
        frame.setSize(WIDTH, HEIGHT);

        frame.add(noteView);

        // refresh so that the note will be visible when we start the program
        noteInteractor.executeRefresh();

        return frame;

    }
}


