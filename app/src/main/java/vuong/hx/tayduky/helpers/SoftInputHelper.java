package vuong.hx.tayduky.helpers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class SoftInputHelper {
    public static void hideKeyboard(Activity activity){
        InputMethodManager inputMethodManager =
                    (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        View view =  activity.getCurrentFocus();

        if (view == null){
            view = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboardFrom(Context context, View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
