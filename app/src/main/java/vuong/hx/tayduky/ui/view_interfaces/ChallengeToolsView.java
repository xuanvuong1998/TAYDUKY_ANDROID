package vuong.hx.tayduky.ui.view_interfaces;

import java.util.List;

import vuong.hx.tayduky.models.SceneTool;

public interface ChallengeToolsView extends ToastView{
    void loadChallengeTools(List<SceneTool> tools);
    void refreshList();
}
