package interface_adapter.location;

/**
 * The state representing location-related data, including city, address, keywords,
 * suggested locations, and any error messages.
 */
public class LocationState {
    private String address = "";
    private String locationType = "";
    private String username = "";
    private String error;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

