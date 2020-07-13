package vuong.hx.tayduky.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.ToolsAdapter;
import vuong.hx.tayduky.constants.SharePreferenceKeys;
import vuong.hx.tayduky.helpers.SharePreferenceHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Tool;
import vuong.hx.tayduky.presenters.ManageToolsPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ManageToolView;

public class AdminToolsFragment extends Fragment implements ManageToolView, ToolsAdapter.OnClickItem {

    private final int SELECT_PHOTO = 23;
    private final int CREATE_TOOL = 78;
    private final String DIALOG_CREATE_TOOL_TAG = "create_tool_dialog";


    private ToolsAdapter mToolsAdapter;
    private RecyclerView mRecyclerView;
    private ManageToolsPresenter mPresenter;


    private List<Tool> mToolsList, mToolsFilteredList;
    private Button mBtnAddNew;
    private String mUserToken;

    public AdminToolsFragment() {

    }


    public static AdminToolsFragment newInstance() {
        AdminToolsFragment fragment = new AdminToolsFragment();

        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.setManageToolView(null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_tools, container, false);

        mUserToken = SharePreferenceHelper.getString(getContext(),
                                            SharePreferenceKeys.USER_TOKEN);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        mBtnAddNew = view.findViewById(R.id.btnAddNewTool);
        mRecyclerView = view.findViewById(R.id.rcAdminTools);

        registerEvents();
        initData();
    }


    private void registerEvents(){
        final CreateToolDialogFragment fragment = new CreateToolDialogFragment();

        fragment.setTargetFragment(this, CREATE_TOOL);

        mBtnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.show(getActivity().getSupportFragmentManager(), DIALOG_CREATE_TOOL_TAG);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_TOOL){
            if (resultCode == Activity.RESULT_OK){
                mPresenter.loadToolsList();
            }else if (resultCode == Activity.RESULT_CANCELED){
                showToastMessage("Nothing changed!");
            }
        }

    }

    private void initData(){
        if (mPresenter == null){
            mPresenter = new ManageToolsPresenter(this);
        }
        mPresenter.loadToolsList();
    }

    @Override
    public void loadToolsList(List<Tool> list) {

        mToolsList = list;

        mToolsAdapter = new ToolsAdapter(list, getContext());
        mToolsAdapter.setListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mToolsAdapter);
    }

    @Override
    public void refreshToolsList() {
        mPresenter.loadToolsList();
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }

    @Override
    public void onClickDelete(Tool tool) {
        mPresenter.deleteTool(mUserToken, tool.getId());
    }

    @Override
    public void onClickEdit(Tool tool) {


        //mPresenter.updateTool(mUserToken, tool);
    }
}