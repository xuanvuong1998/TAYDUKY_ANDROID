package vuong.hx.tayduky.repositories.implementations;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.SceneTool;
import vuong.hx.tayduky.remote.api.ClientApi;
import vuong.hx.tayduky.remote.services.SceneToolService;
import vuong.hx.tayduky.repositories.interfaces.SceneToolRepo;

public class SceneToolRepoImpl implements SceneToolRepo {

    private SceneToolService mService = new ClientApi().getSceneToolService();

    @Override
    public void createRange(String token, List<SceneTool> tools, final ApiCallBack<ResponseBody> callBack) {
        mService.createRange(token, tools).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void create(String token, SceneTool tool, final ApiCallBack<ResponseBody> callBack) {
        mService.createNew(token, tool).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }
}
