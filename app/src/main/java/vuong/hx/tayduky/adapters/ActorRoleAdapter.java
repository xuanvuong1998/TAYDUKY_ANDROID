package vuong.hx.tayduky.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.models.SceneRole;

public class ActorRoleAdapter extends RecyclerView.Adapter<ActorRoleAdapter.ActorRoleViewHolder> {
    private List<SceneRole> listData;
    private boolean areIncoming;

    public ActorRoleAdapter(List<SceneRole> listData, boolean mode) {
        this.listData = listData;
        areIncoming = mode;
    }

    @NonNull
    @Override
    public ActorRoleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.adapter_rv_actor_challenges, parent, false);

        return new ActorRoleViewHolder(view, areIncoming);

    }

    @Override
    public void onBindViewHolder(@NonNull ActorRoleViewHolder holder, int position) {
        holder.bindData(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ActorRoleViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvChallengeName, mTvLocation, mTvStartDate, mTvDesc, mTvEndDate, mTvRole;

        public ActorRoleViewHolder(@NonNull View itemView, boolean mode) {
            super(itemView);
            initViews(itemView, mode);
        }

        private void initViews(View view, boolean mode){
            mTvChallengeName = view.findViewById(R.id.tvChallengeTitle);
            mTvEndDate = view.findViewById(R.id.tvFinishedDate);
            if (mode == true){

                view.findViewById(R.id.tvFinishedDateTitle).setVisibility(View.GONE);
                mTvEndDate.setVisibility(View.GONE);
            }else{
                view.findViewById(R.id.tvFinishedDateTitle).setVisibility(View.VISIBLE);
                mTvEndDate.setVisibility(View.VISIBLE);
            }
            mTvStartDate = view.findViewById(R.id.tvStartDate);
            mTvDesc = view.findViewById(R.id.tvRoleDesc);
            mTvLocation = view.findViewById(R.id.tvChallengeLocation);
            mTvRole = view.findViewById(R.id.tvRole);
        }

        private void bindData(SceneRole role){
            mTvChallengeName.setText(role.getChallenge().getName());
            if (role.getFinishedDate() != null){
                mTvEndDate.setText(role.getFinishedDate());
            }

            mTvStartDate.setText(role.getParticipatedDate());
            mTvRole.setText(role.getCharacter().getName());
            mTvDesc.setText(role.getDescription());
            mTvLocation.setText(role.getChallenge().getLocation());

        }
    }
}
