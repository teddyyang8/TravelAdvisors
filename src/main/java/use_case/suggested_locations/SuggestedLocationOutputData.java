package use_case.suggested_locations;

/**
 * The output data for the Suggested Location Use Case.
 */
public class SuggestedLocationOutputData {

    private final String suggestedLocation;

    public SuggestedLocationOutputData(String suggestedLocation) {
        this.suggestedLocation = suggestedLocation;
    }

    public String getSuggestedLocation() {
        return suggestedLocation;
    }
}
