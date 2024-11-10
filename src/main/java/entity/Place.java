package entity;

/**
 * The representation of a place in our program.
 */
public interface Place {
    /**
     * Returns the name of the place.
     * @return the name of the place.
     */
    String getName();

    /**
     * Returns the address of the place.
     * @return the address of the place.
     */
    String getAddress();
}