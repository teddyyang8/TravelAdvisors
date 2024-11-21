package interface_adapter.suggestlocation;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the SuggestedLocationsView.
 */
public class SuggestedLocationsViewModel extends ViewModel<SuggestedLocationsState> {
    public SuggestedLocationsViewModel() {
        super("suggestedLocations");
        setState(new SuggestedLocationsState());
    }
}
