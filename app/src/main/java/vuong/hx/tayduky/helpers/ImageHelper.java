package vuong.hx.tayduky.helpers;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import vuong.hx.tayduky.R;

public class ImageHelper {
    public static void loadImageFromURI(String imageURI, ImageView imgView){
        Picasso.get().load(imageURI).error(R.drawable.no_image).into(imgView);
    }
}
