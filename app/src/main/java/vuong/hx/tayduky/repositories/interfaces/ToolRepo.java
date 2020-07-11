package vuong.hx.tayduky.repositories.interfaces;

import java.io.File;
import java.util.List;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Tool;

public interface ToolRepo {
    void getAll(ApiCallBack<List<Tool>> callBack);

    void getById(int toolId, ApiCallBack<Tool> callBack);

    void addNew(String token, String toolName, int quantity,
                    String desc, File image, ApiCallBack<ResponseBody> callBack);

    void delete(String token, int toolId, ApiCallBack<ResponseBody> callBack);

    void update(String token, Tool tool, ApiCallBack<ResponseBody> callBack);
}
