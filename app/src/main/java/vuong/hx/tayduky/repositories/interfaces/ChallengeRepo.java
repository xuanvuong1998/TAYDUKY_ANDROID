package vuong.hx.tayduky.repositories.interfaces;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Challenge;

public interface ChallengeRepo {
    void getAll(ApiCallBack<List<Challenge>> callBack);

    void getById(String token, int challengeId, ApiCallBack<Challenge> callBack);


}
