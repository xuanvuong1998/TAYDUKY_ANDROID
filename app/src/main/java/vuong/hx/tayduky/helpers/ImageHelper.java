package vuong.hx.tayduky.helpers;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.remote.api.ApiConfig;

public class ImageHelper {

    public static void loadImageFromURI(String imageURI, ImageView imgView){

        String fullUrl = ApiConfig.HOST_URL + imageURI;

        Picasso.get().load(fullUrl).error(R.drawable.no_image).into(imgView);
    }

    public static void loadImageFromFullURL(String publicUrl, ImageView imgView){
        Picasso.get().load(publicUrl).error(R.drawable.no_image).into(imgView);
    }
}
