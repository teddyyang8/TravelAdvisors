package interface_adapter.selectedlocation;


import use_case.selected_locations.SelectedLocationsOutputBoundary;
import use_case.selected_locations.SelectedLocationsOutputData;

public class SelectedLocationsPresenter implements SelectedLocationsOutputBoundary {


    @Override
    public void prepareSuccessView(SelectedLocationsOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
