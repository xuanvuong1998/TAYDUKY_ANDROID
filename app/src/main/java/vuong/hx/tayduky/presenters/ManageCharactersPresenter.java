package vuong.hx.tayduky.presenters;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.repositories.implementations.CharacterRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.CharacterRepo;
import vuong.hx.tayduky.ui.view_interfaces.ManageCharacterView;

public class ManageCharactersPresenter {
    private ManageCharacterView manageCharacterView;
    private CharacterRepo mCharacterRepo;

    public ManageCharactersPresenter(ManageCharacterView manageCharacterView) {
        this.manageCharacterView = manageCharacterView;
        mCharacterRepo = new CharacterRepoImpl();
    }

    public void loadCharactersList(){

        mCharacterRepo.getAll(new ApiCallBack<List<Character>>() {
            @Override
            public void onSuccess(List<Character> Characters) {
                manageCharacterView.loadCharactersList(Characters);
            }

            @Override
            public void onFail(String message) {
                manageCharacterView.showToastMessage(message);
            }
        });
    }

    public void setManageCharacterView(ManageCharacterView manageCharacterView) {
        this.manageCharacterView = manageCharacterView;
    }
}
