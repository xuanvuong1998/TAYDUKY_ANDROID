package vuong.hx.tayduky.presenters;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.AuthenticatedUser;
import vuong.hx.tayduky.repositories.implementations.AuthRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.AuthRepo;
import vuong.hx.tayduky.ui.view_interfaces.LoginScreenView;

public class LoginPresenter {
    private AuthRepo mAuthRepo;
    private LoginScreenView mLoginView;

    public LoginPresenter(LoginScreenView loginView) {
        mAuthRepo = new AuthRepoImpl();

        mLoginView = loginView;
    }

    public void authenticate(final String username, String password){
        mAuthRepo.authenticate(username, password, new ApiCallBack<AuthenticatedUser>() {
            @Override
            public void onSuccess(AuthenticatedUser authenticatedUser) {
                if (authenticatedUser != null){
                    mLoginView.goToHomeActivity(username, authenticatedUser.getToken()
                            , authenticatedUser.getRole());
                }else{
                    mLoginView.showToastMessage("Login failed!");
                }

            }

            @Override
            public void onFail(String message) {
                mLoginView.showToastMessage(message);
            }
        });
    }
}
