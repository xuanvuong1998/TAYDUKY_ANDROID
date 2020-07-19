package vuong.hx.tayduky.presenters;

import java.io.File;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.repositories.implementations.CharacterRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.CharacterRepo;
import vuong.hx.tayduky.ui.view_interfaces.CharacterView;

public class CharacterPresenter {
    private CharacterRepo mCharacterRepo;

    private CharacterView characterView;

    public CharacterPresenter(CharacterView characterView) {
        this.characterView = characterView;

        mCharacterRepo = new CharacterRepoImpl();
    }

    public void setCreateView(CharacterView characterView) {
        this.characterView = characterView;
    }

    public void createNewCharacter(String token, String name, String defaultActor, File image){
        mCharacterRepo.createNew(token, name, defaultActor, image, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                characterView.showToastMessage("Created!");
                characterView.notifySaveSuccessfully();
            }

            @Override
            public void onFail(String message) {
                characterView.showToastMessage(message);
            }
        });
    }
    public void update(String token, int id, String name, String defaultActor, File image){
        mCharacterRepo.update(token,id, name, defaultActor, image, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                characterView.showToastMessage("Updated!");
                characterView.notifySaveSuccessfully();
            }

            @Override
            public void onFail(String message) {
                characterView.showToastMessage(message);
            }
        });
    }
}
