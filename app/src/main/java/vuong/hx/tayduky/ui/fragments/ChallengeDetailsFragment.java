package vuong.hx.tayduky.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import vuong.hx.tayduky.R;

public class ChallengeDetailsFragment extends DialogFragment implements View.OnClickListener{
    private EditText mEdtName, mEdtDesc, mEdtStartTime, mEdtEndTime, mEdtShotTimes, mEdtLocation;


    public static ChallengeDetailsFragment newInstance(boolean createNewMode) {
        
        Bundle args = new Bundle();
        args.putBoolean("actionMode", createNewMode);
        
        ChallengeDetailsFragment fragment = new ChallengeDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private Button mBtnRoles, mBtnTools, mBtnSave, mBtnCancel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_challenge_detail, container, false);

        initViews(view);
        return view;
    }



    private void initViews(View view){
        mEdtName = view.findViewById(R.id.edtChallengeName);
        mEdtDesc = view.findViewById(R.id.edtChallengeDesc);
        mEdtLocation = view.findViewById(R.id.edtChallengeLocation);
        mEdtStartTime = view.findViewById(R.id.edtStartTime);
        mEdtEndTime = view.findViewById(R.id.edtEndTime);
        mEdtShotTimes = view.findViewById(R.id.edtShotTime);

        mBtnRoles = view.findViewById(R.id.btnRoles);
        mBtnTools = view.findViewById(R.id.btnTools);
        mBtnSave = view.findViewById(R.id.btnSaveChallenge);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRoles:

                break;
            case R.id.btnTools:

                break;
            case R.id.btnSaveChallenge:
                //getTargetFragment().onActivityResult(111, Activity.RESULT_CANCELED, );
                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.edtStartTime:

                break;
            case R.id.edtEndTime:

                break;

        }
    }
}
