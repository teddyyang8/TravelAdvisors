package interface_adapter.location;

import java.util.List;

import entity.Place;
import interface_adapter.ViewModel;

/**
 * The ViewModel for the LocationView.
 */
public class LocationViewModel extends ViewModel<LocationState> {
    public LocationViewModel() {
        super("location");
        setState(new LocationState());
    }

    /**
     * Updates the suggested locations.
     * @param suggestedLocations the list of suggested locations
     */
    public void updateSuggestedLocations(List<Place> suggestedLocations) {
        getState().setSuggestedLocations(suggestedLocations);
        firePropertyChanged("suggestLocations");
    }

}
