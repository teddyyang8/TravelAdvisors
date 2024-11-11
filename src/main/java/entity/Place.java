package entity;

/**
 * The representation of a place in our program.
 */
public class Place {
    private final String name;
    private final String address;

    /**
     * The representation of a place.
     *
     * @param name the name of the place
     * @param address the address of the place
     */
    public Place(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Returns the address of the place.
     *
     * @return the address of the place.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the address of the place.
     *
     * @return the address of the place.
     */
    public String getAddress() {
        return address;

    }
}
