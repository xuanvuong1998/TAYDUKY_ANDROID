package vuong.hx.tayduky.ui.fragments.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.CartRolesAdapter;
import vuong.hx.tayduky.callbacks.QuerySnapshotCallBack;
import vuong.hx.tayduky.helpers.CartHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.SceneRoleFullInfo;
import vuong.hx.tayduky.presenters.CartPresenter;
import vuong.hx.tayduky.ui.fragments.support.LoadingDialog;
import vuong.hx.tayduky.ui.view_interfaces.CartView;

public class CartRolesFragment extends Fragment implements CartRolesAdapter.OnClickItem, CartView {

    private List<SceneRoleFullInfo> mRoles;

    private RecyclerView mRecyclerView;
    private CartRolesAdapter mAdapter;
    private Button mBtnCheckout;
    private SwipeRefreshLayout swipeRefreshLayout;

    private CartPresenter mPresenter;
    private LoadingDialog loadingDialog;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadingDialog = new LoadingDialog(getActivity());

        loadingDialog.start();
        loadRoles();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_roles, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mBtnCheckout = view.findViewById(R.id.btnCheckoutCartRoles);
        swipeRefreshLayout = view.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setEnabled(false);

        mPresenter = new CartPresenter(this);

        mBtnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOut();
            }
        });

        return view;
    }

    private void checkOut(){
        mPresenter.checkoutRoles(mRoles);
    }

    private void loadRoles(){
        CartHelper.getRoles(new QuerySnapshotCallBack() {
            @Override
            public void onComplete(List list) {
                mRoles = list;
                updateAdapter();
            }
        });
    }

    private void updateAdapter(){
        mAdapter = new CartRolesAdapter(mRoles, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setAdapter(mAdapter);

        loadingDialog.stop();

    }

    @Override
    public void onDelete(int pos) {
        mRoles.remove(pos);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeRolesInCart() {
        showToastMessage("ASSIGNED SUCCESSFULLY!");
        CartHelper.checkoutRoles();
        mRoles.clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeToolsInCart() {

    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }
}
