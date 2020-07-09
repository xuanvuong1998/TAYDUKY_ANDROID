package vuong.hx.tayduky.ui.view_interfaces;

import java.util.List;

import vuong.hx.tayduky.models.Tool;

public interface ManageToolView extends ToastView {
    void loadToolsList(List<Tool> tools);
}
