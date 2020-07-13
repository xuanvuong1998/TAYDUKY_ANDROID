package vuong.hx.tayduky.presenters;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.repositories.implementations.ActorRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.ActorRepo;
import vuong.hx.tayduky.ui.view_interfaces.ActorsListView;

public class ActorsListPresenter {
    private ActorsListView actorsView;

    private ActorRepo actorRepo;

    public ActorsListPresenter(ActorsListView actorsView) {
        this.actorsView = actorsView;
        actorRepo = new ActorRepoImpl();
    }

    public void setActorsView(ActorsListView actorsView) {
        this.actorsView = actorsView;
    }

    public void loadActorsList(String userToken){
        ActorRepo actorRepo = new ActorRepoImpl();

        actorRepo.getAll(userToken, new ApiCallBack<List<Actor>>() {
            @Override
            public void onSuccess(List<Actor> actors) {
                if (actorsView != null){
                    actorsView.loadActorsList(actors);
                }
            }

            @Override
            public void onFail(String message) {
                if (actorsView != null){
                    actorsView.showToastMessage(message);
                }
            }
        });
    }
}
