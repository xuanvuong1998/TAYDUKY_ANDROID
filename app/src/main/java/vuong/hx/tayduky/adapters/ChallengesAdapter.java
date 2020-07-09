package vuong.hx.tayduky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.models.Challenge;

public class ChallengesAdapter extends RecyclerView.Adapter<ChallengesAdapter.ChallengHolder> {

    private List<Challenge> listData;
    private Context context;
    private OnClickItem listener;

    public List<Challenge> getListData() {
        return listData;
    }

    public void setListData(List<Challenge> listData) {
        this.listData = listData;
    }

    public OnClickItem getListener() {
        return listener;
    }

    public void setListener(OnClickItem listener) {
        this.listener = listener;
    }

    public ChallengesAdapter(List<Challenge> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ChallengHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_rv_admin_challenges, parent, false);

        return new ChallengHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengHolder holder, int position) {
        final Challenge challenge = listData.get(position);

        holder.bindData(challenge);

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickEdit(challenge);
            }
        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDetails(challenge);
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
        void onClickDetails(Challenge challenge);

        void onClickEdit(Challenge challenge);
    }

    class ChallengHolder extends RecyclerView.ViewHolder {

        private TextView tvChallengeName, tvShootTimes, tvStartDate, tvEndDate, tvLocation;
        public View view;

        public Button btnUpdate;

        public ChallengHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            tvChallengeName = itemView.findViewById(R.id.tvChallengeTitle);
            tvShootTimes = itemView.findViewById(R.id.tvShootTimes);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvEndDate = itemView.findViewById(R.id.tvEndDate);
            tvLocation = itemView.findViewById(R.id.tvChallengeLocation);
            btnUpdate = itemView.findViewById(R.id.btnUpdateChallenge);
        }

        public void bindData(Challenge challenge) {
            tvChallengeName.setText(challenge.getName());
            tvLocation.setText(challenge.getLocation());
            tvEndDate.setText(challenge.getEndDate());
            tvStartDate.setText(challenge.getStartDate());
            tvShootTimes.setText(challenge.getShootTimes() + "");
        }
    }
}
