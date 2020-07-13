package vuong.hx.tayduky.presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Tool;
import vuong.hx.tayduky.repositories.implementations.ToolRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.ToolRepo;
import vuong.hx.tayduky.ui.view_interfaces.ManageToolView;

public class ManageToolsPresenter {
    private ManageToolView manageToolView;
    private ToolRepo toolRepo;

    public ManageToolsPresenter(ManageToolView manageToolView) {
        this.manageToolView = manageToolView;

        toolRepo = new ToolRepoImpl();
    }

    public void setManageToolView(ManageToolView manageToolView) {
        this.manageToolView = manageToolView;
    }

    public void deleteTool(String token, int toolId){
        toolRepo.delete(token, toolId, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                manageToolView.showToastMessage("Deleted!");
                manageToolView.refreshToolsList();
            }

            @Override
            public void onFail(String message) {
                manageToolView.showToastMessage(message);
            }
        });
    }

    public void loadToolsList(){

        toolRepo.getAll(new ApiCallBack<List<Tool>>() {
            @Override
            public void onSuccess(List<Tool> Tools) {
                Log.println(Log.ASSERT,"ToolS",  Tools.size() + "");
                manageToolView.loadToolsList(Tools);
            }

            @Override
            public void onFail(String message) {
                Log.println(Log.ASSERT,"ToolS",  message);
                manageToolView.showToastMessage(message);
            }
        });
    }

    public List<Tool> filterListByName(List<Tool> list, String name){
        List<Tool> filterList = new ArrayList<>();

        return list;

    }

    public void updateTool(String token, Tool tool) {

    }
}
