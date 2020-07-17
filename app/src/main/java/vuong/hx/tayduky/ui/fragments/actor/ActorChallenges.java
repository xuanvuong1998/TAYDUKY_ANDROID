package vuong.hx.tayduky.ui.fragments.actor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.ActorRoleAdapter;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.presenters.ManageActorsPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ActorRolesView;

public class ActorChallenges extends Fragment implements ActorRolesView {
    private boolean areIncomingChallenges;
    private ManageActorsPresenter manageActorsPresenter;
    private ActorRoleAdapter mAdapter;
    private RecyclerView mRecyclerView;


    public static ActorChallenges newInstance(boolean areIncomingChallenges) {

        Bundle args = new Bundle();

        args.putBoolean("incoming", areIncomingChallenges);

        ActorChallenges fragment = new ActorChallenges();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manageActorsPresenter = new ManageActorsPresenter(this);
        areIncomingChallenges =  (boolean) getArguments().get("incoming");

        manageActorsPresenter.loadActorRoles(TempDataHelper.getUserId(), areIncomingChallenges);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview_with_swipe_refresh, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }

    @Override
    public void loadRoles(List<SceneRole> roles) {
        mAdapter = new ActorRoleAdapter(roles);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);

    }
}
