package interface_adapter.selectedlocation;


import interface_adapter.ViewManagerModel;
import interface_adapter.suggestlocation.SuggestedLocationsState;
import use_case.selected_locations.SelectedLocationsOutputBoundary;
import use_case.selected_locations.SelectedLocationsOutputData;

public class SelectedLocationsPresenter implements SelectedLocationsOutputBoundary {
    private final SelectedLocationsViewModel selectedLocationsViewModel;
    private final ViewManagerModel viewManagerModel;

    public SelectedLocationsPresenter(SelectedLocationsViewModel selectedLocationsViewModel,
                                      ViewManagerModel viewManagerModel) {
        this.selectedLocationsViewModel = selectedLocationsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SelectedLocationsOutputData outputData) {
        final SelectedLocationsState selectedLocationsState =
                selectedLocationsViewModel.getState();
        selectedLocationsState.setPlaceToCoordinates(outputData.getLocationCoordinatesMap());
        this.selectedLocationsViewModel.setState(selectedLocationsState);
        this.selectedLocationsViewModel.firePropertyChanged();
        this.viewManagerModel.setState(selectedLocationsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
