package interface_adapter.suggestlocation;

/**
 * The ViewModel for the SuggestedLocationsView.
 */
public class SuggestedLocationsViewModel {
    private SuggestedLocationsState state;

    public SuggestedLocationsViewModel() {
        this.state = new SuggestedLocationsState();
    }

    public SuggestedLocationsState getState() {
        return state;
    }

    public void setState(SuggestedLocationsState state) {
        this.state = state;
    }
}
