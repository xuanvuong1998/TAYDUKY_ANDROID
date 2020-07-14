package vuong.hx.tayduky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.helpers.ImageHelper;
import vuong.hx.tayduky.models.Tool;

public class ToolsAdapter extends RecyclerView.Adapter<ToolsAdapter.ToolHolder> {

    private List<Tool> listData;
    private Context context;
    private OnClickItem listener;

    public List<Tool> getListData() {
        return listData;
    }

    public void setListData(List<Tool> listData) {
        this.listData = listData;
    }

    public OnClickItem getListener() {
        return listener;
    }

    public void setListener(OnClickItem listener) {
        this.listener = listener;
    }

    public ToolsAdapter(List<Tool> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ToolHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_rv_admin_tools, parent, false);

        return new ToolHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToolHolder holder, int position) {
        final Tool Tool = listData.get(position);

        holder.bindData(Tool);

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickEdit(Tool);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDelete(Tool);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (listData == null){
            return 0;
        }

        return listData.size();
    }

    public interface OnClickItem {
        void onClickDelete(Tool Tool);

        void onClickEdit(Tool Tool);
    }

    class ToolHolder extends RecyclerView.ViewHolder {

        private TextView tvToolName, tvToolQuantity;
        private ImageView imageViewTool;
        public View view;

        public Button btnUpdate, btnDelete;

        public ToolHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            tvToolName = itemView.findViewById(R.id.tvToolName);
            tvToolQuantity = itemView.findViewById(R.id.tvToolQuantity);
            imageViewTool = itemView.findViewById(R.id.imgvTool);
            btnUpdate = itemView.findViewById(R.id.btnUpdateTool);
            btnDelete = itemView.findViewById(R.id.btnDelTool);
        }

        public void bindData(Tool tool) {
            tvToolName.setText(tool.getName());
            tvToolQuantity.setText("Quantity: " + tool.getQuantity() + "");

            ImageHelper.loadImageFromInternal(tool.getImage(), imageViewTool);
        }
    }
}
