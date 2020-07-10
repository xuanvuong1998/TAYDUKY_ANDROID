package vuong.hx.tayduky.repositories.interfaces;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Character;

public interface CharacterRepo {

    void getAll(ApiCallBack<List<Character>> callBack);

    void getById(String token, int toolId, ApiCallBack<Character> callBack);
}
