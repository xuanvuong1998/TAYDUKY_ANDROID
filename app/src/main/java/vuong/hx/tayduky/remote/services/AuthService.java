package vuong.hx.tayduky.remote.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vuong.hx.tayduky.models.AuthenticatedUser;
import vuong.hx.tayduky.remote.api.ApiConfig;

public interface AuthService {
    @GET(ApiConfig.Apis.Auth.LOGIN)

    Call<AuthenticatedUser> login(@Query("username") String username
                , @Query("password") String password);
}
