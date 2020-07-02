package vuong.hx.tayduky.repositories.implementations;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.repositories.interfaces.ChallengeRepo;

public class ChallengeRepoImpl implements ChallengeRepo {

    @Override
    public void getAll(String token, ApiCallBack<List<Challenge>> callBack) {

    }

    @Override
    public void getById(String token, int challengeId, ApiCallBack<Challenge> callBack) {

    }
}
