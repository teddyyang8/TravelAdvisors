package entity;

import java.util.Set;

public class SavedLocationFilter implements LocationFilterStrategy {
    private Set<String> savedLocations;

    public SavedLocationFilter(Set<String> savedLocations) {
        this.savedLocations = savedLocations;
    }

    @Override
    public boolean filter(String location) {
        return savedLocations.contains(location);
    }
}
