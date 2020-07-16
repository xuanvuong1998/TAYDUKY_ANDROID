package vuong.hx.tayduky.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.models.SceneTool;

public class ChallengeToolsAdapter extends RecyclerView.Adapter<ChallengeToolsAdapter.ChallengeToolsViewHolder> {

    private List<SceneTool> listData;
    private OnClickItem listener;

    public ChallengeToolsAdapter(List<SceneTool> listData, OnClickItem listener) {
        this.listData = listData;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ChallengeToolsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_rv_challenge_tools, parent, false);

        return new ChallengeToolsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeToolsViewHolder holder, int position) {
        final SceneTool curItem = listData.get(position);

        holder.bindData(curItem);
        holder.btnDeleteTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteChallengeTool(curItem);
            }
        });

        holder.btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSaveChangeTool(curItem);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (listData == null) return 0;

        return listData.size();
    }


    public interface OnClickItem{
        void onDeleteChallengeTool(SceneTool tool);
        void onSaveChangeTool(SceneTool tool);
    }

    public class ChallengeToolsViewHolder extends RecyclerView.ViewHolder {

        private EditText edtToolQuantity, edtToolName;
        public Button btnSaveChange, btnDeleteTool;

        public ChallengeToolsViewHolder(@NonNull View itemView) {
            super(itemView);

            edtToolQuantity = itemView.findViewById(R.id.edtToolQuantity);
            edtToolName = itemView.findViewById(R.id.edtToolName);
            btnSaveChange = itemView.findViewById(R.id.btnSaveChanges);
            btnDeleteTool = itemView.findViewById(R.id.btnDeleteTool);

        }

        public void bindData(SceneTool tool){
            edtToolQuantity.setText(tool.getQuantity() + "");
            edtToolName.setText(tool.getTool().getName());
        }
    }

}
