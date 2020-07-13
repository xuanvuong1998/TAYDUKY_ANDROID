package vuong.hx.tayduky.repositories.implementations;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Admin;
import vuong.hx.tayduky.remote.api.ClientApi;
import vuong.hx.tayduky.repositories.interfaces.AdminRepo;

public class AdminRepoImpl implements AdminRepo {
    @Override
    public void getInfoById(String token, String adminId, final ApiCallBack<Admin> callBack) {
        new ClientApi().getAdminService().getById(token,adminId).enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }
}
