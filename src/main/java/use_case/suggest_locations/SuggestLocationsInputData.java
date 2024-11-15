package use_case.suggest_locations;

/**
 * The input data for the Suggest Locations Use Case.
 */
public class SuggestLocationsInputData {

    private final String address;
    private final String locationType;

    public SuggestLocationsInputData(String address, String locationType) {
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
