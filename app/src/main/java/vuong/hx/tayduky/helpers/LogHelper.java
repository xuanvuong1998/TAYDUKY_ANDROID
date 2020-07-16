package vuong.hx.tayduky.helpers;

import android.util.Log;

public class LogHelper {
    public static void printAssert(String mess) {
        Log.println(Log.ASSERT, "TEST", mess);
    }
}