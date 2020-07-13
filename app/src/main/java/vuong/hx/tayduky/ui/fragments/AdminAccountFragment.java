package vuong.hx.tayduky.ui.fragments;

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
import vuong.hx.tayduky.constants.SharePreferenceKeys;
import vuong.hx.tayduky.helpers.AuthHelper;
import vuong.hx.tayduky.helpers.SharePreferenceHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Admin;
import vuong.hx.tayduky.repositories.implementations.AdminRepoImpl;
import vuong.hx.tayduky.ui.activities.LoginActivity;


public class AdminAccountFragment extends Fragment {

    private ImageView mImgLogout;
    private TextView mTvUsername, mTvFullname;

    public AdminAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_account, container, false);

        mImgLogout = view.findViewById(R.id.btnLogout);
        mTvFullname = view.findViewById(R.id.tvAdminName);
        mTvUsername = view.findViewById(R.id.tvAdminUsername);

        String username = SharePreferenceHelper
                .getString(getContext(), SharePreferenceKeys.USER_ID);

        String token = SharePreferenceHelper
                .getString(getContext(), SharePreferenceKeys.USER_TOKEN);
        mTvUsername.setText(username);

        new AdminRepoImpl().getInfoById(token,username, new ApiCallBack<Admin>() {
            @Override
            public void onSuccess(Admin admin) {
                mTvFullname.setText(admin.getFullName());
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