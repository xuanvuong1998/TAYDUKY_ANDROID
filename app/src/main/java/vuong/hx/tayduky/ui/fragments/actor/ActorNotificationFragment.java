package vuong.hx.tayduky.ui.fragments.actor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.NotifAdapter;
import vuong.hx.tayduky.callbacks.NotifCallBack;
import vuong.hx.tayduky.helpers.NotificationHelper;
import vuong.hx.tayduky.models.SceneRoleFullInfo;


public class ActorNotificationFragment extends Fragment implements NotifAdapter.OnClickItem {

    private NotifAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<SceneRoleFullInfo> roles;


    public ActorNotificationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationHelper.onHaveNewNotif(new NotifCallBack() {
            @Override
            public void onHaveNewAssignedRole(SceneRoleFullInfo role) {
                if (roles == null){
                    roles = new ArrayList<>();
                }
                roles.add(role);

                updateAdapter();
            }
        });
    }

    private void updateAdapter(){
        if (mAdapter == null){
            mAdapter = new NotifAdapter(roles, this);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_actor_notification, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);


        return view;
    }


    @Override
    public void onClickNotifDetails(SceneRoleFullInfo role) {

    }
}