package vuong.hx.tayduky.ui.fragments;

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
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.presenters.ActorsListPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ActorsListView;

public class AddRoleDialogFragment extends DialogFragment
                implements View.OnClickListener, ActorsListView {

    private int mChallengeId;
    private Character mCharacter;
    private Actor mAssignedActor;
    private String mDesc;
    private EditText mEdtDesc;
    private ImageView mImgvCharacter, mImgvAssignedActor;
    private Button mBtnCreate, mBtnCancel;
    private ActorsListPresenter mActorsPresenter;
    private List<Actor> mActorsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_add_role, container, false);

        mActorsPresenter = new ActorsListPresenter(this);

        mActorsPresenter.loadActorsList(TempDataHelper.getUserToken());

        initViews(view);
        return view;
    }

    void initViews(View view){
        mImgvAssignedActor = view.findViewById(R.id.imgvAssignedActor);
        mImgvCharacter = view.findViewById(R.id.imgvCharacter);

        mEdtDesc = view.findViewById(R.id.edtRoleDesc);
        mBtnCreate = view.findViewById(R.id.btnAddRole);
        mBtnCancel = view.findViewById(R.id.btnCancel);

        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssignActorDialogFragment fr = AssignActorDialogFragment
                                .newInstance( mActorsList);
            }
        });

        mBtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
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
                // add to cart
                break;
            case R.id.imgvAssignedActor:

                AssignActorDialogFragment fr = AssignActorDialogFragment.newInstance(mActorsList);

                fr.setTargetFragment(this, ReqCode.ASSIGN_ACTOR);

                fr.show(getActivity().getSupportFragmentManager(), ReqTag.ASSIGN_ACTOR);

                break;
            case R.id.imgvCharacter:
                //dismiss();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ReqCode.ASSIGN_ACTOR){
            if (requestCode == Activity.RESULT_OK){
                Actor chosenActor = (Actor) data.getSerializableExtra("chosenActorFullInfo");

                ImageHelper.loadImageFromInternal(chosenActor.getImage(), mImgvAssignedActor);
            }
        }
    }

    @Override
    public void loadActorsList(List<Actor> actors) {
        mActorsList = actors;
    }

    @Override
    public void showToastMessage(String message) {

    }
}
