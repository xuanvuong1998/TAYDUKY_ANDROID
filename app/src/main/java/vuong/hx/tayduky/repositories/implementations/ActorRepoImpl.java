package vuong.hx.tayduky.repositories.implementations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.remote.api.ClientApi;
import vuong.hx.tayduky.remote.services.ActorService;
import vuong.hx.tayduky.repositories.interfaces.ActorRepo;

public class ActorRepoImpl implements ActorRepo {
    private ActorService actorService = new ClientApi().getActorService();
    @Override
    public void getAll(String token, final ApiCallBack<List<Actor>> callBack) {

        actorService.getAll( token).enqueue(new Callback<List<Actor>>() {
            @Override
            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                if (response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Actor>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getActorById(String token, String actorId, final ApiCallBack<Actor> callBack) {
        actorService.getById(token, actorId).enqueue(new Callback<Actor>() {
            @Override
            public void onResponse(Call<Actor> call, Response<Actor> response) {
                if (response.isSuccessful()){
                    callBack.onSuccess(response.body());

                }else{
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<Actor> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getIncomingRoles(String actorId, final ApiCallBack<List<SceneRole>> callBack) {
        actorService.getIncomingRoles(actorId).enqueue(new Callback<List<SceneRole>>() {
            @Override
            public void onResponse(Call<List<SceneRole>> call, Response<List<SceneRole>> response) {
                if (response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<SceneRole>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getPlayedRoles(String actorId, final ApiCallBack<List<SceneRole>> callBack) {
        actorService.getPlayedRoles(actorId).enqueue(new Callback<List<SceneRole>>() {
            @Override
            public void onResponse(Call<List<SceneRole>> call, Response<List<SceneRole>> response) {
                if (response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<SceneRole>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }



}
