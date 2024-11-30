package interface_adapter.location;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the LocationView.
 */
public class LocationViewModel extends ViewModel<LocationState> {
    public LocationViewModel() {
        super("Search Locations");
        setState(new LocationState());
    }
}
