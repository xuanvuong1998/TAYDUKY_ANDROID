package vuong.hx.tayduky.repositories.interfaces;

import java.io.File;
import java.util.List;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Character;

public interface CharacterRepo {

    void getAll(ApiCallBack<List<Character>> callBack);

    void createNew(String token, String name, String defaultActor,
                   File image, ApiCallBack<ResponseBody> callBack);

    void update(String token, int id, String name, String defaultActor, File image, ApiCallBack<ResponseBody> callBack);
}
