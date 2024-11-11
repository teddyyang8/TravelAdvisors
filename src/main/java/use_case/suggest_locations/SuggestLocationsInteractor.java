package use_case.suggest_locations;

import java.util.List;

public class SuggestLocationsInteractor implements SuggestLocationsInputBoundary {

    private final LocationDataAccessInterface locationDataAccess;
    private final SuggestLocationsOutputBoundary outputBoundary;

    public SuggestLocationsInteractor(LocationDataAccessInterface locationDataAccess, SuggestLocationsOutputBoundary outputBoundary) {
        this.locationDataAccess = locationDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public void setInterest(String interest) {

    }

    @Override
    public void execute(SuggestLocationsInputData inputData) {
        try {
            String response = locationDataAccess.searchLocation(inputData.getAddress(), inputData.getInterest());
            List<String> locations = parseResponse(response);
            SuggestLocationsOutputData outputData = new SuggestLocationsOutputData(locations);
            outputBoundary.prepareSuccessView(outputData);
        } catch (DataAccessException e) {
            outputBoundary.prepareFailView(e.getMessage());
        }
    }

    private List<String> parseResponse(String response) {

    }
}