package entity;

/**
 * The representation of a place/destination for our program.
 */
public class Place {

    private final String name;
    private final String address;

    public Place(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
