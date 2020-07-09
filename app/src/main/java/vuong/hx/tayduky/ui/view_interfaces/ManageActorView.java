package vuong.hx.tayduky.ui.view_interfaces;

import java.util.List;

import vuong.hx.tayduky.models.Tool;

public interface ManageActorView extends ToastView {
    void loadActorsList(List<Tool> tools);
}
