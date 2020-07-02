package vuong.hx.tayduky.repositories.interfaces;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.models.SceneRole;

public interface ActorRepo {
    void getAll(String token, ApiCallBack<List<Actor>> callBack);
    void getActorById(String token, String actorId, ApiCallBack<Actor> callBack);
    void getIncomingRoles(String token, String actorId, ApiCallBack<List<SceneRole>> callBack);
    void getPlayedRoles(String token,String actorId, ApiCallBack<List<SceneRole>> callBack);


}
