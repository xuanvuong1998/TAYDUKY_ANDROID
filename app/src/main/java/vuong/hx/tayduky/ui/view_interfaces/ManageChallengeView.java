package vuong.hx.tayduky.ui.view_interfaces;

import java.util.List;

import vuong.hx.tayduky.models.Challenge;

public interface ManageChallengeView extends ToastView {
    void loadChallengesList(List<Challenge> challenges);
}
