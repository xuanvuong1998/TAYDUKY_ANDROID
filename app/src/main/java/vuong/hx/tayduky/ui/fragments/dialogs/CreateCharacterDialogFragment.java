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
import vuong.hx.tayduky.helpers.FileHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.presenters.ManageActorsPresenter;
import vuong.hx.tayduky.presenters.CreateCharacterPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ActorsListView;
import vuong.hx.tayduky.ui.view_interfaces.CreateCharacterView;

public class CreateCharacterDialogFragment extends DialogFragment
                    implements CreateCharacterView, ActorsListView {

    private String mChosenActor = "";
    private Button mBtnCancel, mBtnUploadImage, mBtnSave, mBtnAssignActor;
    private EditText mEdtCharacterName;
    private TextView mTvAssignedActor;
    private String mUserToken;
    private final int CREATE_CHARACTER = 87;
    private final int SELECT_PHOTO = 99;
    private final int CHOOSE_ACTOR = 17;
    private final String ASSIGN_ACTOR_TAG = "assign_actor";
    private ImageView mImgUploaded;
    private CreateCharacterPresenter mCreateCharacterPresenter;
    private ManageActorsPresenter mActorsPresenter;
    private File mFileUploaded;
    private List<Actor> mActorsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = inflater.inflate(R.layout.dialog_fragment_create_character, container, false);

        mUserToken = TempDataHelper.getUserToken();

        mEdtCharacterName = view.findViewById(R.id.edtCharacterName);

        mBtnAssignActor = view.findViewById(R.id.btnAssignActor);
        mBtnCancel = view.findViewById(R.id.btnCancelNewCharacter);
        mBtnSave = view.findViewById(R.id.btnSaveNewCharacter);
        mBtnUploadImage = view.findViewById(R.id.btnUploadCharacterImage);
        mImgUploaded = view.findViewById(R.id.imgvNewCharacter);
        mTvAssignedActor = view.findViewById(R.id.tvAsignedActor);

        mCreateCharacterPresenter = new CreateCharacterPresenter(this);
        mActorsPresenter = new ManageActorsPresenter(this);
        mActorsPresenter.loadActorsList(mUserToken);

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTargetFragment().onActivityResult(CREATE_CHARACTER,
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

                mCreateCharacterPresenter.createNewCharacter(mUserToken,
                            mEdtCharacterName.getText().toString(), mChosenActor, mFileUploaded);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_PHOTO){
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
        }else if (requestCode == CHOOSE_ACTOR){
            mChosenActor =  data.getStringExtra("chosenActor");

            String actorName = "";
            for(Actor actor : mActorsList){
                if (actor.getUsername() == mChosenActor){
                    actorName = actor.getName();
                }
            }

            // Avoid displaying Actor's username on UI

            mTvAssignedActor.setText(actorName);
        }
    }

    private void openFileChooser(){


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent, SELECT_PHOTO);
    }

    @Override
    public void refreshCharacterList() {
        getTargetFragment().onActivityResult(CREATE_CHARACTER,
                        Activity.RESULT_OK, getActivity().getIntent());

        dismiss();
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }

    @Override
    public void loadActorsList(List<Actor> actors) {
        mActorsList = actors;

        final ChooseActorDialogFragment fragment =
                ChooseActorDialogFragment.newInstance( mActorsList);

        fragment.setTargetFragment(this, CHOOSE_ACTOR);

        mBtnAssignActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.show(getActivity().getSupportFragmentManager(), ASSIGN_ACTOR_TAG);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Clean views
        mActorsPresenter.setActorsView(null);
        mCreateCharacterPresenter.setCreateView(null);
    }
}
