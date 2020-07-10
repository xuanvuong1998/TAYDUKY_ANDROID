package vuong.hx.tayduky.remote.api;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApi {
    private Retrofit retrofit;

    public <T> T getService(Class<T> tClass, String url){
        if (retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(80, TimeUnit.SECONDS)
                            .build();

            try {
                retrofit = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create())

                        //.client(client)
                        .build();
            }catch (Exception exp)
            {
                Log.e("RETROFIT_ERR", exp.getMessage());
            }
        }

        return retrofit.create(tClass);
    }
}
