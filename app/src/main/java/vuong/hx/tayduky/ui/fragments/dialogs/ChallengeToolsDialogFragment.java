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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.ChallengeToolsAdapter;
import vuong.hx.tayduky.constants.ReqTag;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.SceneTool;
import vuong.hx.tayduky.presenters.ManageChallengesPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ChallengeToolsView;

public class ChallengeToolsDialogFragment extends DialogFragment
                implements ChallengeToolsAdapter.OnClickItem, ChallengeToolsView {

    private ChallengeToolsAdapter mAdapter;
    private ManageChallengesPresenter mPresenter;
    private Button mBtnAddNew, mBtnCancel;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private Challenge mChallenge;

    public static ChallengeToolsDialogFragment newInstance(Challenge challenge) {

        Bundle args = new Bundle();

        args.putSerializable("challenge", challenge);
        ChallengeToolsDialogFragment fragment = new ChallengeToolsDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_challenge_tools, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mBtnAddNew = view.findViewById(R.id.btnAddTool);
        mBtnCancel = view.findViewById(R.id.btnCancel);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_layout);

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mBtnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSceneToolDialogFragment fr = AddSceneToolDialogFragment.newInstance(mChallenge);

                fr.show(getActivity().getSupportFragmentManager(), ReqTag.ADD_ROLE);
            }
        });

        mPresenter = new ManageChallengesPresenter(this);

        mChallenge = (Challenge) getArguments().getSerializable("challenge");

        mPresenter.loadChallengeTools(mChallenge.getId());
        return view;
    }

    @Override
    public void loadChallengeTools(List<SceneTool> tools) {
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter = new ChallengeToolsAdapter(tools, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showToastMessage(String message) {
        mSwipeRefreshLayout.setRefreshing(false);
        ToastHelper.showLongMess(getContext(), message);
    }


    @Override
    public void onDeleteChallengeTool(SceneTool tool) {

    }

    @Override
    public void onSaveChangeTool(SceneTool tool) {

    }
}
