package vuong.hx.tayduky.helpers;

import android.content.Context;

import vuong.hx.tayduky.constants.SharePreferenceKeys;

public class AuthHelper {
    public static void removeCredentials(Context context){
        SharePreferenceHelper.remove(context, SharePreferenceKeys.USER_TOKEN);
        SharePreferenceHelper.remove(context, SharePreferenceKeys.USER_ID);
        SharePreferenceHelper.remove(context, SharePreferenceKeys.USER_PASSWORD);
        SharePreferenceHelper.remove(context, SharePreferenceKeys.USER_ROLE);
    }
}
