package vuong.hx.tayduky.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.models.SceneRoleFullInfo;

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.NotifViewHolder> {
    private List<SceneRoleFullInfo> listData;
    public OnClickItem listener;

    public NotifAdapter(List<SceneRoleFullInfo> listData, OnClickItem listener) {
        this.listData = listData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotifViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_rv_notification, parent, false);

        return new NotifViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotifViewHolder holder, final int position) {

        holder.bindData(listData.get(position));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickNotifDetails(listData.get(position));
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

    public interface OnClickItem{
        void onClickNotifDetails(SceneRoleFullInfo role);
    }

    class NotifViewHolder extends RecyclerView.ViewHolder{

        private TextView tvChallenge, tvMess;
        View view;

        public NotifViewHolder(@NonNull View itemView) {
            super(itemView);

            tvChallenge = itemView.findViewById(R.id.tvChallengeTitle);
            tvMess = itemView.findViewById(R.id.tvNotifMess);

            view = itemView;

        }

        void bindData(SceneRoleFullInfo role){
            tvChallenge.setText(role.getChallenge().getName());
            tvMess.setText("You have a new role!");
        }

    }
}
