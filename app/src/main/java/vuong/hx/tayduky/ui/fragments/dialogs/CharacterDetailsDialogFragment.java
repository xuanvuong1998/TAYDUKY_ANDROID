package vuong.hx.tayduky.ui.fragments.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.constants.ReqCode;
import vuong.hx.tayduky.constants.ReqTag;
import vuong.hx.tayduky.helpers.FileHelper;
import vuong.hx.tayduky.helpers.ImageHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.presenters.CharacterPresenter;
import vuong.hx.tayduky.presenters.ManageActorsPresenter;
import vuong.hx.tayduky.ui.fragments.support.LoadingDialog;
import vuong.hx.tayduky.ui.view_interfaces.ActorsListView;
import vuong.hx.tayduky.ui.view_interfaces.CharacterView;

public class CharacterDetailsDialogFragment extends DialogFragment
                    implements CharacterView, ActorsListView {

    private Actor mChosenActor;
    private Button mBtnCancel, mBtnUploadImage, mBtnSave, mBtnAssignActor;
    private EditText mEdtCharacterName;
    private TextView mTvAssignedActor;
    private String mUserToken;
    private ImageView mImgUploaded;
    private CharacterPresenter mCharacterPresenter;
    private ManageActorsPresenter mActorsPresenter;
    private File mFileUploaded;
    private List<Actor> mActorsList;
    private Character mCurCharacter;
    private LoadingDialog loadingDialog;

    public static CharacterDetailsDialogFragment newInstance(Character character) {

        Bundle args = new Bundle();
        args.putSerializable("curCharacter", character);

        CharacterDetailsDialogFragment fragment = new CharacterDetailsDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadingDialog = new LoadingDialog(getActivity());

        loadingDialog.start();
        mActorsPresenter.loadActorsList(mUserToken);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = inflater.inflate(R.layout.dialog_fragment_character_details, container, false);

        mCurCharacter = (Character) getArguments().getSerializable("curCharacter");
        mUserToken = TempDataHelper.getUserToken();

        mEdtCharacterName = view.findViewById(R.id.edtCharacterName);

        mBtnAssignActor = view.findViewById(R.id.btnAssignActor);
        mBtnCancel = view.findViewById(R.id.btnCancelNewCharacter);
        mBtnSave = view.findViewById(R.id.btnSaveNewCharacter);
        mBtnUploadImage = view.findViewById(R.id.btnUploadCharacterImage);
        mImgUploaded = view.findViewById(R.id.imgvNewCharacter);
        mTvAssignedActor = view.findViewById(R.id.tvAsignedActor);

        mCharacterPresenter = new CharacterPresenter(this);
        mActorsPresenter = new ManageActorsPresenter(this);


        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTargetFragment().onActivityResult(ReqCode.CHARACTER_DETAILS,
                                    Activity.RESULT_CANCELED, getActivity().getIntent());
                dismiss();
            }
        });

        mBtnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCreateNew()){
                    createNew();
                }else{
                    update();
                }
            }
        });

        if (isCreateNew() == false){
            setData();
        }
        return view;
    }

    private void createNew(){

        String actorUsername = null;

        if (mChosenActor != null) {
            actorUsername = mChosenActor.getUsername();
        }

        mCharacterPresenter.createNewCharacter(mUserToken,
                mEdtCharacterName.getText().toString(), actorUsername, mFileUploaded);
    }

    private void update(){
        mCharacterPresenter.update(mUserToken, (int) mCurCharacter.getId(),
                mEdtCharacterName.getText().toString(),
                            mTvAssignedActor.getText().toString(), mFileUploaded );
    }

    private boolean isCreateNew(){
        return mCurCharacter == null;
    }
    private void setData(){
        mEdtCharacterName.setText(mCurCharacter.getName());
        ImageHelper.loadImageFromInternal(mCurCharacter.getImage(), mImgUploaded);

        mTvAssignedActor.setText(mCurCharacter.getDefaultActor());

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ReqCode.SELECT_PHOTO){
            if (resultCode == Activity.RESULT_OK){
                Uri contentUri = data.getData();

                String filePath = FileHelper.getPath(getContext(), contentUri);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                            getContext().getContentResolver(), contentUri);

                    mImgUploaded.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    mFileUploaded = new File(new URI(filePath));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

            }
        }else if (requestCode == ReqCode.CHOOSE_ACTOR){
            mChosenActor = (Actor) data.getSerializableExtra("chosenActorFullInfo");

            if (mChosenActor != null){
                mTvAssignedActor.setText(mChosenActor.getUsername());
            }
        }
    }

    private void openFileChooser(){


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent, ReqCode.SELECT_PHOTO);
    }

    @Override
    public void notifySaveSuccessfully() {
        getTargetFragment().onActivityResult(ReqCode.CHARACTER_DETAILS,
                        Activity.RESULT_OK, getActivity().getIntent());

        dismiss();
    }

    @Override
    public void showToastMessage(String message) {
        loadingDialog.stop();
        ToastHelper.showLongMess(getContext(), message);
    }

    @Override
    public void loadActorsList(List<Actor> actors) {
        loadingDialog.stop();
        mActorsList = actors;

        final ChooseActorDialogFragment fragment =
                ChooseActorDialogFragment.newInstance( mActorsList);

        fragment.setTargetFragment(this, ReqCode.CHOOSE_ACTOR);


        mBtnAssignActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.show(getActivity().getSupportFragmentManager(), ReqTag.CHOOSE_ACTOR);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        /*// Clean views
        mActorsPresenter.setActorsView(null);
        mCreateCharacterPresenter.setCreateView(null);*/
    }
}
