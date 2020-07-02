package vuong.hx.tayduky.repositories.interfaces;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Tool;

public interface ToolRepo {
    void getAll(ApiCallBack<Tool> callBack);

    void getById(int toolId, ApiCallBack<Tool> callBack);
}
