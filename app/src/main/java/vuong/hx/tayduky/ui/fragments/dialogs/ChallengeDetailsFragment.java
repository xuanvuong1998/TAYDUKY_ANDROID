package vuong.hx.tayduky.ui.fragments.dialogs;

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
import vuong.hx.tayduky.constants.ReqCode;
import vuong.hx.tayduky.constants.ReqTag;
import vuong.hx.tayduky.helpers.DateTimeHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.SceneRole;
import vuong.hx.tayduky.models.SceneTool;
import vuong.hx.tayduky.presenters.ManageChallengesPresenter;
import vuong.hx.tayduky.ui.fragments.support.DatePickerFragment;
import vuong.hx.tayduky.ui.view_interfaces.ChallengeDetailsView;

public class ChallengeDetailsFragment extends DialogFragment
                    implements View.OnClickListener, DatePickerDialog.OnDateSetListener, ChallengeDetailsView {
    private EditText mEdtName, mEdtDesc, mEdtStartTime, mEdtEndTime, mEdtShotTimes, mEdtLocation;
    private Button mBtnRoles, mBtnTools, mBtnSave, mBtnCancel, mBtnPickStartDate, mBtnPickEndDate;
    private String mChosenDate;
    private boolean isPickingStartingDate;
    private DatePickerFragment datePicker;
    private Challenge curChallenge;
    private List<SceneRole> mRoles;
    private List<SceneTool> mTools;
    private ManageChallengesPresenter mPresenter;
    private String mUserToken;

    public static ChallengeDetailsFragment newInstance(Challenge challenge) {
        Bundle args = new Bundle();
        args.putSerializable("currentChallenge", challenge);
        
        ChallengeDetailsFragment fragment = new ChallengeDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new ManageChallengesPresenter(this);
        mUserToken = TempDataHelper.getUserToken();
        curChallenge = (Challenge) getArguments().getSerializable("currentChallenge");
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
        mBtnPickStartDate = view.findViewById(R.id.btnPickStartDate);
        mBtnPickEndDate = view.findViewById(R.id.btnPickEndDate);


        registerEvents();

        if (isCreateNewMode() == false) setData();

        updateUI();

    }

    private void updateUI(){
        if (isCreateNewMode()){ // Create New
            mBtnSave.setText("Create");
            mBtnRoles.setVisibility(View.GONE);
            mBtnTools.setVisibility(View.GONE);
            mEdtShotTimes.setVisibility(View.GONE);

        }else{ // Update
            mBtnSave.setText("Save changes");
            mBtnTools.setVisibility(View.VISIBLE);
            mBtnRoles.setVisibility(View.VISIBLE);
            mEdtShotTimes.setVisibility(View.VISIBLE);
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
        mBtnPickStartDate.setOnClickListener(this);
        mBtnPickEndDate.setOnClickListener(this);

    }

    private void showCreateNewRoleDialog(){
        AddRoleDialogFragment fr = new AddRoleDialogFragment();

        fr.show(getActivity().getSupportFragmentManager(), ReqTag.ADD_ROLE);
    }

    private void showChallengeRolesList(){
        ChallengeRolesDialogFragment fr = ChallengeRolesDialogFragment
                .newInstance(curChallenge);

        fr.show(getActivity().getSupportFragmentManager(), "challenge-roles");
    }

    private boolean isCreateNewMode(){
        return curChallenge ==  null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRoles:
                if (isCreateNewMode()){
                    showCreateNewRoleDialog();
                }else{
                    showChallengeRolesList();
                }

                break;
            case R.id.btnTools:

                break;
            case R.id.btnSaveChallenge:
                saveChanges();
                break;
            case R.id.btnCancel:
                finishAndRequestRefresh();
                break;
            case R.id.btnPickStartDate:
                isPickingStartingDate = true;
                datePicker = DatePickerFragment.newInstance(this);
                datePicker.show(getActivity().getSupportFragmentManager(), "DatePicker");
                break;
            case R.id.btnPickEndDate:
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

            mPresenter.createNewChallenge(mUserToken, newChallenge, null, null);

        }else{ // update
            mPresenter.update(mUserToken, curChallenge, mRoles, mTools);
        }
    }

    private void finishAndRequestRefresh(){
        getTargetFragment().onActivityResult(ReqCode.CHALLENGE_DETAILS
                    , Activity.RESULT_OK, getActivity().getIntent());
        dismiss();
    }

    private void cancel(){
        getTargetFragment().onActivityResult(ReqCode.CHALLENGE_DETAILS
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
    public void notifyModelErr(String mess) {
        ToastHelper.showLongMess(getContext(), mess);
    }

    @Override
    public void notifyCreateSuccess() {
        ToastHelper.showLongMess(getContext(), "created!");

        getTargetFragment().onActivityResult(ReqCode.CHALLENGE_DETAILS,
                            Activity.RESULT_OK, getActivity().getIntent());

        dismiss();
    }
}
