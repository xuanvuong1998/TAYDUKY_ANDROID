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
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.remote.api.ApiConfig;

public interface ActorService {
    @GET(ApiConfig.Apis.Actor.GET_BY_ID)
    Call<Actor> getById(@Header("Authorization") String token, @Path("actor_id") String actor_id);

    @GET(ApiConfig.Apis.Actor.GET_ALL)
    Call<List<Actor>> getAll(@Header("Authorization") String token);

    @GET(ApiConfig.Apis.Actor.GET_INCOMING_ROLES)
    Call<List<SceneRole>> getIncomingRoles(@Path("actor_id") String actorId);

    @GET(ApiConfig.Apis.Actor.GET_PLAYED_ROLES)
    Call<List<SceneRole>> getPlayedRoles(@Path("actor_id") String actorId);

    @POST(ApiConfig.Apis.Actor.CREATE)
    Call<ResponseBody> createNew(@Body Actor actor);

    @PUT(ApiConfig.Apis.Actor.UPDATE)
    Call<ResponseBody> update(@Header("Authorization") String token, @Body Actor actor);

    @DELETE(ApiConfig.Apis.Actor.DELETE)
    Call<ResponseBody> delete(@Header("Authorization") String token, @Path("actor_id") String actorId);



}
