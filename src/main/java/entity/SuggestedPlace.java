
package entity;

/**
 * An implementation of the Place interface.
 */
public class SuggestedPlace implements Place {

    private final String name;
    private final String address;

    public SuggestedPlace(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }
}