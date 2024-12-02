package interface_adapter.reviewlocation;

import entity.Place;
import entity.SavedPlace;

import java.util.List;
import java.util.Map;

/**
 * The state representing selected location-related data, including a list of
 * selected locations.
 */
public class ReviewLocationState {

    private Map<Place, List<String>> reviewMap;

    public Map<Place, List<String>> getReviewMap() {
        return reviewMap;
    }

    public void setReviewMap(Map<Place, List<String>> reviewMap) {
        this.reviewMap = reviewMap;
    }
}