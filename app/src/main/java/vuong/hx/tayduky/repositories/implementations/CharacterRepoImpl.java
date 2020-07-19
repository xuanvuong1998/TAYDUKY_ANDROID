package vuong.hx.tayduky.repositories.implementations;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.constants.MediaTypeConst;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.remote.api.ClientApi;
import vuong.hx.tayduky.remote.services.CharacterService;
import vuong.hx.tayduky.repositories.interfaces.CharacterRepo;

public class CharacterRepoImpl implements CharacterRepo {

    private CharacterService mService;

    public CharacterRepoImpl() {
        mService = new ClientApi().getCharacterService();
    }

    @Override
    public void getAll(final ApiCallBack<List<Character>> callBack) {
        Call<List<Character>> call = mService.getAll();

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
    public void createNew(String token, String name, String defaultActor,
                          File image, final ApiCallBack<ResponseBody> callBack) {

        RequestBody reqName = RequestBody.create(MultipartBody.FORM, name);

        RequestBody reqDefaultActor = RequestBody.create(MultipartBody.FORM, defaultActor);

        RequestBody reqImageFile = RequestBody.create(
                        MediaType.parse(MediaTypeConst.IMAGE), image);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData(
                            "ImageFile",image.getName(), reqImageFile);

        Call<ResponseBody> call = mService.createNew(token, reqName, reqDefaultActor, imagePart);

        call.enqueue(new Callback<ResponseBody>() {
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
    public void update(String token, int id, String name, String defaultActor, File image, final ApiCallBack<ResponseBody> callBack) {

        RequestBody reqName = RequestBody.create(MultipartBody.FORM, name);

        RequestBody reqDefaultActor = RequestBody.create(MultipartBody.FORM, defaultActor);

        MultipartBody.Part imagePart = null;

        if (image != null){
            RequestBody reqImageFile = RequestBody.create(
                    MediaType.parse(MediaTypeConst.IMAGE), image);
            imagePart = MultipartBody.Part.createFormData(
                    "ImageFile",image.getName(), reqImageFile);
        }

        Call<ResponseBody> call = mService.update(token, id, reqName, reqDefaultActor, imagePart);

        call.enqueue(new Callback<ResponseBody>() {
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
