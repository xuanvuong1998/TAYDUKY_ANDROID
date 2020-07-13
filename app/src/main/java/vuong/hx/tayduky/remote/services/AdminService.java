package vuong.hx.tayduky.remote.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vuong.hx.tayduky.models.Admin;
import vuong.hx.tayduky.remote.api.ApiConfig;

public interface AdminService {
    @POST(ApiConfig.Apis.Admin.CREATE)
    Call<ResponseBody> createNew(@Body Admin admin);

    @GET(ApiConfig.Apis.Admin.GET_BY_ID)
    Call<Admin> getById(@Header("Authorization") String token, @Path("id") String id);

    @DELETE(ApiConfig.Apis.Admin.DELETE)
    Call<ResponseBody> delete(@Header("Authorization") String token, @Path("id") String id);
}
