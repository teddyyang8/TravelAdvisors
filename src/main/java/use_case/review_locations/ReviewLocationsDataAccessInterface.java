package use_case.review_locations;

import entity.Place;
import entity.SavedPlace;
import use_case.DataAccessException;

import java.util.List;
import java.util.Map;

public interface ReviewLocationsDataAccessInterface {

    void savePlaces(Map<String, List<SavedPlace>> places) throws DataAccessException;

}
