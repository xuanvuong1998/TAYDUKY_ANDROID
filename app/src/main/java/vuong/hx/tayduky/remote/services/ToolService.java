package vuong.hx.tayduky.remote.services;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import vuong.hx.tayduky.models.Tool;
import vuong.hx.tayduky.remote.api.ApiConfig;

public interface ToolService {
    @GET(ApiConfig.Apis.Tool.GET_BY_ID)
    Call<Tool> getById(@Path("tool_id") int toolId);

    @GET(ApiConfig.Apis.Tool.GET_ALL)
    Call<List<Tool>> getAll();

    @Multipart
    @POST(ApiConfig.Apis.Tool.CREATE)
    Call<ResponseBody> createNew(@Header("Authorization") String token,
                                 @Part("toolName") String name, @Part("toolDesc") String desc,
                                 @Part("toolQuantity") int qty, @Part MultipartBody.Part imageFile);

    @PUT(ApiConfig.Apis.Tool.UPDATE)
    Call<ResponseBody> update(@Header("Authorization") String token, @Body Tool Tool);

    @DELETE(ApiConfig.Apis.Tool.DELETE)
    Call<ResponseBody> delete(@Header("Authorization") String token, @Path("tool_id") int toolId);



}
