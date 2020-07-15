package vuong.hx.tayduky.ui.fragments.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.ChallengeRolesAdapter;
import vuong.hx.tayduky.constants.ReqTag;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.SceneRoleFullInfo;
import vuong.hx.tayduky.presenters.ManageChallengesPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ChallengeRolesView;

public class ChallengeRolesDialogFragment extends DialogFragment
                implements ChallengeRolesAdapter.OnClickItem, ChallengeRolesView {

    private ChallengeRolesAdapter mAdapter;
    private ManageChallengesPresenter mPresenter;
    private Button mBtnAddNew, mBtnCancel;
    private RecyclerView mRecyclerView;
    private int mChallengeId;

    public static ChallengeRolesDialogFragment newInstance(int challengeId) {

        Bundle args = new Bundle();
        args.putInt("challengeId", challengeId);
        ChallengeRolesDialogFragment fragment = new ChallengeRolesDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_challenge_roles, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mBtnAddNew = view.findViewById(R.id.btnAddRole);
        mBtnCancel = view.findViewById(R.id.btnCancel);

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mBtnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRoleDialogFragment fr = new AddRoleDialogFragment();

                fr.show(getActivity().getSupportFragmentManager(), ReqTag.ADD_ROLE);
            }
        });
        mPresenter = new ManageChallengesPresenter(this);

        mChallengeId = getArguments().getInt("challengeId");
        mPresenter.loadChallengeRoles(mChallengeId);
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
