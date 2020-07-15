package vuong.hx.tayduky.ui.fragments.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.constants.ReqCode;
import vuong.hx.tayduky.constants.ReqTag;
import vuong.hx.tayduky.helpers.ImageHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.presenters.ManageActorsPresenter;
import vuong.hx.tayduky.presenters.ManageCharactersPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ActorsListView;
import vuong.hx.tayduky.ui.view_interfaces.CharacterListView;

public class AddRoleDialogFragment extends DialogFragment
                implements View.OnClickListener, ActorsListView, CharacterListView {

    private int mChallengeId;
    private Character mCharacter;
    private Actor mAssignedActor;
    private String mDesc;
    private EditText mEdtDesc;
    private ImageView mImgvCharacter, mImgvAssignedActor;
    private Button mBtnAddRole, mBtnCancel;
    private ManageActorsPresenter mActorsPresenter;
    private ManageCharactersPresenter mCharactersPresenter;
    private List<Actor> mActorsList;
    private List<Character> mCharactersList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_add_role, container, false);

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

        mBtnAddRole = view.findViewById(R.id.btnAddRole);
        mBtnAddRole.setOnClickListener(this);

        mBtnCancel = view.findViewById(R.id.btnCancel);
        mBtnCancel.setOnClickListener(this);

    }

    /**
     * Add role to The Cart
     */
    private void addRoleToCart(){
        
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

                ImageHelper.loadImageFromInternal(mCharacter.getImage(), mImgvCharacter);
            }
        }
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
}
