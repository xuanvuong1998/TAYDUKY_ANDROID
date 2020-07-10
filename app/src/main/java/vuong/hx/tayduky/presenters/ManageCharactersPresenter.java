package vuong.hx.tayduky.presenters;

import android.util.Log;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.repositories.implementations.CharacterRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.CharacterRepo;
import vuong.hx.tayduky.ui.view_interfaces.ManageCharacterView;

public class ManageCharactersPresenter {
    private ManageCharacterView manageCharacterView;
    private CharacterRepo CharacterRepo;

    public ManageCharactersPresenter(ManageCharacterView manageCharacterView) {
        this.manageCharacterView = manageCharacterView;

        CharacterRepo = new CharacterRepoImpl();
    }

    public void loadCharactersList(){

        CharacterRepo.getAll(new ApiCallBack<List<Character>>() {
            @Override
            public void onSuccess(List<Character> Characters) {
                Log.println(Log.ASSERT,"CharacterS",  Characters.size() + "");
                manageCharacterView.loadCharactersList(Characters);
            }

            @Override
            public void onFail(String message) {
                Log.println(Log.ASSERT,"CharacterS",  message);
                manageCharacterView.showToastMessage(message);
            }
        });
    }

}
