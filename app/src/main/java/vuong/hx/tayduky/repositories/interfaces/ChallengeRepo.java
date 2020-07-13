package vuong.hx.tayduky.repositories.interfaces;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.models.SceneTool;

public interface ChallengeRepo {
    void getAll(ApiCallBack<List<Challenge>> callBack);

    void getById(String token, int challengeId, ApiCallBack<Challenge> callBack);

    void getChallengeTools(int challengeId, ApiCallBack<List<SceneTool>> callBack);

    void getChallengeRoles(int challengeId, ApiCallBack<List<SceneRole>> callBack);

}
