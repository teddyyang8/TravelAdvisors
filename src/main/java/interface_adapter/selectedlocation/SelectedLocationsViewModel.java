package interface_adapter.selectedlocation;

import interface_adapter.ViewModel;
import entity.Place;

import java.util.List;

/**
 * The ViewModel for the SelectedLocationsView.
 */
public class SelectedLocationsViewModel extends ViewModel<SelectedLocationsState> {
    public SelectedLocationsViewModel() {
        super("Selected Locations");
        setState(new SelectedLocationsState());
    }

}
