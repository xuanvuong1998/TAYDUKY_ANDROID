package vuong.hx.tayduky.repositories.implementations;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.remote.api.ClientApi;
import vuong.hx.tayduky.remote.services.SceneRoleService;
import vuong.hx.tayduky.repositories.interfaces.SceneRoleRepo;

public class SceneRoleRepoImpl implements SceneRoleRepo {

    private SceneRoleService mService = new ClientApi().getSceneRoleService();

    @Override
    public void createRange(String token, List<SceneRole> roles, final ApiCallBack<ResponseBody> callBack) {
        mService.createRange(token, roles).enqueue(new Callback<ResponseBody>() {
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
    public void create(String token, SceneRole role, final ApiCallBack<ResponseBody> callBack) {
        mService.createNew(token, role).enqueue(new Callback<ResponseBody>() {
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
    public void remove(String userToken, int id, final ApiCallBack<ResponseBody> callBack) {
        mService.delete(userToken, id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }else{
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
    public void update(String userToken, SceneRole role, final ApiCallBack<ResponseBody> callBack) {
        mService.update(userToken, role).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }else{
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
