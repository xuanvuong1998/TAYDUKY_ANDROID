package vuong.hx.tayduky.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.helpers.ImageHelper;
import vuong.hx.tayduky.models.Actor;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorHolder> {

    private List<Actor> listData;
    private Context context;
    private OnClickItem listener;

    public List<Actor> getListData() {
        return listData;
    }

    public void setListData(List<Actor> listData) {
        this.listData = listData;
    }

    public OnClickItem getListener() {
        return listener;
    }

    public void setListener(OnClickItem listener) {
        this.listener = listener;
    }

    public ActorsAdapter(List<Actor> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ActorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_rv_admin_actors, parent, false);

        return new ActorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorHolder holder, int position) {
        final Actor actor = listData.get(position);

        holder.bindData(actor);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickActor(actor);
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
        void onClickActor(Actor actor);
    }

    class ActorHolder extends RecyclerView.ViewHolder {

        private TextView tvActorName;
        private ImageView imageViewActor;
        public View view;


        public ActorHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            tvActorName = itemView.findViewById(R.id.tvActorName);
            imageViewActor = itemView.findViewById(R.id.imgvActor);

        }

        public void bindData(Actor actor) {
            tvActorName.setText(actor.getName());

            ImageHelper.loadImageFromURI(actor.getImage(), imageViewActor);
        }
    }
}
