package interface_adapter.reviewlocation;

import interface_adapter.ViewModel;

public class ReviewLocationViewModel extends ViewModel<ReviewLocationState> {

    public ReviewLocationViewModel(ReviewLocationState state) {
        super("Review Locations");
        setState(new ReviewLocationState());
    }
}
