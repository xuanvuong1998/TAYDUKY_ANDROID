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
import vuong.hx.tayduky.models.Tool;
import vuong.hx.tayduky.remote.api.ClientApi;
import vuong.hx.tayduky.remote.services.ToolService;
import vuong.hx.tayduky.repositories.interfaces.ToolRepo;

public class ToolRepoImpl implements ToolRepo {

    private ToolService mToolService;

    public ToolRepoImpl() {
        mToolService = new ClientApi().getToolService();
    }

    @Override
    public void getAll(final ApiCallBack<List<Tool>> callBack) {
        Call<List<Tool>> call = mToolService.getAll();

        call.enqueue(new Callback<List<Tool>>() {
            @Override
            public void onResponse(Call<List<Tool>> call, Response<List<Tool>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
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

    @Override
    public void addNew(String token, String toolName, int quantity,
                       String desc, File image, final ApiCallBack<ResponseBody> callBack) {

        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), image);

        MultipartBody.Part imagePart = MultipartBody.Part
                .createFormData("imageFile", image.getName(), fileReqBody);

        RequestBody reqName = RequestBody.create(MultipartBody.FORM, toolName);
        RequestBody reqDesc = RequestBody.create(MultipartBody.FORM, desc);

        Call<ResponseBody> call = mToolService.createNew(token, reqName,
                                    reqDesc, quantity, imagePart);

        call.enqueue(new Callback<ResponseBody>() {
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
    public void delete(String token, int toolId, final ApiCallBack<ResponseBody> callBack){
        Call<ResponseBody> call = mToolService.delete(token, toolId);

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
    public void update(String token, Tool tool, File image, final ApiCallBack<ResponseBody> callBack) {

        RequestBody fileReqBody = null;

        if (image != null){
            fileReqBody = RequestBody.create(MediaType.parse("image/*"), image);
        }

        String imageName = null;

        if (image != null) imageName = image.getName();

        MultipartBody.Part imagePart = null;
        if (image != null){
            imagePart = MultipartBody.Part
                    .createFormData("imageFile", imageName, fileReqBody);

        }
        RequestBody reqName = RequestBody.create(MultipartBody.FORM, tool.getName());
        RequestBody reqDesc = RequestBody.create(MultipartBody.FORM, tool.getDescription());

        Call<ResponseBody> call = mToolService.update(token, (int) tool.getId(),
                                    reqName, reqDesc, (int) tool.getQuantity(), imagePart);

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
