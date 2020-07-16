package vuong.hx.tayduky.helpers;

import java.util.List;

import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.models.SceneTool;

public class TempDataHelper {
    private static List<Actor> actors;
    private static String userToken;
    private static String userId;
    private static List<SceneRole> cartRoles;
    private static List<SceneTool> cartTools;

    public static List<SceneRole> getCartRoles() {
        return cartRoles;
    }

    public static void setCartRoles(List<SceneRole> cartRoles) {
        TempDataHelper.cartRoles = cartRoles;
    }

    public static List<SceneTool> getCartTools() {
        return cartTools;
    }

    public static void setCartTools(List<SceneTool> cartTools) {
        TempDataHelper.cartTools = cartTools;
    }

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
