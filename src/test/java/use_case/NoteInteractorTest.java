package use_case;

import entity.User;
import org.junit.Test;
import use_case.note.NoteDataAccessInterface;
import use_case.note.NoteInteractor;
import use_case.note.NoteOutputBoundary;

import static org.junit.Assert.*;

public class NoteInteractorTest {

    @Test
    public void testExecuteRefreshSuccess() {

        NoteDataAccessInterface noteDAO = new NoteDataAccessInterface() {


            @Override
            public String saveNote(User user, String note) {
                return "";
            }


            @Override
            public String loadNote(User user) {
                return "test";
            }
        };

        NoteOutputBoundary noteOB = new NoteOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                assertEquals("test", message);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }
        };

        NoteInteractor noteInteractor = new NoteInteractor(noteDAO, noteOB);

        noteInteractor.executeRefresh();


    }
}