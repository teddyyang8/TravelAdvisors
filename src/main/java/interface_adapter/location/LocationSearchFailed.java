package interface_adapter.location;

public class LocationSearchFailed extends RuntimeException {
    public LocationSearchFailed(String error) {
        super(error);
    }
}
