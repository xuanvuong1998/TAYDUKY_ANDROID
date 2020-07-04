package vuong.hx.tayduky.helpers;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {
    public static void showShortMess(Context context, String mess){
        Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
    }
    public static void showLongMess(Context context, String mess){

        Toast.makeText(context, mess, Toast.LENGTH_LONG).show();
    }
}
