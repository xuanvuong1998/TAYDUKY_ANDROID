package vuong.hx.tayduky.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.constants.SharePreferenceKeys;
import vuong.hx.tayduky.constants.UserRole;
import vuong.hx.tayduky.helpers.SharePreferenceHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.presenters.LoginPresenter;
import vuong.hx.tayduky.remote.api.ApiConfig;
import vuong.hx.tayduky.ui.fragments.support.LoadingDialog;
import vuong.hx.tayduky.ui.view_interfaces.LoginScreenView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginScreenView {

    private LoginPresenter mLoginPresenter;
    private EditText edtUsername, edtPassword;
    private Button btnLogin, btnSignUp;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (mLoginPresenter == null) {
            mLoginPresenter = new LoginPresenter(this);
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        initViews();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLoginPresenter.setLoginView(null);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //goToHomeActivity(null, null, null, UserRole.ADMIN.getVal());
        if (isUserAuthenticated()) {
            startLoadingDialog();

            String userId = SharePreferenceHelper.getString(this, SharePreferenceKeys.USER_ID);

            refreshUserToken(userId);
        }
    }

    private void refreshUserToken(String userId) {
        String userPassword = SharePreferenceHelper.getString(this, SharePreferenceKeys.USER_PASSWORD);

        if (userId != null) {
            mLoginPresenter.authenticate(userId, userPassword);
        }
    }

    private void startLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.start();

    }

    private boolean isUserAuthenticated() {

        return SharePreferenceHelper.getString(this,
                SharePreferenceKeys.USER_ID) != null;
    }

    private void initViews() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void showToastMessage(String message) {
        loadingDialog.stop();
        ToastHelper.showLongMess(this, message);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnLogin:
                startLoadingDialog();
                mLoginPresenter.authenticate(edtUsername.getText().toString(),
                        edtPassword.getText().toString());
                break;
            case R.id.btnSignUp:
                break;
        }
    }

    @Override
    public void goToHomeActivity(String username, String password, String token, int role) {

        loadingDialog.stop();

        if (username != null) { // First time
            token = ApiConfig.Apis.Auth.BEARER_PREFIX + token;
            TempDataHelper.setUserToken(token);
            TempDataHelper.setUserId(username);
            SharePreferenceHelper.putString(this, SharePreferenceKeys.USER_PASSWORD, password);
            SharePreferenceHelper.putString(this, SharePreferenceKeys.USER_TOKEN, token);
            SharePreferenceHelper.putString(this, SharePreferenceKeys.USER_ID, username);
            SharePreferenceHelper.putInt(this, SharePreferenceKeys.USER_ROLE, role);
        }
        Intent intent = null;
        if (role == UserRole.ADMIN.getVal()) {
            intent = new Intent(this, AdminHomeActivity.class);
        } else if (role == UserRole.ACTOR.getVal()) {
            intent = new Intent(this, ActorHomeActivity.class);
        } else {
            // other user
        }

        if (intent != null) {
            finish();
            startActivity(intent);
        }

    }

}
