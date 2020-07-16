package vuong.hx.tayduky.ui.fragments.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.CartRolesAdapter;
import vuong.hx.tayduky.callbacks.QuerySnapshotCallBack;
import vuong.hx.tayduky.helpers.CartHelper;
import vuong.hx.tayduky.models.SceneRoleFullInfo;

public class CartRolesFragment extends Fragment implements CartRolesAdapter.OnClickItem {

    private List<SceneRoleFullInfo> mRoles;

    private RecyclerView mRecyclerView;
    private CartRolesAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_roles, container, false);

        loadRoles();
        return view;
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

    }

    @Override
    public void onDelete(int pos) {
        mRoles.remove(pos);

        mAdapter.notifyDataSetChanged();
    }
}
