package vuong.hx.tayduky.presenters;

import java.io.File;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.repositories.implementations.CharacterRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.CharacterRepo;
import vuong.hx.tayduky.ui.view_interfaces.CreateCharacterView;

public class CreateCharacterPresenter {
    private CharacterRepo mCharacterRepo;

    private CreateCharacterView createView;

    public CreateCharacterPresenter(CreateCharacterView createView) {
        this.createView = createView;

        mCharacterRepo = new CharacterRepoImpl();
    }

    public void setCreateView(CreateCharacterView createView) {
        this.createView = createView;
    }

    public void createNewCharacter(String token, String name, String defaultActor, File image){
        mCharacterRepo.createNew(token, name, defaultActor, image, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                createView.showToastMessage("Created!");
                createView.refreshCharacterList();
            }

            @Override
            public void onFail(String message) {
                createView.showToastMessage(message);
            }
        });
    }
}
