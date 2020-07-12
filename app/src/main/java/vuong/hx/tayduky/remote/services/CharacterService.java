package vuong.hx.tayduky.remote.services;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.remote.api.ApiConfig;

public interface CharacterService {
    @GET(ApiConfig.Apis.Character.GET_BY_ID)
    Call<Character> getById(@Path("character_id") int characterId);

    @GET(ApiConfig.Apis.Character.GET_ALL)
    Call<List<Character>> getAll();

    @Multipart
    @POST(ApiConfig.Apis.Character.CREATE)
    Call<ResponseBody> createNew(@Header("Authorization") String token,
                                 @Part("Name") RequestBody name,
                                 @Part("DefaultActor") RequestBody defaultActor,
                                 @Part MultipartBody.Part image);

    @PUT(ApiConfig.Apis.Character.UPDATE)
    Call<ResponseBody> update(@Header("Authorization") String token,@Body Character Character);

    @DELETE(ApiConfig.Apis.Character.DELETE)
    Call<ResponseBody> delete(@Header("Authorization") String token, @Path("character_id") int characterId);



}
