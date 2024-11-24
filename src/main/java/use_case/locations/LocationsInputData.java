package use_case.locations;

/**
 * The input data for the Suggest Locations Use Case.
 */
public class LocationsInputData {

    private final String address;
    private final String locationType;

    public LocationsInputData(String address, String locationType) {
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
