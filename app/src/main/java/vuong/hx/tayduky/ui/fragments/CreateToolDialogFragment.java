package vuong.hx.tayduky.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.io.IOException;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.constants.SharePreferenceKeys;
import vuong.hx.tayduky.helpers.SharePreferenceHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.presenters.CreateToolPresenter;
import vuong.hx.tayduky.ui.view_interfaces.CreateToolView;


public class CreateToolDialogFragment extends DialogFragment
                implements View.OnFocusChangeListener, CreateToolView {


    private EditText mEdtToolName, mEdtToolQty, mEdtToolDesc;
    private Button mBtnUploadImage, mBtnCancel, mBtnSave;
    private ImageView mImgvToolImage;
    private File mImageFile;
    private CreateToolPresenter mPresenter;

    private final int SELECT_PHOTO = 99;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        View view = inflater.inflate(R.layout.dialog_fragment_create_tool, container, false);


        mEdtToolName = view.findViewById(R.id.edtToolName);
        mEdtToolQty = view.findViewById(R.id.edtToolQty);
        mEdtToolDesc = view.findViewById(R.id.edtToolDesc);
        mEdtToolName.setOnFocusChangeListener(this);
        mBtnUploadImage = view.findViewById(R.id.btnUploadToolImage);

        mBtnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        mImgvToolImage = view.findViewById(R.id.imgvNewTool);

        mBtnCancel = view.findViewById(R.id.btnCancelNewTool);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTargetFragment().onActivityResult(getTargetRequestCode(),
                            Activity.RESULT_CANCELED, getActivity().getIntent());
                dismiss();
            }
        });

        mPresenter = new CreateToolPresenter(this);

        mBtnSave = view.findViewById(R.id.btnSaveNewTool);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userToken = SharePreferenceHelper
                            .getString(getContext(), SharePreferenceKeys.USER_TOKEN);

                mPresenter.createNewTool(userToken, mEdtToolName.getText().toString(),
                                    Integer.parseInt(mEdtToolQty.getText().toString()),
                                    mEdtToolDesc.getText().toString(), mImageFile);
            }
        });

        return view;
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PHOTO);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == SELECT_PHOTO && data != null && data.getData() != null){
                Uri imageUri = data.getData();

                mImageFile = new File(imageUri.getPath());

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                getActivity().getContentResolver(), imageUri );

                    mImgvToolImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //mImgvToolImage.setImageResource(R.drawable.cover);
            }
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.edtToolName){
            if (hasFocus == false){
                InputMethodManager imm = (InputMethodManager)
                            getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }


    @Override
    public void refreshToolList() {
        getTargetFragment().onActivityResult(getTargetRequestCode(),
                Activity.RESULT_OK, getActivity().getIntent() );
        dismiss();
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }
}