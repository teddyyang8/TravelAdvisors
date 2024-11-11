package app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import interface_adapter.location.LocationController;
import interface_adapter.location.LocationPresenter;
import interface_adapter.location.LocationViewModel;
import use_case.note.NoteDataAccessInterface;
import use_case.note.NoteInteractor;
import use_case.note.NoteOutputBoundary;
import view.LocationView;

/**
 * Builder for the Note Application.
 */
public class NoteAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;
    private NoteDataAccessInterface noteDAO;
    private LocationViewModel locationViewModel = new LocationViewModel();
    private LocationView noteView;
    private NoteInteractor noteInteractor;

    /**
     * Sets the NoteDAO to be used in this application.
     * @param noteDataAccess the DAO to use
     * @return this builder
     */
    public NoteAppBuilder addNoteDAO(NoteDataAccessInterface noteDataAccess) {
        noteDAO = noteDataAccess;
        return this;
    }

    /**
     * Creates the objects for the Note Use Case and connects the NoteView to its
     * controller.
     * <p>This method must be called after addNoteView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addNoteView
     */
    public NoteAppBuilder addNoteUseCase() {
        final NoteOutputBoundary noteOutputBoundary = new LocationPresenter(locationViewModel);
        noteInteractor = new NoteInteractor(
                noteDAO, noteOutputBoundary);

        final LocationController controller = new LocationController(noteInteractor);
        if (noteView == null) {
            throw new RuntimeException("addNoteView must be called before addNoteUseCase");
        }
        noteView.setNoteController(controller);
        return this;
    }

    /**
     * Creates the NoteView and underlying NoteViewModel.
     * @return this builder
     */
    public NoteAppBuilder addNoteView() {
        locationViewModel = new LocationViewModel();
        noteView = new LocationView(locationViewModel);
        return this;
    }

    /**
     * Builds the application.
     * @return the JFrame for the application
     */
    public JFrame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Note Application");
        frame.setSize(WIDTH, HEIGHT);

        frame.add(noteView);

        // refresh so that the note will be visible when we start the program
        noteInteractor.executeRefresh();

        return frame;

    }
}
