package vuong.hx.tayduky.repositories.implementations;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.repositories.interfaces.CharacterRepo;

public class CharacterRepoImpl implements CharacterRepo {

    @Override
    public void getAll(String token, ApiCallBack<List<Character>> callBack) {

    }

    @Override
    public void getById(String token, int toolId, ApiCallBack<Character> callBack) {

    }
}
