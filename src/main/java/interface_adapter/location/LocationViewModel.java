package interface_adapter.location;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the NoteView.
 */
public class LocationViewModel extends ViewModel<LocationState> {
    public LocationViewModel() {
        super("note");
        setState(new LocationState());
    }
}