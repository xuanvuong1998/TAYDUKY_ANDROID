package vuong.hx.tayduky.repositories.implementations;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.remote.api.ClientApi;
import vuong.hx.tayduky.repositories.interfaces.CharacterRepo;

public class CharacterRepoImpl implements CharacterRepo {

    @Override
    public void getAll(final ApiCallBack<List<Character>> callBack) {
        Call<List<Character>> call = new ClientApi().getCharacterService().getAll();

        call.enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                if (response.isSuccessful()){
                    callBack.onSuccess(response.body());
                }else{
                    callBack.onFail("Failed to get list");
                }
            }

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void getById(String token, int toolId, ApiCallBack<Character> callBack) {

    }
}
