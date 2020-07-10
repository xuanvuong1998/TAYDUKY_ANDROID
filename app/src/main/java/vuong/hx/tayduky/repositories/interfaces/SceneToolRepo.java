package vuong.hx.tayduky.repositories.interfaces;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.models.SceneTool;

public interface SceneToolRepo {

    void getAll(ApiCallBack<List<SceneTool>> callBack);
    void getById(int toolId, ApiCallBack<Character> callBack);
}
