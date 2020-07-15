package vuong.hx.tayduky.helpers;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.remote.api.ApiConfig;

public class ImageHelper {

    /**
     * Load image from internal storage in WEB API
     * @param imageURI relative path which stored in SQL SERVER
     * @param imgView
     */
    public static void loadImageFromInternal(String imageURI, ImageView imgView){

        String fullUrl = ApiConfig.HOST_URL + imageURI;

        Picasso.get().load(fullUrl).error(R.drawable.no_image).into(imgView);
    }

    /**
     * Load iamge from public resource on the internet
     * @param publicUrl absolute path
     * @param imgView
     */
    public static void loadImageFromExternal(String publicUrl, ImageView imgView){
        Picasso.get().load(publicUrl).error(R.drawable.no_image).into(imgView);
    }

    /**
     * After pick a image from gallery, convert to Bitmap ->
     * @param bitmap
     * @param imgView
     */
    public static void loadImageFromBitmap(Bitmap bitmap, ImageView imgView){
        imgView.setImageBitmap(bitmap);
    }
}
