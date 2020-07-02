package vuong.hx.tayduky.repositories.interfaces;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.models.SceneTool;

public interface SceneToolRepo {

    void getAll(String token, ApiCallBack<List<SceneTool>> callBack);
    void getById(String token, int toolId, ApiCallBack<Character> callBack);
}
