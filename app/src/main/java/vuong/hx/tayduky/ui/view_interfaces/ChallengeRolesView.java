package vuong.hx.tayduky.ui.view_interfaces;

import java.util.List;

import vuong.hx.tayduky.models.SceneRoleFullInfo;

public interface ChallengeRolesView extends ToastView{
    void loadChallengeRoles(List<SceneRoleFullInfo> roles);

    void refreshTheList();
}
