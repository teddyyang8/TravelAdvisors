package use_case.review_locations;

import entity.SavedPlace;
import org.junit.Test;
import use_case.DataAccessException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ReviewLocationsInteractorTest {

    @Test
    public void successTest() {
        // Arrange
        SavedPlace place1 = new SavedPlace("Place1", "Address1", "", false);
        SavedPlace place2 = new SavedPlace("Place2", "Address2", "", false);
        Map<SavedPlace, List<String>> addedReviews = new LinkedHashMap<>();
        addedReviews.put(place1, List.of("like", "Great place!"));
        addedReviews.put(place2, List.of("dislike", "Not so good."));
        ReviewLocationsInputData inputData = new ReviewLocationsInputData(addedReviews, "TestList");

        ReviewLocationsOutputBoundary reviewPresenter = new ReviewLocationsOutputBoundary() {
            @Override
            public void prepareSuccessView(ReviewLocationsOutputData outputData) {
                assertEquals("TestList", outputData.getListName());
                List<SavedPlace> savedPlaces = outputData.getSavedPlaces();
                assertEquals(2, savedPlaces.size());

                SavedPlace updatedPlace1 = savedPlaces.get(0);
                assertEquals("Place1", updatedPlace1.getName());
                assertEquals("Address1", updatedPlace1.getAddress());
                assertEquals("Great place!", updatedPlace1.getReview());
                assertEquals(true, updatedPlace1.getRating());
                SavedPlace updatedPlace2 = savedPlaces.get(1);
                assertEquals("Place2", updatedPlace2.getName());
                assertEquals("Address2", updatedPlace2.getAddress());
                assertEquals("Not so good.", updatedPlace2.getReview());
                assertEquals(false, updatedPlace2.getRating());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        ReviewLocationsInteractor interactor = new ReviewLocationsInteractor(reviewPresenter);

        // Act
        try {
            interactor.execute(inputData);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testExecuteWithEmptyReviews() {
        // Arrange
        Map<SavedPlace, List<String>> addedReviews = new LinkedHashMap<>();
        ReviewLocationsInputData inputData = new ReviewLocationsInputData(addedReviews, "EmptyList");

        ReviewLocationsOutputBoundary reviewPresenter = new ReviewLocationsOutputBoundary() {
            @Override
            public void prepareSuccessView(ReviewLocationsOutputData outputData) {
                assertEquals("EmptyList", outputData.getListName());
                assertEquals(0, outputData.getSavedPlaces().size());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        ReviewLocationsInteractor interactor = new ReviewLocationsInteractor(reviewPresenter);

        // Act
        try {
            interactor.execute(inputData);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testExecuteWithNullReviews() {
        // Arrange
        ReviewLocationsInputData inputData = new ReviewLocationsInputData(null, "NullList");

        ReviewLocationsOutputBoundary reviewPresenter = new ReviewLocationsOutputBoundary() {
            @Override
            public void prepareSuccessView(ReviewLocationsOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        ReviewLocationsInteractor interactor = new ReviewLocationsInteractor(reviewPresenter);

        // Act & Assert
        try {
            interactor.execute(inputData);
            fail("Expected NullPointerException was not thrown.");
        } catch (NullPointerException e) {
            // Expected exception
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}