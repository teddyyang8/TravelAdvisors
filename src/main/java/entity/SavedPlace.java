
package entity;

/**
 * An implementation of the Place interface.
 */
public class SavedPlace implements Place {

    private final String name;
    private final String address;
    private final String review;
    private final boolean rating;

    public SavedPlace(String name, String address, String review, boolean rating) {
        this.name = name;
        this.address = address;
        this.review = review;
        this.rating = rating;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public String getReview() {
        return review;
    }

    public boolean getRating() {
        return rating;
    }
}