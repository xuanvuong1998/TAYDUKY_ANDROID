package vuong.hx.tayduky.presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.helpers.DateTimeHelper;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.repositories.implementations.ChallengeRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.ChallengeRepo;
import vuong.hx.tayduky.ui.view_interfaces.ManageChallengeView;

public class ManageChallengesPresenter {
    private ManageChallengeView manageChallengeView;
    private ChallengeRepo challengeRepo;

    public ManageChallengesPresenter(ManageChallengeView manageChallengeView) {
        this.manageChallengeView = manageChallengeView;

        challengeRepo = new ChallengeRepoImpl();
    }

    public void setManageChallengeView(ManageChallengeView manageChallengeView) {
        this.manageChallengeView = manageChallengeView;
    }

    public void loadChallengesList(String token){
        challengeRepo.getAll(new ApiCallBack<List<Challenge>>() {
            @Override
            public void onSuccess(List<Challenge> challenges) {
                Log.println(Log.ASSERT,"CHALLENGES",  challenges.size() + "");
                manageChallengeView.loadChallengesList(challenges);
            }

            @Override
            public void onFail(String message) {
                Log.println(Log.ASSERT,"CHALLENGES",  message);
                manageChallengeView.showToastMessage(message);
            }
        });
    }

    public List<Challenge> filterListByStatus(List<Challenge> list, int filter){
        List<Challenge> filterList = new ArrayList<>();

        if (filter == 0){
            return list;
        }
        else if (filter == 1){ // incoming challenges
            for(Challenge c : list){
                if (c.getEndDate() == null || DateTimeHelper.CompareToNow(c.getEndDate()) >= 0){
                    filterList.add(c);
                }
            }
        }else{
            for(Challenge c : list){
                if (c.getEndDate() != null && DateTimeHelper.CompareToNow(c.getEndDate()) < 0){
                    filterList.add(c);
                }
            }
        }
        return filterList;
    }
}
