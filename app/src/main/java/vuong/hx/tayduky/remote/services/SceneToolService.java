package vuong.hx.tayduky.remote.services;

import java.util.List;

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

    @GET(ApiConfig.Apis.SceneTool.GET_CHALLENGE_TOOLS)
    Call<List<SceneTool>> getChallengeTools(@Path("challenge_id") int challengeId);

    @POST(ApiConfig.Apis.SceneTool.CREATE)
    Call<ResponseBody> createNew(@Header("Authorization") String token,  @Body SceneTool sceneTool);

    @POST(ApiConfig.Apis.SceneTool.CREATE_RANGE)
    Call<ResponseBody> createRange(@Header("Authorization") String token,  @Body List<SceneTool> sceneTool);

    @PUT(ApiConfig.Apis.SceneTool.UPDATE)
    Call<ResponseBody> update(@Header("Authorization") String token, @Body SceneTool sceneTool);

    @DELETE(ApiConfig.Apis.SceneTool.DELETE)
    Call<ResponseBody> delete(@Header("Authorization") String token
                , @Path("challenge_id") int challengeId, @Path("tool_id") int toolId);



}
