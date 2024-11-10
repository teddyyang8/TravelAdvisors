package entity;

/**
 * Factory for creating places.
 */
public interface PlaceFactory {
    /**
     * Creates a new Place.
     * @param name the name of the new place
     * @param address the address of the new place
     * @return the new place
     */
    Place create(String name, String address);
}
