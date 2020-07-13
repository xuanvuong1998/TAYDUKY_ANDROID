package vuong.hx.tayduky.ui.view_interfaces;

import java.util.List;

import vuong.hx.tayduky.models.Actor;

public interface ActorsListView extends ToastView{
    void loadActorsList(List<Actor> actors);
}
