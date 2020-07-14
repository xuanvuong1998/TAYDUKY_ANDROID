package vuong.hx.tayduky.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.helpers.ImageHelper;
import vuong.hx.tayduky.models.SceneRoleFullInfo;

public class ChallengeRolesAdapter extends RecyclerView.Adapter<ChallengeRolesAdapter.ChallengeRolesViewHolder> {

    private List<SceneRoleFullInfo> listData;
    private OnClickItem listener;

    public ChallengeRolesAdapter(List<SceneRoleFullInfo> listData, OnClickItem listener) {
        this.listData = listData;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ChallengeRolesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.adapter_rv_challenge_roles, parent, false);

        return new ChallengeRolesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeRolesViewHolder holder, int position) {
        SceneRoleFullInfo curItem = listData.get(position);

        holder.bindData(curItem);
    }

    @Override
    public int getItemCount() {
        if (listData == null) return 0;

        return listData.size();
    }


    public interface OnClickItem{
        void onClickCharacter();
        void onClickAssignedActor();
    }

    public class ChallengeRolesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgCharacter, imgAssignedActor;
        private EditText edtRoleDesc;
        private Button btnSaveChange, btnDeleteRole;

        public ChallengeRolesViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCharacter = itemView.findViewById(R.id.imgvCharacter);
            imgAssignedActor = itemView.findViewById(R.id.imgvAssignedActor);

            edtRoleDesc = itemView.findViewById(R.id.edtRoleDesc);

            btnSaveChange = itemView.findViewById(R.id.btnSaveChanges);
            btnDeleteRole = itemView.findViewById(R.id.btnDeleteRole);


        }

        public void bindData(SceneRoleFullInfo role){
            edtRoleDesc.setText(role.getDesc());

            ImageHelper.loadImageFromInternal(role.getCharacter().getImage(), imgCharacter);
            ImageHelper.loadImageFromInternal(role.getAssignedActor().getImage(), imgAssignedActor);

        }
    }

}
