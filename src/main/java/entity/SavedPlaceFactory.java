package entity;

/**
 * Factory for creating SuggestedPlace objects.
 */
public class SavedPlaceFactory implements PlaceFactory {

    @Override
    public Place create(String name, String address) {
        return new SavedPlace(name, address, "", true);
    }
}
