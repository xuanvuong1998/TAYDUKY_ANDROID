package vuong.hx.tayduky.ui.fragments;

import android.app.Activity;
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

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.helpers.DateTimeHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.models.SceneTool;
import vuong.hx.tayduky.presenters.ManageChallengesPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ChallengeDetailsView;

public class ChallengeDetailsFragment extends DialogFragment
                    implements View.OnClickListener, DatePickerDialog.OnDateSetListener, ChallengeDetailsView {
    private EditText mEdtName, mEdtDesc, mEdtStartTime, mEdtEndTime, mEdtShotTimes, mEdtLocation;
    private String mChosenDate;
    private boolean isPickingStartingDate;
    private DatePickerFragment datePicker;
    private Challenge curChallenge;
    private List<SceneRole> mRoles;
    private List<SceneTool> mTools;
    private ManageChallengesPresenter mPresenter;
    private String mUserToken;

    private int CHALLENGE_DETAILS = 999;


    public static ChallengeDetailsFragment newInstance(Challenge challenge) {
        Bundle args = new Bundle();
        args.putParcelable("currentChallenge", challenge);
        
        ChallengeDetailsFragment fragment = new ChallengeDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private Button mBtnRoles, mBtnTools, mBtnSave, mBtnCancel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManageChallengesPresenter(this);
        mUserToken = TempDataHelper.getUserToken();
        curChallenge =  getArguments().getParcelable("currentChallenge");
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

        if (curChallenge != null) setData();

    }

    private void updateUI(){
        if (curChallenge == null){ // Create New
            mBtnSave.setText("Create");
            mBtnTools.setText("Add tools");
            mBtnRoles.setText("Add roles");

        }else{ // Update
            mBtnSave.setText("Save changes");
            mBtnRoles.setText("Roles");
            mBtnTools.setText("Tools");
        }
    }
    private void setData(){
        mEdtName.setText(curChallenge.getName());
        mEdtDesc.setText(curChallenge.getDescription());
        mEdtStartTime.setText(curChallenge.getStartDate());
        mEdtEndTime.setText(curChallenge.getEndDate());
        mEdtShotTimes.setText(curChallenge.getShootTimes() + "");
        mEdtLocation.setText(curChallenge.getLocation());
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
                ChallengeRolesDialogFragment fr = ChallengeRolesDialogFragment
                        .newInstance(curChallenge != null ? curChallenge.getId() : null);

                fr.show(getActivity().getSupportFragmentManager(), "challenge-roles");
                break;
            case R.id.btnTools:

                break;
            case R.id.btnSaveChallenge:
                saveChanges();
                break;
            case R.id.btnCancel:
                finishAndRequestRefresh();
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

    private void saveChanges(){
        if (curChallenge == null){ // create new
            Challenge newChallenge = new Challenge();

            newChallenge.setName(mEdtName.getText().toString());
            newChallenge.setDescription(mEdtDesc.getText().toString());
            newChallenge.setLocation(mEdtLocation.getText().toString());
            newChallenge.setStartDate(mEdtStartTime.getText().toString());

            mPresenter.createNewChallenge(mUserToken, newChallenge, mRoles, mTools);

        }else{ // update
            mPresenter.update(mUserToken, curChallenge, mRoles, mTools);
        }
    }

    private void finishAndRequestRefresh(){
        getTargetFragment().onActivityResult(CHALLENGE_DETAILS
                    , Activity.RESULT_OK, getActivity().getIntent());
        dismiss();
    }

    private void cancel(){
        getTargetFragment().onActivityResult(CHALLENGE_DETAILS
                , Activity.RESULT_CANCELED, getActivity().getIntent());
        dismiss();
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


    @Override
    public void notifyModelErr() {
        ToastHelper.showLongMess(getContext(), "Failed! Check data again");
    }

    @Override
    public void notifyCreateSuccess() {
        ToastHelper.showLongMess(getContext(), "created!");
    }
}
