package vuong.hx.tayduky.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.callbacks.ApiCallBack;
import vuong.hx.tayduky.constants.SharePreferenceKeys;
import vuong.hx.tayduky.helpers.FileHelper;
import vuong.hx.tayduky.helpers.SharePreferenceHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Actor;
import vuong.hx.tayduky.presenters.CreateCharacterPresenter;
import vuong.hx.tayduky.repositories.implementations.ActorRepoImpl;
import vuong.hx.tayduky.repositories.interfaces.ActorRepo;
import vuong.hx.tayduky.ui.view_interfaces.CreateCharacterView;

public class CreateCharacterDialogFragment extends DialogFragment
                    implements CreateCharacterView {

    private Actor mChosenActor;
    private Button mBtnCancel, mBtnUploadImage, mBtnSave;
    private Spinner mSpActors;
    private EditText mEdtCharacterName;
    private String mUserToken;
    private final int CREATE_CHARACTER = 87;
    private final int SELECT_PHOTO = 99;
    private ImageView mImgUploaded;
    private CreateCharacterPresenter mPresenter;
    private File mFileUploaded;
    private List<Actor> mActorsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = inflater.inflate(R.layout.dialog_fragment_create_character, container, false);

        mUserToken = SharePreferenceHelper.getString(getContext(), SharePreferenceKeys.USER_TOKEN);

        mEdtCharacterName = view.findViewById(R.id.edtCharacterName);

        mSpActors = view.findViewById(R.id.spActors);


        mBtnCancel = view.findViewById(R.id.btnCancelNewCharacter);
        mBtnSave = view.findViewById(R.id.btnSaveNewCharacter);
        mBtnUploadImage = view.findViewById(R.id.btnUploadCharacterImage);
        mImgUploaded = view.findViewById(R.id.imgvNewCharacter);

        mPresenter = new CreateCharacterPresenter(this);

        loadActorsList();

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

                String chosenActorName = "";
                if (mChosenActor != null){
                    chosenActorName = mChosenActor.getUsername();
                }
                mPresenter.createNewCharacter(mUserToken,
                            mEdtCharacterName.getText().toString(), chosenActorName, mFileUploaded);
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
        }
    }

    private void setActorSpinnerAdapter(){
        List<String> actorNames = new ArrayList<>();

        for(Actor actor: mActorsList){
            actorNames.add(actor.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                        getContext(), android.R.layout.simple_list_item_1, actorNames);

        mSpActors.setAdapter(arrayAdapter);

        mSpActors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mChosenActor = mActorsList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void loadActorsList(){
        ActorRepo actorRepo = new ActorRepoImpl();

        actorRepo.getAll(new ApiCallBack<List<Actor>>() {
            @Override
            public void onSuccess(List<Actor> actors) {
                mActorsList = actors;
                setActorSpinnerAdapter();
            }

            @Override
            public void onFail(String message) {
                showToastMessage(message);
            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent, SELECT_PHOTO);
    }

    @Override
    public void refreshCharacterList() {
        getTargetFragment().onActivityResult(SELECT_PHOTO,
                        Activity.RESULT_OK, getActivity().getIntent());

        dismiss();
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }
}
