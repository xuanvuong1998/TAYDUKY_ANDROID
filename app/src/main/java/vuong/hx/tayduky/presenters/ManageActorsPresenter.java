package vuong.hx.tayduky.presenters;

import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.repositories.implementations.ActorRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.ActorRepo;
import vuong.hx.tayduky.ui.view_interfaces.ActorRolesView;
import vuong.hx.tayduky.ui.view_interfaces.ActorsListView;

public class ManageActorsPresenter {
    private ActorsListView actorsView;
    private ActorRepo actorRepo = new ActorRepoImpl();;
    private ActorRolesView rolesView;

    public ManageActorsPresenter(ActorsListView actorsView) {
        this.actorsView = actorsView;
    }

    public ManageActorsPresenter(ActorRolesView rolesView) {
        this.rolesView = rolesView;
    }


    public void setActorsView(ActorsListView actorsView) {
        this.actorsView = actorsView;
    }

    public void loadActorsList(String userToken) {

        if (TempDataHelper.getActors() != null){
            actorsView.loadActorsList(TempDataHelper.getActors());
        }else{
            ActorRepo actorRepo = new ActorRepoImpl();

            actorRepo.getAll(userToken, new ApiCallBack<List<Actor>>() {
                @Override
                public void onSuccess(List<Actor> actors) {
                    TempDataHelper.setActors(actors);
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

    public void loadActorRoles(String actor, boolean incoming){
        if (incoming){
            actorRepo.getIncomingRoles(actor, new ApiCallBack<List<SceneRole>>() {
                @Override
                public void onSuccess(List<SceneRole> sceneRoles) {
                    rolesView.loadRoles(sceneRoles);
                }

                @Override
                public void onFail(String message) {
                    rolesView.showToastMessage(message);
                }
            });
        }else{
            actorRepo.getPlayedRoles(actor, new ApiCallBack<List<SceneRole>>() {
                @Override
                public void onSuccess(List<SceneRole> sceneRoles) {
                    rolesView.loadRoles(sceneRoles);
                }

                @Override
                public void onFail(String message) {
                    rolesView.showToastMessage(message);
                }
            });
        }
    }
}
