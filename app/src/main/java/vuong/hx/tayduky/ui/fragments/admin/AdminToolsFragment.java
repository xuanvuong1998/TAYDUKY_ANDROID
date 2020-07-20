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
import vuong.hx.tayduky.ui.fragments.dialogs.ToolDetailsDialogFragment;
import vuong.hx.tayduky.ui.fragments.support.LoadingDialog;
import vuong.hx.tayduky.ui.view_interfaces.ManageToolView;

public class AdminToolsFragment extends Fragment implements ManageToolView, ToolsAdapter.OnClickItem {

    private SwipeRefreshLayout mSwipeLayout;

    private ToolsAdapter mToolsAdapter;
    private RecyclerView mRecyclerView;
    private ManageToolsPresenter mPresenter;
    private List<Tool> mToolsList, mToolsFilteredList;
    private Button mBtnAddNew;
    private String mUserToken;
    private LoadingDialog loadingDialog;

    public AdminToolsFragment() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadingDialog = new LoadingDialog(getActivity());
        initData();

    }
    public static AdminToolsFragment newInstance() {
        AdminToolsFragment fragment = new AdminToolsFragment();
        return fragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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

    private void initViews(View view) {
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
    }


    private void registerEvents() {

        mBtnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            final ToolDetailsDialogFragment fragment = ToolDetailsDialogFragment.newInstance(null);
            fragment.setTargetFragment(AdminToolsFragment.this, ReqCode.TOOL_DETAILS);
            fragment.show(getActivity().getSupportFragmentManager(), ReqTag.TOOL_DETAILS_TAG);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ReqCode.TOOL_DETAILS) {
            if (resultCode == Activity.RESULT_OK) {
                mPresenter.loadToolsList();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                showToastMessage("Nothing changed!");
            }
        }

    }

    private void initData() {
        if (mPresenter == null) {
            mPresenter = new ManageToolsPresenter(this);
        }
        loadingDialog.start();
        mPresenter.loadToolsList();
    }

    @Override
    public void loadToolsList(List<Tool> list) {
        loadingDialog.stop();
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
        loadingDialog.start();
        mPresenter.loadToolsList();
    }

    @Override
    public void showToastMessage(String message) {
        loadingDialog.stop();
        mSwipeLayout.setRefreshing(false);
        ToastHelper.showLongMess(getContext(), message);
    }

    @Override
    public void onClickDelete(Tool tool) {
        mPresenter.deleteTool(mUserToken, (int) tool.getId());
    }

    @Override
    public void onClickEdit(Tool tool) {
        final ToolDetailsDialogFragment fragment = ToolDetailsDialogFragment.newInstance(tool);
        fragment.setTargetFragment(AdminToolsFragment.this, ReqCode.TOOL_DETAILS);
        fragment.show(getActivity().getSupportFragmentManager(), ReqTag.TOOL_DETAILS_TAG);
    }
}