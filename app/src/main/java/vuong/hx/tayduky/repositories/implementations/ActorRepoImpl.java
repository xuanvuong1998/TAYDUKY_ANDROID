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

    @Override
    public void getAll(final ApiCallBack<List<Actor>> callBack) {
        ActorService actorService = new ClientApi().getActorService();

        actorService.getAll().enqueue(new Callback<List<Actor>>() {
            @Override
            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                if (response != null && response.body() != null){
                    if (response.code() == 200){
                        callBack.onSuccess(response.body());
                    }else{
                        callBack.onFail(response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Actor>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getActorById(String token, String actorId, ApiCallBack<Actor> callBack) {

    }

    @Override
    public void getIncomingRoles(String actorId, ApiCallBack<List<SceneRole>> callBack) {

    }

    @Override
    public void getPlayedRoles(String actorId, ApiCallBack<List<SceneRole>> callBack) {

    }



}
