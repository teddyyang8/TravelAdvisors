package entity;

/**
 * Factory for creating SuggestedPlace objects.
 */
public class SuggestedPlaceFactory implements PlaceFactory {

    @Override
    public Place create(String name, String address) {
        return new SuggestedPlace(name, address);
    }
}
