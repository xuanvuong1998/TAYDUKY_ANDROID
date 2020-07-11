package vuong.hx.tayduky.presenters;

import java.io.File;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.repositories.implementations.ToolRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.ToolRepo;
import vuong.hx.tayduky.ui.view_interfaces.CreateToolView;

public class CreateToolPresenter {
    private ToolRepo mToolRepo;

    private CreateToolView mCreateView;

    public CreateToolPresenter(CreateToolView mCreateView) {
        this.mCreateView = mCreateView;

        mToolRepo = new ToolRepoImpl();
    }

    public void createNewTool(String token, String toolName, int quantity, String desc, File image){

        mToolRepo.addNew(token, toolName, quantity, desc,
                image, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                mCreateView.showToastMessage("Created!");
                mCreateView.refreshToolList();
            }

            @Override
            public void onFail(String message) {
                mCreateView.showToastMessage(message);
            }
        });

    }
}
