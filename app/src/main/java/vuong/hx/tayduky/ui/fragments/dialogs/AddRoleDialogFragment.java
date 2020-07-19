package vuong.hx.tayduky.ui.fragments.dialogs;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.callbacks.SetDocumentCallBack;
import vuong.hx.tayduky.constants.ReqCode;
import vuong.hx.tayduky.constants.ReqTag;
import vuong.hx.tayduky.helpers.CartHelper;
import vuong.hx.tayduky.helpers.DateTimeHelper;
import vuong.hx.tayduky.helpers.ImageHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.models.SceneRoleFullInfo;
import vuong.hx.tayduky.presenters.ManageActorsPresenter;
import vuong.hx.tayduky.presenters.ManageCharactersPresenter;
import vuong.hx.tayduky.ui.fragments.support.ConfirmGotoCartDialog;
import vuong.hx.tayduky.ui.fragments.support.DatePickerFragment;
import vuong.hx.tayduky.ui.view_interfaces.ActorsListView;
import vuong.hx.tayduky.ui.view_interfaces.CharacterListView;

public class AddRoleDialogFragment extends DialogFragment
                implements View.OnClickListener, ActorsListView, CharacterListView, DatePickerDialog.OnDateSetListener {

    private Character mCharacter;
    private Actor mAssignedActor;
    private EditText mEdtDesc, mEdtJoinedDate;
    private ImageView mImgvCharacter, mImgvAssignedActor;
    private Button mBtnAddRole, mBtnCancel, mBtnPickdate;
    private ManageActorsPresenter mActorsPresenter;
    private ManageCharactersPresenter mCharactersPresenter;
    private List<Actor> mActorsList;
    private DatePickerFragment datePicker;
    private List<Character> mCharactersList;
    private Challenge mChallenge;

    public static AddRoleDialogFragment newInstance(Challenge challenge) {

        Bundle args = new Bundle();

        args.putSerializable("challenge", challenge);

        AddRoleDialogFragment fragment = new AddRoleDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_add_role, container, false);

        mChallenge = (Challenge) getArguments().getSerializable("challenge");
        loadData();

        initViews(view);
        return view;
    }

    private void loadData(){
        mActorsPresenter = new ManageActorsPresenter(this);
        mCharactersPresenter = new ManageCharactersPresenter(this);

        mActorsPresenter.loadActorsList(TempDataHelper.getUserToken());
        mCharactersPresenter.loadCharactersList();

    }


    void initViews(View view){
        mImgvAssignedActor = view.findViewById(R.id.imgvAssignedActor);
        mImgvAssignedActor.setOnClickListener(this);

        mImgvCharacter = view.findViewById(R.id.imgvCharacter);
        mImgvCharacter.setOnClickListener(this);

        mEdtDesc = view.findViewById(R.id.edtRoleDesc);
        mEdtJoinedDate = view.findViewById(R.id.edtStartTime);
        mEdtJoinedDate.setText(mChallenge.getStartDate());

        mBtnAddRole = view.findViewById(R.id.btnAddRole);
        mBtnAddRole.setOnClickListener(this);

        mBtnCancel = view.findViewById(R.id.btnCancel);
        mBtnCancel.setOnClickListener(this);

    }

    /**
     * Add role to The Cart
     */
    private void addRoleToCart(){

        SceneRoleFullInfo newRole = new SceneRoleFullInfo();

        newRole.setAssignedActor(mAssignedActor);
        newRole.setCharacter(mCharacter);
        newRole.setDesc(mEdtDesc.getText().toString());
        newRole.setChallenge(mChallenge);
        newRole.setParticipatedDate(mEdtJoinedDate.getText().toString());

        final Fragment thisFr = this;

        CartHelper.addNewRole(newRole, new SetDocumentCallBack() {
            @Override
            public void onSuccess() {
                ConfirmGotoCartDialog dialog = new ConfirmGotoCartDialog();

                dialog.setTargetFragment(thisFr, ReqCode.CONFIRM_GOTOCART);
                dialog.show(getActivity().getSupportFragmentManager(), "confirm-gotocart");
            }

            @Override
            public void onFail(String message) {
                ToastHelper.showLongMess(getContext(), message);
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.btnAddRole:
                addRoleToCart();
                break;
            case R.id.btnPickStartDate:
                datePicker = new DatePickerFragment();

                datePicker.show(getActivity().getSupportFragmentManager(), "date-picker");

                break;
                
            case R.id.imgvAssignedActor:

                ChooseActorDialogFragment fr = ChooseActorDialogFragment.newInstance(mActorsList);

                fr.setTargetFragment(this, ReqCode.CHOOSE_ACTOR);

                fr.show(getActivity().getSupportFragmentManager(), ReqTag.CHOOSE_ACTOR);

                break;
            case R.id.imgvCharacter:
                ChooseCharacterDialogFragment fr2 = ChooseCharacterDialogFragment.newInstance(mCharactersList);

                fr2.setTargetFragment(this, ReqCode.CHOOSE_CHARACTER);

                fr2.show(getActivity().getSupportFragmentManager(), ReqTag.CHOOSE_CHARACTER);

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ReqCode.CHOOSE_ACTOR){
            if (resultCode == Activity.RESULT_OK){
                mAssignedActor = (Actor) data.getSerializableExtra("chosenActorFullInfo");

                ImageHelper.loadImageFromInternal(mAssignedActor.getImage(), mImgvAssignedActor);
            }
        }
        if (requestCode == ReqCode.CHOOSE_CHARACTER){
            if (resultCode == Activity.RESULT_OK){
                mCharacter= (Character) data.getSerializableExtra("chosenCharacterFullInfo");

                // Get default actor image
                mAssignedActor = getActorById(mCharacter.getDefaultActor());

                ImageHelper.loadImageFromInternal(mCharacter.getImage(), mImgvCharacter);

                if (mAssignedActor != null){
                    ImageHelper.loadImageFromInternal(mAssignedActor.getImage(), mImgvAssignedActor);
                }
            }
        }

        if (requestCode == ReqCode.CONFIRM_GOTOCART){
            if (resultCode == Activity.RESULT_CANCELED){
                resetInputs();
            }
        }
    }

    private Actor getActorById(String actorId){
        for(Actor actor: mActorsList){
            if (actor.getUsername().equals(actorId)){
                return actor;
            }
        }

        return null;
    }

    private void resetInputs(){
        mImgvAssignedActor.setImageResource(R.drawable.no_image);
        mImgvCharacter.setImageResource(R.drawable.no_image);
        mEdtDesc.setText("");
        mAssignedActor = null;
        mCharacter = null;
    }

    @Override
    public void loadActorsList(List<Actor> actors) {
        mActorsList = actors;
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }

    @Override
    public void loadCharactersList(List<Character> characters) {
        mCharactersList = characters;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = DateTimeHelper.GetDateString(year, month, dayOfMonth);

        mEdtJoinedDate.setText(date);
    }
}
