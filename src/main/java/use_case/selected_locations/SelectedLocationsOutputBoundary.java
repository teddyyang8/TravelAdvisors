package use_case.selected_locations;

/**
 * The output boundary for the Selected Locations Use Case
 */
public interface SelectedLocationsOutputBoundary {

    /**
     * Prepare the success view
     * @param outputData The output data
     */
    void prepareSuccessView(SelectedLocationsOutputData outputData);

    /**
     * Prepare the failure view.
     * @param errorMessage The explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
