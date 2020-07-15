package vuong.hx.tayduky.presenters;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.repositories.implementations.CharacterRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.CharacterRepo;
import vuong.hx.tayduky.ui.view_interfaces.CharacterListView;

public class ManageCharactersPresenter {
    private CharacterListView characterListView;
    private CharacterRepo mCharacterRepo;

    public ManageCharactersPresenter(CharacterListView characterListView) {
        this.characterListView = characterListView;
        mCharacterRepo = new CharacterRepoImpl();
    }

    public void loadCharactersList(){

        mCharacterRepo.getAll(new ApiCallBack<List<Character>>() {
            @Override
            public void onSuccess(List<Character> Characters) {
                characterListView.loadCharactersList(Characters);
            }

            @Override
            public void onFail(String message) {
                characterListView.showToastMessage(message);
            }
        });
    }

    public void setCharacterListView(CharacterListView characterListView) {
        this.characterListView = characterListView;
    }
}
