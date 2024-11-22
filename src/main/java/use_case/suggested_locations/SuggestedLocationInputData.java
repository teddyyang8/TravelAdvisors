package use_case.suggested_locations;

/**
 * The input data for the Suggested Locations Use Case.
 */
public class SuggestedLocationInputData {

    private final String address;
    private final String locationType;

    public SuggestedLocationInputData(String address, String locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public String getAddress() {
        return address;
    }

    public String getLocationType() {
        return locationType;
    }
}
