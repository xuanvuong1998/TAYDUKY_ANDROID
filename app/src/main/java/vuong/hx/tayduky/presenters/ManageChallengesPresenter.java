package vuong.hx.tayduky.presenters;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.helpers.DateTimeHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.models.SceneRoleFullInfo;
import vuong.hx.tayduky.models.SceneTool;
import vuong.hx.tayduky.repositories.implementations.ChallengeRepoImpl;
import vuong.hx.tayduky.repositories.implementations.SceneRoleRepoImpl;
import vuong.hx.tayduky.repositories.implementations.SceneToolRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.ChallengeRepo;
import vuong.hx.tayduky.repositories.interfaces.SceneRoleRepo;
import vuong.hx.tayduky.repositories.interfaces.SceneToolRepo;
import vuong.hx.tayduky.ui.view_interfaces.ChallengeDetailsView;
import vuong.hx.tayduky.ui.view_interfaces.ChallengeRolesView;
import vuong.hx.tayduky.ui.view_interfaces.ChallengeToolsView;
import vuong.hx.tayduky.ui.view_interfaces.ManageChallengeView;

public class ManageChallengesPresenter {
    private ManageChallengeView manageChallengeView;
    private ChallengeRolesView rolesView;
    private ChallengeToolsView toolsView;
    private ChallengeDetailsView mDetailsView;

    private ChallengeRepo challengeRepo = new ChallengeRepoImpl();
    private SceneRoleRepo roleRepo = new SceneRoleRepoImpl();
    private SceneToolRepo sceToolRepo = new SceneToolRepoImpl();

    public ManageChallengesPresenter(ManageChallengeView manageChallengeView) {
        this.manageChallengeView = manageChallengeView;
    }

    public ManageChallengesPresenter(ChallengeDetailsView mDetailsView) {
        this.mDetailsView = mDetailsView;
    }


    public ManageChallengesPresenter(ChallengeRolesView rolesView) {
        this.rolesView = rolesView;
    }
    public ManageChallengesPresenter(ChallengeToolsView toolView) {
        this.toolsView = toolView;
    }

    public void setManageChallengeView(ManageChallengeView manageChallengeView) {
        this.manageChallengeView = manageChallengeView;
    }

    public void loadChallengeRoles(int challengeId) {
        challengeRepo.getChallengeRoles(challengeId, new ApiCallBack<List<SceneRoleFullInfo>>() {
            @Override
            public void onSuccess(List<SceneRoleFullInfo> sceneRoleFullInfos) {
                rolesView.loadChallengeRoles(sceneRoleFullInfos);
            }

            @Override
            public void onFail(String message) {
                rolesView.showToastMessage(message);
            }
        });
    }

    public void updateRole(SceneRole role){
        roleRepo.update(TempDataHelper.getUserToken(), role, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                rolesView.refreshTheList();
                rolesView.showToastMessage("Updated!");
            }

            @Override
            public void onFail(String message) {
                rolesView.showToastMessage(message);
            }
        });
    }

    public void updateTool(SceneTool tool){
        sceToolRepo.update(TempDataHelper.getUserToken(), tool, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                toolsView.refreshList();
                toolsView.showToastMessage("Updated!");
            }

            @Override
            public void onFail(String message) {
                toolsView.showToastMessage(message);
            }
        });
    }

    public void removeTool(SceneTool tool) {
        sceToolRepo.remove(TempDataHelper.getUserToken(), tool, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                toolsView.refreshList();
                toolsView.showToastMessage("Removed!");
            }

            @Override
            public void onFail(String message) {
                toolsView.showToastMessage(message);
            }
        });
    }

    public void removeRole(SceneRoleFullInfo role){
        roleRepo.remove(TempDataHelper.getUserToken(), role.getId(), new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                rolesView.refreshTheList();
                rolesView.showToastMessage("Removed!");
            }

            @Override
            public void onFail(String message) {
                rolesView.showToastMessage(message);
            }
        });
    }

    public void loadChallengeTools(int challengeId){
        challengeRepo.getChallengeTools(challengeId, new ApiCallBack<List<SceneTool>>() {
            @Override
            public void onSuccess(List<SceneTool> sceneTools) {
                toolsView.loadChallengeTools(sceneTools);
            }

            @Override
            public void onFail(String message) {
                toolsView.showToastMessage(message);
            }
        });
    }

    public void loadChallengesList(String token) {
        challengeRepo.getAll(new ApiCallBack<List<Challenge>>() {
            @Override
            public void onSuccess(List<Challenge> challenges) {
                Log.println(Log.ASSERT, "CHALLENGES", challenges.size() + "");
                manageChallengeView.loadChallengesList(challenges);
            }

            @Override
            public void onFail(String message) {
                Log.println(Log.ASSERT, "CHALLENGES", message);
                manageChallengeView.showToastMessage(message);
            }
        });
    }

    public void createNewChallenge(String token, Challenge model) {

        challengeRepo.createNewChallenge(token, model, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                mDetailsView.notifyCreateSuccess();
            }

            @Override
            public void onFail(String message) {
                mDetailsView.notifyModelErr(message);
            }
        });
    }


    public void update(String token, Challenge model) {
        challengeRepo.updateChallenge(token, model, new ApiCallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                mDetailsView.notifyCreateSuccess();
            }

            @Override
            public void onFail(String message) {
                mDetailsView.notifyModelErr(message);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Challenge> filterListByStatus(List<Challenge> list, int filter) {
        List<Challenge> filterList = new ArrayList<>();

        if (filter == 0) {
            return list;
        } else if (filter == 1) { // incoming challenges
            for (Challenge c : list) {
                if (c.getEndDate() == null || DateTimeHelper.CompareToNow(c.getEndDate()) >= 0) {
                    filterList.add(c);
                }
            }
            filterList.sort(new Comparator<Challenge>() {
                @Override
                public int compare(Challenge o1, Challenge o2) {
                    return DateTimeHelper.Compare(o1.getStartDate(), o2.getStartDate());
                }
            });
        } else {
            for (Challenge c : list) {
                if (c.getEndDate() != null && DateTimeHelper.CompareToNow(c.getEndDate()) < 0) {
                    filterList.add(c);
                }
            }
        }
        return filterList;
    }


}
