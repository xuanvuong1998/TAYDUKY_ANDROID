package vuong.hx.tayduky.ui.fragments.actor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.helpers.AuthHelper;
import vuong.hx.tayduky.helpers.ImageHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.repositories.implementations.ActorRepoImpl;
import vuong.hx.tayduky.ui.activities.LoginActivity;


public class ActorAccountFragment extends Fragment {

    private ImageView mImgLogout;
    private TextView mTvUsername, mTvFullname, mTvEmailAddress;
    private ImageView mImgAvt;

    public ActorAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_actor_account, container, false);

        mImgLogout = view.findViewById(R.id.btnLogout);
        mTvFullname = view.findViewById(R.id.tvActorName);
        mTvUsername = view.findViewById(R.id.tvActorUsername);
        mTvEmailAddress = view.findViewById(R.id.tvAccountEmail);
        mImgAvt = view.findViewById(R.id.imgActorAvt);

        String username = TempDataHelper.getUserId();

        String token = TempDataHelper.getUserToken();
        mTvUsername.setText(username);

        new ActorRepoImpl().getActorById(token,username, new ApiCallBack<Actor>() {
            @Override
            public void onSuccess(Actor actor) {
                mTvFullname.setText(actor.getName());
                mTvEmailAddress.setText(actor.getEmail());
                ImageHelper.loadImageFromInternal(actor.getImage(), mImgAvt);
            }

            @Override
            public void onFail(String message) {
                ToastHelper.showLongMess(getContext(), message);
            }
        });

        mImgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                AuthHelper.removeCredentials(getContext());
                Intent intent = new Intent(getContext(), LoginActivity.class);

                startActivity(intent);
            }
        });

        return view;
    }
}