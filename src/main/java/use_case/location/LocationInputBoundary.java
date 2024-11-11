package use_case.location;

/**
 * The Input Boundary for our note-related use cases. Since they are closely related,
 * we have included them both in the same interface for simplicity.
 */
public interface LocationInputBoundary {

    void setCity(String city);

    void setAddress(String address);

    void setInterest(String interest);

    /**
     * Executes the refresh note use case.
     */
    void executeRefresh();

    /**
     * Executes the save note use case.
     * @param message the input data
     */
    void executeSave(String message);
}
