package interface_adapter.suggestlocation;

import use_case.suggest_locations.SuggestLocationsOutputBoundary;
import use_case.suggest_locations.SuggestLocationsOutputData;
import view.SuggestedLocationsView;

/**
 * The presenter for the suggested locations use case.
 */
public class SuggestedLocationsPresenter implements SuggestLocationsOutputBoundary {

    private SuggestedLocationsView view;
    private SuggestedLocationsViewModel viewModel;

    public SuggestedLocationsPresenter(SuggestedLocationsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setView(SuggestedLocationsView view) {
        this.view = view;
    }

}
