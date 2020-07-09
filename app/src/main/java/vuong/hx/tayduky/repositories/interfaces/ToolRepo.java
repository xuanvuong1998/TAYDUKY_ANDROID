package vuong.hx.tayduky.repositories.interfaces;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Tool;

public interface ToolRepo {
    void getAll(ApiCallBack<List<Tool>> callBack);

    void getById(int toolId, ApiCallBack<Tool> callBack);
}
