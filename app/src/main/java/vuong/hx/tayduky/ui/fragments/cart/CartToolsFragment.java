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
import vuong.hx.tayduky.adapters.CartToolsAdapter;
import vuong.hx.tayduky.callbacks.QuerySnapshotCallBack;
import vuong.hx.tayduky.helpers.CartHelper;
import vuong.hx.tayduky.models.SceneToolFullInfo;

public class CartToolsFragment extends Fragment implements CartToolsAdapter.OnClickItem {
    private List<SceneToolFullInfo> mTools;

    private RecyclerView mRecyclerView;
    private CartToolsAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_tools, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);

        loadTools();
        return view;
    }


    private void loadTools(){
        CartHelper.getTools(new QuerySnapshotCallBack() {
            @Override
            public void onComplete(List list) {
                mTools = list;
                updateAdapter();
            }
        });
    }

    private void updateAdapter(){
        mAdapter = new CartToolsAdapter(mTools, this);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDelete(int pos) {
        mTools.remove(pos);

        mAdapter.notifyDataSetChanged();
    }
}
