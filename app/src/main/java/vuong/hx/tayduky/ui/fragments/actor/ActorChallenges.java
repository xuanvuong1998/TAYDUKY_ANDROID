package vuong.hx.tayduky.ui.fragments.actor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.ActorRoleAdapter;
import vuong.hx.tayduky.helpers.FileHelper;
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
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Button mBtnDownload;
    private List<SceneRole> mRoles;


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
        areIncomingChallenges = (boolean) getArguments().get("incoming");

        manageActorsPresenter.loadActorRoles(TempDataHelper.getUserId(), areIncomingChallenges);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actor_challenges, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                manageActorsPresenter.loadActorRoles(TempDataHelper.getUserId(), areIncomingChallenges);
            }
        });

        mBtnDownload = view.findViewById(R.id.btnDownloadRoles);
        mBtnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRoles();
                //FileHelper.downloadRoles(getActivity(), "roles134.txt", "TON");
            }
        });

        return view;
    }

    private void showDownloadProgress() {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Exporting....");
        progressDialog.setTitle("Please Wait");
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.setMax(20);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDialog.getProgress() < progressDialog.getMax()) {
                        Thread.sleep(100);
                        progressDialog.incrementProgressBy(1);
                    }
                    if (progressDialog.getProgress() >= progressDialog.getMax()) {
                        progressDialog.dismiss();
                    }
                } catch (Exception e) {

                }
                ;
            }
        }).start();
    }

    private void saveRoles() {
        StringBuilder builder = new StringBuilder();

        for (SceneRole role : mRoles) {
            String roleStr = "Challenge: " + role.getChallenge().getName()
                    + ", Role: " + role.getCharacter().getName()
                    + ", Desc: " + role.getDescription()
                    + ", Time: " + role.getParticipatedDate()
                    + "\n";

            builder.append(roleStr);
        }

        String content = builder.toString();

        String fileName = TempDataHelper.getUserId() + "_roles.txt";

        showDownloadProgress();

        FileHelper.downloadRoles(getActivity(), fileName, content);
    }

    @Override
    public void showToastMessage(String message) {
        mSwipeRefreshLayout.setRefreshing(false);
        ToastHelper.showLongMess(getContext(), message);
    }

    @Override
    public void loadRoles(List<SceneRole> roles) {

        mRoles = roles;
        mSwipeRefreshLayout.setRefreshing(false);
        mAdapter = new ActorRoleAdapter(roles, areIncomingChallenges);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setAdapter(mAdapter);

    }
}
