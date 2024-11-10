package use_case.suggest_locations;

/**
 * Output Data for the Suggest Locations Use Case.
 */
public class SuggestLocationsOutputData {

    private final String locations;
    private final boolean useCaseFailed;

    public SuggestLocationsOutputData(String locations, boolean useCaseFailed) {
        this.locations = locations;
        this.useCaseFailed = useCaseFailed;
    }

    public String getLocations() {
        return locations;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
