package vuong.hx.tayduky.repositories.interfaces;

import java.util.List;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.SceneRoleFullInfo;
import vuong.hx.tayduky.models.SceneTool;

public interface ChallengeRepo {
    void getAll(ApiCallBack<List<Challenge>> callBack);

    void getById(String token, int challengeId, ApiCallBack<Challenge> callBack);

    void getChallengeTools(int challengeId, ApiCallBack<List<SceneTool>> callBack);

    void getChallengeRoles(int challengeId, ApiCallBack<List<SceneRoleFullInfo>> callBack);

    void createNewChallenge(String token, Challenge model, ApiCallBack<ResponseBody> callBack);

    void updateChallenge(String token, Challenge model, ApiCallBack<ResponseBody> callBack);
}
