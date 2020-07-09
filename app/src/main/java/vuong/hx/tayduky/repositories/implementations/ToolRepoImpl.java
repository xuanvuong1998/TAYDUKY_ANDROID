package vuong.hx.tayduky.repositories.implementations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Tool;
import vuong.hx.tayduky.remote.api.ClientApi;
import vuong.hx.tayduky.repositories.interfaces.ToolRepo;

public class ToolRepoImpl implements ToolRepo {
    @Override
    public void getAll(final ApiCallBack<List<Tool>> callBack) {
        Call<List<Tool>> call = new ClientApi().getToolService().getAll();

        call.enqueue(new Callback<List<Tool>>() {
            @Override
            public void onResponse(Call<List<Tool>> call, Response<List<Tool>> response) {
                if (response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFail(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Tool>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });

    }

    @Override
    public void getById(int toolId, ApiCallBack<Tool> callBack) {

    }
}
