package vuong.hx.tayduky.repositories.implementations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.models.SceneTool;
import vuong.hx.tayduky.remote.api.ClientApi;
import vuong.hx.tayduky.remote.services.ChallengeService;
import vuong.hx.tayduky.remote.services.SceneRoleService;
import vuong.hx.tayduky.remote.services.SceneToolService;
import vuong.hx.tayduky.repositories.interfaces.ChallengeRepo;

public class ChallengeRepoImpl implements ChallengeRepo {

    private ChallengeService mChallengeService;
    private SceneToolService mSceneToolService;
    private SceneRoleService mSceneRoleService;

    public ChallengeRepoImpl() {
        mChallengeService = new ClientApi().getChallengeService();
        mSceneRoleService = new ClientApi().getSceneRoleService();
        mSceneToolService = new ClientApi().getSceneToolService();
    }

    @Override
    public void getAll(final ApiCallBack<List<Challenge>> callBack) {
        Call<List<Challenge>> call = mChallengeService.getAll();

        call.enqueue(new Callback<List<Challenge>>() {
            @Override
            public void onResponse(Call<List<Challenge>> call, Response<List<Challenge>> response) {
                if (response.isSuccessful() == false){

                    callBack.onFail("Retrieve Data failed!");
                }else{
                    callBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Challenge>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getById(String token, int challengeId, final ApiCallBack<Challenge> callBack) {
        Call<Challenge> call = mChallengeService.getById(challengeId);

        call.enqueue(new Callback<Challenge>() {
            @Override
            public void onResponse(Call<Challenge> call, Response<Challenge> response) {

                callBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Challenge> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });

    }

    @Override
    public void getChallengeTools(int challengeId, final ApiCallBack<List<SceneTool>> callBack) {
        mSceneToolService.getChallengeTools(challengeId).enqueue(new Callback<List<SceneTool>>() {
            @Override
            public void onResponse(Call<List<SceneTool>> call, Response<List<SceneTool>> response) {
                if (response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<SceneTool>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getChallengeRoles(int challengeId, final ApiCallBack<List<SceneRole>> callBack) {
        mSceneRoleService.getChallengeRoles(challengeId).enqueue(new Callback<List<SceneRole>>() {
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
