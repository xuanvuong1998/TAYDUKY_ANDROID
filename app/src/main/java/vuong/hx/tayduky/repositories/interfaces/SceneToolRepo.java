package vuong.hx.tayduky.repositories.interfaces;

import java.util.List;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.SceneTool;

public interface SceneToolRepo {
    void createRange(String token, List<SceneTool> tools, ApiCallBack<ResponseBody> callBack);
    void create(String token, SceneTool tool, ApiCallBack<ResponseBody> callBack);
    void remove(String token, SceneTool tool, ApiCallBack<ResponseBody> callBack);
    void update(String token, SceneTool tool, ApiCallBack<ResponseBody> callBack);
}
