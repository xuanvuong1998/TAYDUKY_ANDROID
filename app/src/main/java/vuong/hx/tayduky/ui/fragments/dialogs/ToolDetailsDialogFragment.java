package vuong.hx.tayduky.ui.fragments.dialogs;

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
import android.view.inputmethod.InputMethodManager;
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

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.constants.ReqCode;
import vuong.hx.tayduky.helpers.FileHelper;
import vuong.hx.tayduky.helpers.ImageHelper;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Tool;
import vuong.hx.tayduky.presenters.CreateToolPresenter;
import vuong.hx.tayduky.ui.view_interfaces.CreateToolView;


public class ToolDetailsDialogFragment extends DialogFragment
        implements View.OnFocusChangeListener, CreateToolView {

    private EditText mEdtToolName, mEdtToolQty, mEdtToolDesc;
    private TextView mTvTitle;

    private Button mBtnUploadImage, mBtnCancel, mBtnSave;
    private ImageView mImgvToolImage;
    private File mImageFile;
    private CreateToolPresenter mPresenter;
    private String mUserToken;

    private Tool mCurTool;

    public static ToolDetailsDialogFragment newInstance(Tool tool) {

        Bundle args = new Bundle();

        args.putSerializable("curTool", tool);
        ToolDetailsDialogFragment fragment = new ToolDetailsDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        View view = inflater.inflate(R.layout.dialog_fragment_tool_details, container, false);

        if (getArguments() != null){
            mCurTool =(Tool) getArguments().getSerializable("curTool");
        }
        mUserToken = TempDataHelper.getUserToken();

        mEdtToolName = view.findViewById(R.id.edtToolName);
        mEdtToolQty = view.findViewById(R.id.edtToolQty);
        mEdtToolDesc = view.findViewById(R.id.edtToolDesc);
        mTvTitle = view.findViewById(R.id.tvToolDetailTitle);
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

                if (isCreateMode()){
                    createTool();

                }else{
                    updateTool();
                }

            }
        });

        if (isCreateMode() == false){
            setData();
        }else{
            mTvTitle.setText("NEW TOOL");
        }
        return view;
    }


    private void updateTool(){
        mCurTool.setQuantity(Integer.parseInt(mEdtToolQty.getText().toString()));
        mCurTool.setDescription(mEdtToolDesc.getText().toString());
        mCurTool.setName(mEdtToolName.getText().toString());
        mPresenter.updateTool(mUserToken, mCurTool, mImageFile);
    }

    private void createTool(){
        mPresenter.createNewTool(mUserToken, mEdtToolName.getText().toString(),
                Integer.parseInt(mEdtToolQty.getText().toString()),
                mEdtToolDesc.getText().toString(), mImageFile);
    }

    private boolean isCreateMode(){
        return mCurTool == null;
    }
    private void setData(){
        mEdtToolName.setText(mCurTool.getName());
        mEdtToolQty.setText(mCurTool.getQuantity() + "");
        mEdtToolDesc.setText(mCurTool.getDescription());
        ImageHelper.loadImageFromInternal(mCurTool.getImage(), mImgvToolImage);

        mTvTitle.setText("Tool Details");
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), ReqCode.SELECT_PHOTO);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ReqCode.SELECT_PHOTO && data != null && data.getData() != null) {
                Uri imageUri = data.getData();


                String filePath = FileHelper.getPath(getActivity(),
                        imageUri);

                try {
                    mImageFile = new File(new URI(filePath));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                            getActivity().getContentResolver(), imageUri);


                    mImgvToolImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.edtToolName) {
            if (hasFocus == false) {
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }


    @Override
    public void refreshToolList() {
        getTargetFragment().onActivityResult(getTargetRequestCode(),
                Activity.RESULT_OK, getActivity().getIntent());
        dismiss();
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //mPresenter.setCreateView(null);
    }
}