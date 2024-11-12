package interface_adapter.location;

import java.util.ArrayList;
import java.util.List;

import entity.Place;
import use_case.suggest_locations.SuggestLocationsInputData;

/**
 * The state representing location-related data, including city, address, keywords,
 * suggested locations, and any error messages.
 */
public class LocationState {
    private String error;
    private String address;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String keyword4;
    private String keyword5;
    private List<Place> suggestedLocations;

    public LocationState() {
        this.suggestedLocations = new ArrayList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getKeyword4() {
        return keyword4;
    }

    public void setKeyword4(String keyword4) {
        this.keyword4 = keyword4;
    }

    public String getKeyword5() {
        return keyword5;
    }

    public void setKeyword5(String keyword5) {
        this.keyword5 = keyword5;
    }

    public void setError(String errorMessage) {

        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }

    public List<Place> getSuggestedLocations() {
        return suggestedLocations;
    }

    public void setSuggestedLocations(List<Place> suggestedLocations) {
        this.suggestedLocations = suggestedLocations;
    }

    /**
     * Returns current InputData.
     * @return current input data.
     */
    public SuggestLocationsInputData getInputData() {
        final List<String> keywords = getKeywords();
        final String joinedKeywords = String.join(";", keywords);
        return new SuggestLocationsInputData(address, joinedKeywords);
    }

    private List<String> getKeywords() {
        final List<String> keywords = new ArrayList<>();

        if (!keyword1.isEmpty()) {
            keywords.add(keyword1);
        }
        if (!keyword2.isEmpty()) {
            keywords.add(keyword2);
        }
        if (!keyword3.isEmpty()) {
            keywords.add(keyword3);
        }
        if (!keyword4.isEmpty()) {
            keywords.add(keyword4);
        }
        if (!keyword5.isEmpty()) {
            keywords.add(keyword5);
        }
        return keywords;
    }
}

