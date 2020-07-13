package vuong.hx.tayduky.remote.services;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import vuong.hx.tayduky.models.SceneTool;
import vuong.hx.tayduky.remote.api.ApiConfig;

public interface SceneToolService {
    @GET(ApiConfig.Apis.SceneTool.GET_BY_IDs)
    Call<SceneTool> getById(@Path("challenge_id") int challengeId, @Path("tool_id") int toolId);

    @POST(ApiConfig.Apis.SceneTool.CREATE)
    Call<ResponseBody> createNew(@Header("Authorization") String token,  @Body SceneTool SceneTool);

    @PUT(ApiConfig.Apis.SceneTool.UPDATE)
    Call<ResponseBody> update(@Header("Authorization") String token, @Body SceneTool SceneTool);

    @DELETE(ApiConfig.Apis.SceneTool.DELETE)
    Call<ResponseBody> delete(@Header("Authorization") String token
                , @Path("challenge_id") int challengeId, @Path("tool_id") int toolId);



}
