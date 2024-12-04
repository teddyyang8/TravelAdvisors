package entity;

public class LocationFilterContext {
    private LocationFilterStrategy strategy;

    public LocationFilterContext(LocationFilterStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(LocationFilterStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean applyFilter(String location) {
        return strategy.filter(location);
    }
}
