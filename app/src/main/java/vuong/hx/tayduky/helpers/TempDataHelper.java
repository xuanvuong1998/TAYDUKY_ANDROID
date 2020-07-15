package vuong.hx.tayduky.helpers;

import java.util.List;

import vuong.hx.tayduky.models.Actor;

public class TempDataHelper {
    private static List<Actor> actors;
    private static String userToken;
    private static String userId;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        TempDataHelper.userId = userId;
    }

    public static String getUserToken() {
        return userToken;
    }

    public static void setUserToken(String userToken) {
        TempDataHelper.userToken = userToken;
    }

    public static List<Actor> getActors() {
        return actors;
    }

    public static void setActors(List<Actor> actors) {
        TempDataHelper.actors = actors;
    }
}
