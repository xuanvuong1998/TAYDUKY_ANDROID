package vuong.hx.tayduky.presenters;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.AuthenticatedUser;
import vuong.hx.tayduky.repositories.implementations.AuthRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.AuthRepo;
import vuong.hx.tayduky.ui.view_interfaces.LoginScreenView;

public class LoginPresenter {
    private AuthRepo mAuthRepo;
    private LoginScreenView loginView;

    public LoginPresenter(LoginScreenView loginView) {
        mAuthRepo = new AuthRepoImpl();

        loginView = loginView;
    }

    public void setLoginView(LoginScreenView loginView) {
        this.loginView = loginView;
    }

    public void authenticate(final String username, final String password){
        mAuthRepo.authenticate(username, password, new ApiCallBack<AuthenticatedUser>() {
            @Override
            public void onSuccess(AuthenticatedUser authenticatedUser) {
                if (authenticatedUser != null){
                    loginView.goToHomeActivity(username, password,authenticatedUser.getToken()
                            , authenticatedUser.getRole());
                }else{
                    loginView.showToastMessage("Login failed!");
                }

            }

            @Override
            public void onFail(String message) {
                loginView.showToastMessage(message);
            }
        });
    }
}
