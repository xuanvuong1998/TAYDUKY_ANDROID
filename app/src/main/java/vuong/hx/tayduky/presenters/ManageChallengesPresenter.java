package vuong.hx.tayduky.presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.helpers.DateTimeHelper;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.ChallengeCreateModel;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.models.SceneRoleFullInfo;
import vuong.hx.tayduky.models.SceneTool;
import vuong.hx.tayduky.repositories.implementations.ChallengeRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.ChallengeRepo;
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

    public void createNewChallenge(String token, Challenge challenge, List<SceneRole> roles, List<SceneTool> tools) {

        ChallengeCreateModel model = new ChallengeCreateModel(challenge, roles, tools);
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


    public void update(String token, Challenge challenge, List<SceneRole> roles, List<SceneTool> tools) {
        ChallengeCreateModel model = new ChallengeCreateModel(challenge, roles, tools);
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
