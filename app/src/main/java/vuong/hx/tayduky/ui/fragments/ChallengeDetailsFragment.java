package vuong.hx.tayduky.ui.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.helpers.DateTimeHelper;

public class ChallengeDetailsFragment extends DialogFragment
                    implements View.OnClickListener, DatePickerDialog.OnDateSetListener{
    private EditText mEdtName, mEdtDesc, mEdtStartTime, mEdtEndTime, mEdtShotTimes, mEdtLocation;
    private String mChosenDate;
    private boolean isPickingStartingDate;
    private DatePickerFragment datePicker;



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
        View view = inflater.inflate(R.layout.dialog_fragment_challenge_detail, container, false);

        initViews(view);
        return view;
    }



    private void initViews(View view){
        mEdtName = view.findViewById(R.id.edtChallengeName);
        mEdtDesc = view.findViewById(R.id.edtChallengeDesc);
        mEdtLocation = view.findViewById(R.id.edtChallengeLocation);
        mEdtStartTime = view.findViewById(R.id.edtStartTime);
        //mEdtStartTime.setInputType(0);
        mEdtEndTime = view.findViewById(R.id.edtEndTime);
        //mEdtEndTime.setInputType(0);
        mEdtShotTimes = view.findViewById(R.id.edtShotTime);

        mBtnRoles = view.findViewById(R.id.btnRoles);
        mBtnTools = view.findViewById(R.id.btnTools);
        mBtnSave = view.findViewById(R.id.btnSaveChallenge);
        mBtnCancel = view.findViewById(R.id.btnCancel);

        registerEvents();

    }

    private void registerEvents(){
        mBtnCancel.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mBtnRoles.setOnClickListener(this);
        mBtnTools.setOnClickListener(this);
        mEdtStartTime.setOnClickListener(this);
        mEdtEndTime.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRoles:
                ChallengeRolesDialogFragment fr = new ChallengeRolesDialogFragment();

                fr.show(getActivity().getSupportFragmentManager(), "challenge-roles");
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
                isPickingStartingDate = true;
                datePicker = DatePickerFragment.newInstance(this);
                datePicker.show(getActivity().getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.edtEndTime:
                isPickingStartingDate = false;
                datePicker = DatePickerFragment.newInstance(this);
                datePicker.show(getActivity().getSupportFragmentManager(), "DatePicker");
                break;
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mChosenDate = DateTimeHelper.GetDateString(year, month, dayOfMonth);

        if (isPickingStartingDate){
            mEdtStartTime.setText(mChosenDate);
        }else{
            mEdtEndTime.setText(mChosenDate);
        }
    }
}
