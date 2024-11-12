package interface_adapter.location;

import interface_adapter.ViewModel;

public class LocationViewManagerModel extends ViewModel<String> {
    public LocationViewManagerModel() {
        super("view manager");
        this.setState("");
    }

}
