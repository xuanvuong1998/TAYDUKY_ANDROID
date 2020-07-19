package vuong.hx.tayduky.presenters;

import java.io.File;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Tool;
import vuong.hx.tayduky.repositories.implementations.ToolRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.ToolRepo;
import vuong.hx.tayduky.ui.view_interfaces.CreateToolView;

public class CreateToolPresenter {
    private ToolRepo mToolRepo;

    private CreateToolView createView;

    public CreateToolPresenter(CreateToolView createView) {
        this.createView = createView;

        mToolRepo = new ToolRepoImpl();
    }

    public void setCreateView(CreateToolView createView) {
        this.createView = createView;
    }

    public void createNewTool(String token, String toolName, int quantity, String desc, File image){

        mToolRepo.addNew(token, toolName, quantity, desc,
                image, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                createView.showToastMessage("Created!");
                createView.refreshToolList();
            }

            @Override
            public void onFail(String message) {
                createView.showToastMessage(message);
            }
        });
    }

    public void updateTool(String token, Tool tool, File image){

        mToolRepo.update(token, tool,
                image, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                createView.showToastMessage("Updated!");
                createView.refreshToolList();
            }

            @Override
            public void onFail(String message) {
                createView.showToastMessage(message);
            }
        });
    }
}
