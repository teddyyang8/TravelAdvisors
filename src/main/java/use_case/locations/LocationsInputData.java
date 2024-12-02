package use_case.locations;

/**
 * The input data for the Suggest Locations Use Case.
 */
public class LocationsInputData {

    private final String address;
    private final String locationType;
    private final String username;

    public LocationsInputData(String address, String locationType, String username) {
        this.address = address;
        this.locationType = locationType;
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getUsername() {
        return username;
    }

}
