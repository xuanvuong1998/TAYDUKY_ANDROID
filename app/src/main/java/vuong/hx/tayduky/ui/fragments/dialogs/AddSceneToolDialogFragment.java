package vuong.hx.tayduky.ui.fragments.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.callbacks.SetDocumentCallBack;
import vuong.hx.tayduky.constants.ReqCode;
import vuong.hx.tayduky.helpers.CartHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.models.SceneToolFullInfo;
import vuong.hx.tayduky.models.Tool;
import vuong.hx.tayduky.presenters.ManageCharactersPresenter;
import vuong.hx.tayduky.presenters.ManageToolsPresenter;
import vuong.hx.tayduky.ui.fragments.support.ConfirmGotoCartDialog;
import vuong.hx.tayduky.ui.view_interfaces.ManageToolView;

public class AddSceneToolDialogFragment extends DialogFragment
        implements View.OnClickListener, ManageToolView {

    private Button mBtnAddTool, mBtnCancel;
    private EditText mEdtQty;
    private Tool mChosenTool;
    private Spinner mSpnTools;

    private ManageToolsPresenter mToolsPresenter;
    private ManageCharactersPresenter mCharactersPresenter;
    private List<Tool> mToolsList;
    private Challenge mChallenge;

    public static AddSceneToolDialogFragment newInstance(Challenge challenge) {

        Bundle args = new Bundle();

        args.putSerializable("challenge", challenge);

        AddSceneToolDialogFragment fragment = new AddSceneToolDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_add_scene_tool, container, false);

        mChallenge = (Challenge) getArguments().getSerializable("challenge");
        loadData();

        initViews(view);
        return view;
    }

    private void loadData() {
        mToolsPresenter = new ManageToolsPresenter(this);

        mToolsPresenter.loadToolsList();

    }

    void initViews(View view) {

        mSpnTools = view.findViewById(R.id.spnTools);


        mSpnTools.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mChosenTool = mToolsList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mEdtQty = view.findViewById(R.id.edtQuantity);

        mBtnAddTool = view.findViewById(R.id.btnAddTool);
        mBtnAddTool.setOnClickListener(this);

        mBtnCancel = view.findViewById(R.id.btnCancel);
        mBtnCancel.setOnClickListener(this);
        
    }

    /**
     * Add role to The Cart
     */
    private void addToolToCart() {

        SceneToolFullInfo newTool = new SceneToolFullInfo();
        newTool.setChallenge(mChallenge);
        newTool.setQuantity(Integer.parseInt(mEdtQty.getText().toString()));
        newTool.setTool(mChosenTool);

        final Fragment thisFrg = this;
        CartHelper.addNewSceneTool(newTool, new SetDocumentCallBack() {
            @Override
            public void onSuccess() {
                ConfirmGotoCartDialog dialog = new ConfirmGotoCartDialog();
                dialog.setTargetFragment(thisFrg, ReqCode.CONFIRM_GOTOCART);
                dialog.show(getActivity().getSupportFragmentManager(), "confirm-gotocart");
            }

            @Override
            public void onFail(String message) {
                ToastHelper.showLongMess(getContext(), message);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ReqCode.CONFIRM_GOTOCART){
            if (resultCode == Activity.RESULT_CANCELED){
                resetInputs();
            }
        }
    }

    private void resetInputs(){
        mChosenTool = null;
        mEdtQty.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.btnAddTool:
                addToolToCart();
                break;
        }
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }


    private void setSpinnerData() {
        List<String> namesOnly = new ArrayList<>();

        for (Tool tool : mToolsList) {
            namesOnly.add(tool.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, namesOnly);

        mSpnTools.setAdapter(adapter);

    }

    @Override
    public void loadToolsList(List<Tool> tools) {
        mToolsList = tools;

        setSpinnerData();

    }

    @Override
    public void refreshToolsList() {

    }


}
