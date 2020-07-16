package vuong.hx.tayduky.adapters;

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
import vuong.hx.tayduky.models.SceneRoleFullInfo;

public class CartRolesAdapter extends RecyclerView.Adapter<CartRolesAdapter.CartRolesViewHolder> {

    private List<SceneRoleFullInfo> listData;
    private OnClickItem listener;

    public CartRolesAdapter(List<SceneRoleFullInfo> listData, OnClickItem listener) {
        this.listData = listData;
        this.listener = listener;

    }

    @NonNull
    @Override
    public CartRolesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_rv_cart_roles, parent, false);

        return new CartRolesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartRolesViewHolder holder, final int position) {
        final SceneRoleFullInfo curItem = listData.get(position);

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

    public class CartRolesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCharacter, imgAssignedActor;
        private TextView tvDesc, tvChallengeName;
        Button btnDeleteRole;

        public CartRolesViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCharacter = itemView.findViewById(R.id.imgvCharacter);
            imgAssignedActor = itemView.findViewById(R.id.imgvAssignedActor);
            tvChallengeName = itemView.findViewById(R.id.tvChallengeName);
            tvDesc = itemView.findViewById(R.id.tvDesc);

            btnDeleteRole = itemView.findViewById(R.id.btnDelRole);

        }

        public void bindData(SceneRoleFullInfo role){
            tvDesc.setText(role.getDesc());
            tvChallengeName.setText(role.getChallenge().getName());

            ImageHelper.loadImageFromInternal(role.getCharacter().getImage(), imgCharacter);
            ImageHelper.loadImageFromInternal(role.getAssignedActor().getImage(), imgAssignedActor);

        }
    }

}
