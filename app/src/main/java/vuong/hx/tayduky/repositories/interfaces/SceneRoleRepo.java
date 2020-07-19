package vuong.hx.tayduky.repositories.interfaces;

import java.util.List;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.SceneRole;

public interface SceneRoleRepo {

    void createRange(String token, List<SceneRole> roles, ApiCallBack<ResponseBody> callBack);
    void create(String token, SceneRole role, ApiCallBack<ResponseBody> callBack);
    void update(String token, SceneRole role, ApiCallBack<ResponseBody> callBack);

    void remove(String userToken, int id, ApiCallBack<ResponseBody> callBack);

}
