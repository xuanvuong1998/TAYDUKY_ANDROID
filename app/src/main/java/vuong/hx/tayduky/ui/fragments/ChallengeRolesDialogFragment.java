package vuong.hx.tayduky.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.ChallengeRolesAdapter;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.SceneRoleFullInfo;
import vuong.hx.tayduky.presenters.ManageChallengesPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ChallengeRolesView;

public class ChallengeRolesDialogFragment extends DialogFragment
                implements ChallengeRolesAdapter.OnClickItem, ChallengeRolesView {

    private ChallengeRolesAdapter mAdapter;
    private ManageChallengesPresenter mPresenter;
    private RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_challenge_roles, container, false);

        mRecyclerView = view.findViewById(R.id.rcChallengeRoles);

        mPresenter = new ManageChallengesPresenter(this);


        mPresenter.loadChallengeRoles(1);
        return view;
    }

    @Override
    public void onClickCharacter() {
        showToastMessage("CLicked character");
    }

    @Override
    public void onClickAssignedActor() {
        showToastMessage("CLicked assigned actor");
    }

    @Override
    public void loadChallengeRoles(List<SceneRoleFullInfo> roles) {
        mAdapter = new ChallengeRolesAdapter(roles, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }
}
