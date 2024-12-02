package use_case.review_locations;

import use_case.DataAccessException;

public interface ReviewLocationsInputBoundary {

    void execute(ReviewLocationsInputData reviewLocationsInputData) throws DataAccessException;
}
