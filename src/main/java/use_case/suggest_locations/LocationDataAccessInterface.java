package use_case.suggest_locations;

/**
 * The interface of the DAO for the Suggest Locations Use Case.
 */
public interface LocationDataAccessInterface {
    /**
     * Display suggested locations and info.
     * @param address the starting address.
     * @param locationType the location type to search for.
     * @return String of the suggested locations.
     */
    String searchLocation(String address, String locationType);
}
