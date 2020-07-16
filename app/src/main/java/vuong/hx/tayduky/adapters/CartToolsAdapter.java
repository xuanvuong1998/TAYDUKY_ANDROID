package vuong.hx.tayduky.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.models.SceneToolFullInfo;

public class CartToolsAdapter extends RecyclerView.Adapter<CartToolsAdapter.CartToolsViewHolder> {

    private List<SceneToolFullInfo> listData;
    private OnClickItem listener;

    public CartToolsAdapter(List<SceneToolFullInfo> listData, OnClickItem listener) {
        this.listData = listData;
        this.listener = listener;

    }

    @NonNull
    @Override
    public CartToolsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_rv_cart_tools, parent, false);

        return new CartToolsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartToolsViewHolder holder, final int position) {
        final SceneToolFullInfo curItem = listData.get(position);

        holder.bindData(curItem);

        holder.btnDeleteRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listData == null) return 0;

        return listData.size();
    }


    public interface OnClickItem{
        void onDelete(int pos);
    }

    public class CartToolsViewHolder extends RecyclerView.ViewHolder {

        private TextView tvQuantity, tvChallengeName, tvToolName;
        Button btnDeleteRole;

        public CartToolsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvQuantity = itemView.findViewById(R.id.tvToolQuantity);
            tvChallengeName = itemView.findViewById(R.id.tvChallengeName);
            tvToolName = itemView.findViewById(R.id.tvToolName);

            btnDeleteRole = itemView.findViewById(R.id.btnDelTool);

        }

        public void bindData(SceneToolFullInfo role){
            tvChallengeName.setText(role.getChallenge().getName());
            tvToolName.setText(role.getTool().getName());
            tvQuantity.setText(role.getQuantity() + "");

        }
    }

}
