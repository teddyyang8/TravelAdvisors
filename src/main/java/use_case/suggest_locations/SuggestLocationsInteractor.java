package use_case.suggest_locations;

import java.util.ArrayList;
import java.util.List;

import entity.Place;
import entity.PlaceFactory;

/**
 * The Suggest Locations Interactor.
 */
public class SuggestLocationsInteractor implements SuggestLocationsInputBoundary {
    private final LocationDataAccessInterface placeDataAccessObject;
    private final SuggestLocationsOutputBoundary placePresenter;
    private final PlaceFactory placeFactory;

    public SuggestLocationsInteractor(LocationDataAccessInterface suggestLocationsPlaceDataAccessInterface,
                                      SuggestLocationsOutputBoundary suggestLocationsOutputBoundary,
                                      PlaceFactory placeFactory) {
        this.placeDataAccessObject = suggestLocationsPlaceDataAccessInterface;
        this.placePresenter = suggestLocationsOutputBoundary;
        this.placeFactory = placeFactory;
    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public void setInterest(String interest) {

    }

    @Override
    public void execute(SuggestLocationsInputData suggestLocationsInputData) throws DataAccessException {
        final String suggestedLocations = placeDataAccessObject.searchLocation(suggestLocationsInputData.getAddress(),
                suggestLocationsInputData.getLocationType());
        final String[] locationsList = suggestedLocations.split("<:>");

        final List<Place> suggestedPlaces = new ArrayList<>();
        for (String location : locationsList) {
            suggestedPlaces.add(placeFactory.create(location.split(">")[1], location.split(">")[0]));
        }

        final SuggestLocationsOutputData suggestLocationsOutputData = new SuggestLocationsOutputData(suggestedPlaces,
                false);
        placePresenter.prepareSuccessView(suggestLocationsOutputData);
    }
}
