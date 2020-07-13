package vuong.hx.tayduky.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceHelper {
    public static void putInt(Context context,String key, int val){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        sharedPreferences.edit().putInt(key, val).apply();
    }

    public static void putString(Context context, String key, String val){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        sharedPreferences.edit().putString(key, val).apply();
    }

    public static String getString(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        return sharedPreferences.getString(key, null);
    }
    public static int getInt(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        return sharedPreferences.getInt(key, -999);
    }

    public static void remove(Context context, String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(key, Context.MODE_PRIVATE);

        sharedPreferences.edit().remove(key).apply();
    }

}