package vuong.hx.tayduky.presenters;

import java.io.File;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.repositories.implementations.CharacterRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.CharacterRepo;
import vuong.hx.tayduky.ui.view_interfaces.CreateCharacterView;

public class CreateCharacterPresenter {
    private CharacterRepo mCharacterRepo;

    private CreateCharacterView mCreateView;

    public CreateCharacterPresenter(CreateCharacterView mCreateView) {
        this.mCreateView = mCreateView;

        mCharacterRepo = new CharacterRepoImpl();
    }

    public void createNewCharacter(String token, String name, String defaultActor, File image){
        mCharacterRepo.createNew(token, name, defaultActor, image, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                mCreateView.showToastMessage("Created!");
                mCreateView.refreshCharacterList();
            }

            @Override
            public void onFail(String message) {
                mCreateView.showToastMessage(message);
            }
        });
    }
}
