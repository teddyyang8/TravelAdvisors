package interface_adapter.location;

import java.util.ArrayList;
import java.util.List;

/**
 * The State for a note.
 */
public class LocationState {
    private String error;
    private String city;
    private String address;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String keyword4;
    private String keyword5;
    private List<String> suggestedLocations;

    public LocationState() {
        this.suggestedLocations = new ArrayList<>();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public List<String> getSuggestedLocations() {
        return suggestedLocations;
    }

    public void setSuggestedLocations(List<String> suggestedLocations) {
        this.suggestedLocations = suggestedLocations;
    }
}
