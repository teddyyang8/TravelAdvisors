package use_case.review_locations;

import use_case.DataAccessException;

public interface ReviewLocationsInputBoundary {
    /**
     * Execute the Review Locations Use Case.
     * @param reviewLocationsInputData the input data for the use case
     * @throws DataAccessException if an error occurs
     */
    void execute(ReviewLocationsInputData reviewLocationsInputData) throws DataAccessException;
}
