package entity;

/**
 * Factory for creating SuggestedPlace objects.
 */
public class SavedPlaceFactory implements PlaceFactory {

    public Place create(String name, String address, String review, boolean rating) {
        return new SavedPlace(name, address, review, rating);
    }

    @Override
    public Place create(String name, String address) {
        return null;
    }
}
