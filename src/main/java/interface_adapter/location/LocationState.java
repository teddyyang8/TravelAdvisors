package interface_adapter.location;

/**
 * The State for a note.
 * <p>For this example, a note is simplay a string.</p>
 */
public class LocationState {
    private String note = "";
    private String error;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
