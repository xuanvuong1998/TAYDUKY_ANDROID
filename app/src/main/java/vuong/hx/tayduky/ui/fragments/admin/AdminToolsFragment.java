package vuong.hx.tayduky.ui.fragments.admin;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.ToolsAdapter;
import vuong.hx.tayduky.constants.ReqCode;
import vuong.hx.tayduky.constants.ReqTag;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Tool;
import vuong.hx.tayduky.presenters.ManageToolsPresenter;
import vuong.hx.tayduky.ui.fragments.dialogs.CreateToolDialogFragment;
import vuong.hx.tayduky.ui.view_interfaces.ManageToolView;

public class AdminToolsFragment extends Fragment implements ManageToolView, ToolsAdapter.OnClickItem {

    private SwipeRefreshLayout mSwipeLayout;

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
        //mPresenter.setManageToolView(null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_tools, container, false);

        mUserToken = TempDataHelper.getUserToken();
        initViews(view);
        return view;
    }

    private void initViews(View view){
        mBtnAddNew = view.findViewById(R.id.btnAddNewTool);
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mSwipeLayout = view.findViewById(R.id.swipe_layout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadToolsList();
            }
        });

        registerEvents();
        initData();
    }


    private void registerEvents(){
        final CreateToolDialogFragment fragment = new CreateToolDialogFragment();

        fragment.setTargetFragment(this, ReqCode.CREATE_TOOL);

        mBtnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.show(getActivity().getSupportFragmentManager(), ReqTag.CREATE_TOOL_TAG);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ReqCode.CREATE_TOOL){
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

        mSwipeLayout.setRefreshing(false);
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
        mSwipeLayout.setRefreshing(false);
        ToastHelper.showLongMess(getContext(), message);
    }

    @Override
    public void onClickDelete(Tool tool) {
        mPresenter.deleteTool(mUserToken, (int) tool.getId());
    }

    @Override
    public void onClickEdit(Tool tool) {


        //mPresenter.updateTool(mUserToken, tool);
    }
}