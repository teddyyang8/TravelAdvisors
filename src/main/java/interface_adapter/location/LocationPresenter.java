package interface_adapter.location;

import use_case.note.NoteOutputBoundary;

/**
 * The presenter for our Note viewing and editing program.
 */
public class LocationPresenter implements NoteOutputBoundary {

    private final LocationViewModel locationViewModel;

    public LocationPresenter(LocationViewModel locationViewModel) {
        this.locationViewModel = locationViewModel;
    }

    /**
     * Prepares the success view for the Note related Use Cases.
     *
     * @param note the output data
     */
    @Override
    public void prepareSuccessView(String note) {
        locationViewModel.getState().setNote(note);
        locationViewModel.getState().setError(null);
        locationViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        locationViewModel.getState().setError(errorMessage);
        locationViewModel.firePropertyChanged();
    }
}
