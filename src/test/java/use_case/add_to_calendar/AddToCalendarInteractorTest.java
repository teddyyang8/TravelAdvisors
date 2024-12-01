package use_case.add_to_calendar;

import data_access.InMemoryCalendarDataAccessObject;
import entity.Place;
import entity.SuggestedPlace;
import entity.SuggestedPlaceFactory;
import org.junit.Test;
import use_case.DataAccessException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AddToCalendarInteractorTest {

    @Test
    public void successTest() {
        SuggestedPlaceFactory factory = new SuggestedPlaceFactory();
        Place inputPlace = factory.create("University of Toronto","27 King's College Cir, Toronto");
        Map<Place, String> inputData = new HashMap<>();
        String inputTime = "12:30";
        inputData.put(inputPlace, inputTime);
        AddToCalendarInputData calendarInputData = new AddToCalendarInputData(inputData);
        AddToCalendarDataAccessInterface calendarRepository = new InMemoryCalendarDataAccessObject();

        // Add University of Toronto to the data access repository.
        calendarRepository.save(inputPlace, inputTime);

        // Create a successPresenter to test the test case
        AddToCalendarOutputBoundary successPresenter = new AddToCalendarOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToCalendarOutputData outputData) {
                assertEquals("University of Toronto", inputPlace.getName());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }
        };

        AddToCalendarInputBoundary interactor = new AddToCalendarInteractor(calendarRepository, successPresenter);
        try{
            interactor.execute(calendarInputData);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void failureTimeslotBookedTest() {
        SuggestedPlaceFactory factory = new SuggestedPlaceFactory();
        Place inputPlace = factory.create("University of Toronto","27 King's College Cir, Toronto");
        Map<Place, String> inputData = new HashMap<>();
        String inputTime = "12:30";
        inputData.put(inputPlace, inputTime);
        String inputTime2 = "12:30";
        inputData.put(inputPlace, inputTime2);
        AddToCalendarInputData calendarInputData = new AddToCalendarInputData(inputData);
        AddToCalendarDataAccessInterface calendarRepository = new InMemoryCalendarDataAccessObject();

        // Add University of Toronto to the data access repository.
        calendarRepository.save(inputPlace, inputTime);

        // Create a successPresenter to test the test case
        AddToCalendarOutputBoundary successPresenter = new AddToCalendarOutputBoundary() {
            @Override
            public void prepareSuccessView(AddToCalendarOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("12:30 is already full.", errorMessage);
            }
        };

        AddToCalendarInputBoundary interactor = new AddToCalendarInteractor(calendarRepository, successPresenter);
        try{
            interactor.execute(calendarInputData);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
