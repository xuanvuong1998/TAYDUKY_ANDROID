package vuong.hx.tayduky.repositories.implementations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.AuthenticatedUser;
import vuong.hx.tayduky.remote.api.ClientApi;
import vuong.hx.tayduky.remote.services.AuthService;
import vuong.hx.tayduky.repositories.interfaces.AuthRepo;

public class AuthRepoImpl implements AuthRepo {
    @Override
    public void authenticate(String username, String password
                    , final ApiCallBack<AuthenticatedUser> callBack) {
        AuthService client = new ClientApi().getAuthService();

        client.login(username, password)
            .enqueue(new Callback<AuthenticatedUser>() {
                @Override
                public void onResponse(Call<AuthenticatedUser> call, Response<AuthenticatedUser> response) {
                    callBack.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<AuthenticatedUser> call, Throwable t) {
                    callBack.onFail(t.getMessage());
                }
            });
    }
}
