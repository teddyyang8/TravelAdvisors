package entity;

import java.util.Set;

public class DislikedLocationFilter implements LocationFilterStrategy {
    private Set<String> dislikedLocations;

    public DislikedLocationFilter(Set<String> dislikedLocations) {
        this.dislikedLocations = dislikedLocations;
    }

    @Override
    public boolean filter(String location) {
        return !dislikedLocations.contains(location);
    }
}
