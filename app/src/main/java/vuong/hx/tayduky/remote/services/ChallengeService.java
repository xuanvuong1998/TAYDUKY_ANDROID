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
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.ChallengeCreateModel;
import vuong.hx.tayduky.models.SceneRoleFullInfo;
import vuong.hx.tayduky.remote.api.ApiConfig;

public interface ChallengeService {
    @GET(ApiConfig.Apis.Challenge.GET_BY_ID)
    Call<Challenge> getById(@Path("challenge_id") int challengeId);

    @GET(ApiConfig.Apis.Challenge.GET_ALL)
    Call<List<Challenge>> getAll();

    @GET(ApiConfig.Apis.Challenge.GET_CHALLENGE_ROLES)
    Call<List<SceneRoleFullInfo>> getChallengeRoles(@Path("challenge_id") int challengeId);

    @POST(ApiConfig.Apis.Challenge.CREATE)
    Call<ResponseBody> createNew(@Header("Authorization") String token, @Body ChallengeCreateModel challenge);

    @PUT(ApiConfig.Apis.Challenge.UPDATE)
    Call<ResponseBody> update(@Header("Authorization") String token, @Body ChallengeCreateModel challenge);

    @DELETE(ApiConfig.Apis.Challenge.DELETE)
    Call<ResponseBody> delete(@Header("Authorization") String token, @Path("challenge_id") int challengeId);



}
