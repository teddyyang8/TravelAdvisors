package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.location.LocationViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.reviewlocation.ReviewLocationController;
import interface_adapter.reviewlocation.ReviewLocationPresenter;
import interface_adapter.reviewlocation.ReviewLocationViewModel;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import use_case.review_locations.ReviewLocationsInteractor;
import use_case.review_locations.ReviewLocationsOutputBoundary;
import view.LoginView;
import view.ReviewLocationsView;

import javax.swing.text.View;

public class ReviewLocationsUseCaseFactory {
    public static ReviewLocationsView create(
            ViewManagerModel viewManagerModel,
            ReviewLocationViewModel reviewLocationViewModel, UserProfileViewModel userProfileViewModel) {

        final ReviewLocationController reviewLocationController = createReviewLocationUseCase(viewManagerModel,
                reviewLocationViewModel, userProfileViewModel);
        return new ReviewLocationsView(reviewLocationViewModel, reviewLocationController);
    }

    private static ReviewLocationController createReviewLocationUseCase(
            ViewManagerModel viewManagerModel,
            ReviewLocationViewModel reviewLocationViewModel, UserProfileViewModel userProfileViewModel) {

        // Notice how we pass this method's parameters to the Presenter.
        final ReviewLocationsOutputBoundary reviewLocationsOutputBoundary = new ReviewLocationPresenter(
                reviewLocationViewModel, viewManagerModel, userProfileViewModel);
        final ReviewLocationsInteractor reviewLocationsInteractor = new ReviewLocationsInteractor(reviewLocationsOutputBoundary);

        return new ReviewLocationController(reviewLocationsInteractor);
    }
}
