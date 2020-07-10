package vuong.hx.tayduky.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.ToolsAdapter;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Tool;
import vuong.hx.tayduky.presenters.ManageToolsPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ManageToolView;

public class AdminToolsFragment extends Fragment implements ManageToolView {

    private ToolsAdapter mToolsAdapter;
    private RecyclerView mRecyclerView;
    private ManageToolsPresenter mPresenter;

    private List<Tool> mToolsList, mToolsFilteredList;
    private Button btnAddNew;

    public AdminToolsFragment() {
        // Required empty public constructor
    }


    public static AdminToolsFragment newInstance() {
        AdminToolsFragment fragment = new AdminToolsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_tools, container, false);

        initViews(view);
        return view;
    }

    private void initViews(View view){
        btnAddNew = view.findViewById(R.id.btnAddNewTool);
        mRecyclerView = view.findViewById(R.id.rcAdminTools);


        initData();
    }



    private void initData(){
        if (mPresenter == null){
            mPresenter = new ManageToolsPresenter(this);
        }
        mPresenter.loadToolsList();
    }

    @Override
    public void loadToolsList(List<Tool> list) {

        mToolsAdapter = new ToolsAdapter(list, getContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mToolsAdapter);
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }
}