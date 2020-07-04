package vuong.hx.tayduky.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.constants.UserRole;
import vuong.hx.tayduky.helpers.SharePreferenceHelper;
import vuong.hx.tayduky.presenters.LoginPresenter;
import vuong.hx.tayduky.ui.view_interfaces.LoginScreenView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginScreenView{

    private LoginPresenter mLoginPresenter;
    private EditText edtUsername, edtPassword;
    private Button btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (mLoginPresenter == null){
            mLoginPresenter = new LoginPresenter(this);
        }

        initViews();
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (isUserAuthenticated()){
            int role = SharePreferenceHelper.getInt(this, "user_role");

            goToHomeActivity(null, null, role);

            this.finish();
        }

    }

    private boolean isUserAuthenticated(){
        return SharePreferenceHelper.getString(this, "user_id") != null;
    }
    private void initViews(){
        edtUsername =  findViewById(R.id.edtUsername);
        edtPassword =  findViewById(R.id.edtPassword);
        btnLogin =  findViewById(R.id.btnLogin);
        btnSignUp =  findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }
    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnLogin:
                mLoginPresenter.authenticate(edtUsername.getText().toString(),
                                edtPassword.getText().toString());
                break;
            case R.id.btnSignUp:
                break;
        }
    }

    @Override
    public void goToHomeActivity(String username, String token, int role) {
        // Save token to preference

        if (username != null){
            SharePreferenceHelper.putString(this, "user_token", token);
            SharePreferenceHelper.putString(this, "user_id", username);
            SharePreferenceHelper.putInt(this, "user_role", role);
        }
        Intent intent = null;
        if (role == UserRole.ADMIN.getVal()){
            intent = new Intent(this, AdminHomeActivity.class);
        }else if (role == UserRole.ACTOR.getVal()){
            intent = new Intent(this, ActorHomeActivity.class);
        }else{
            // other user
        }

        if (intent != null){
            startActivity(intent);
        }

    }
}
