package app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class HomeScreenApplication {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;
//    private LocationViewModel locationViewModel = new LocationViewModel();

    public JFrame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Home Screen Application");
        frame.setSize(WIDTH, HEIGHT);

//        frame.add(noteView);

//        noteInteractor.executeRefresh();

        return frame;

    }

}
