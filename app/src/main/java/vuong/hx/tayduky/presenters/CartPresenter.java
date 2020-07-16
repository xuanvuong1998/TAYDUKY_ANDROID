package vuong.hx.tayduky.presenters;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.helpers.CartHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.models.SceneRoleFullInfo;
import vuong.hx.tayduky.models.SceneTool;
import vuong.hx.tayduky.models.SceneToolFullInfo;
import vuong.hx.tayduky.repositories.implementations.SceneRoleRepoImpl;
import vuong.hx.tayduky.repositories.implementations.SceneToolRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.SceneRoleRepo;
import vuong.hx.tayduky.repositories.interfaces.SceneToolRepo;
import vuong.hx.tayduky.ui.view_interfaces.CartView;

public class CartPresenter {
    private CartView cartView;
    private SceneToolRepo toolRepo;
    private SceneRoleRepo roleRepo;

    public CartPresenter(CartView cartView) {
        this.cartView = cartView;
        toolRepo = new SceneToolRepoImpl();
        roleRepo = new SceneRoleRepoImpl();
    }

    public void checkoutRoles(List<SceneRoleFullInfo> roles){

        CartHelper.checkoutRoles();

        List<SceneRole> newList = new ArrayList<>();

        for(SceneRoleFullInfo info : roles){
            SceneRole r = new SceneRole();

            r.setChallengeId(info.getChallenge().getId());
            r.setAssignedActor(info.getAssignedActor().getUsername());
            r.setDescription(info.getDesc());
            r.setParticipatedDate(info.getParticipatedDate());

            newList.add(r);
        }


        roleRepo.createRange(TempDataHelper.getUserToken(), newList, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                cartView.removeRolesInCart();
            }

            @Override
            public void onFail(String message) {
                cartView.showToastMessage(message);
            }
        });

    }
    public void checkoutTools(List<SceneToolFullInfo> tools){

        CartHelper.checkoutTools();

        List<SceneTool> newList = new ArrayList<>();

        for(SceneToolFullInfo info : tools){
            SceneTool t = new SceneTool();
            t.setChallengeId(info.getChallenge().getId());
            t.setQuantity(info.getQuantity());
            t.setToolId(info.getTool().getId());

            newList.add(t);
        }
        toolRepo.createRange(TempDataHelper.getUserToken(), newList, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                cartView.removeToolsInCart();
            }

            @Override
            public void onFail(String message) {
                cartView.showToastMessage(message);
            }
        });
    }
}
