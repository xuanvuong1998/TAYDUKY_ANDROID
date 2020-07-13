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
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.remote.api.ApiConfig;

public interface SceneRoleService {

    @GET(ApiConfig.Apis.SceneRole.GET_BY_ID)
    Call<SceneRole> getById(@Path("sceneRole_id") int sceneRoleId);

    @GET(ApiConfig.Apis.SceneRole.GET_CHALLENGE_ROLES)
    Call<SceneRole> getChallengeRoles(@Path("challenge_id") int challengeId);

    @POST(ApiConfig.Apis.SceneRole.CREATE)
    Call<ResponseBody> createNew(@Header("Authorization") String token,  @Body SceneRole SceneRole);

    @PUT(ApiConfig.Apis.SceneRole.UPDATE)
    Call<ResponseBody> update(@Header("Authorization") String token, @Body SceneRole SceneRole);

    @DELETE(ApiConfig.Apis.SceneRole.DELETE)
    Call<ResponseBody> delete(@Header("Authorization") String token
                            , @Path("sceneRole_id") int sceneRoleId);



}
