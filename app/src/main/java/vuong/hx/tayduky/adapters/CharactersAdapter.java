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
import vuong.hx.tayduky.models.Character;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharacterHolder> {

    private List<Character> listData;
    private Context context;
    private OnClickItem listener;

    public List<Character> getListData() {
        return listData;
    }

    public void setListData(List<Character> listData) {
        this.listData = listData;
    }

    public OnClickItem getListener() {
        return listener;
    }

    public void setListener(OnClickItem listener) {
        this.listener = listener;
    }

    public CharactersAdapter(List<Character> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_rv_admin_characters, parent, false);

        return new CharacterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterHolder holder, int position) {
        final Character character = listData.get(position);

        holder.bindData(character);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickDetails(character);
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
        void onClickDetails(Character character);
    }

    class CharacterHolder extends RecyclerView.ViewHolder {

        private TextView tvCharacterName;
        private ImageView imageViewCharacter;
        public View view;


        public CharacterHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;

            tvCharacterName = itemView.findViewById(R.id.tvCharacterName);
            imageViewCharacter = itemView.findViewById(R.id.imgvCharacter);

        }

        public void bindData(Character character) {
            tvCharacterName.setText(character.getName());

            ImageHelper.loadImageFromInternal(character.getImage(), imageViewCharacter);
        }
    }
}
