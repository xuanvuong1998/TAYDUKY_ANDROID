package vuong.hx.tayduky.ui.view_interfaces;

import java.util.List;

import vuong.hx.tayduky.models.SceneRole;

public interface ActorRolesView extends ToastView {
    void loadRoles(List<SceneRole> roles);
}
