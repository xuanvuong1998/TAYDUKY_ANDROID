package vuong.hx.tayduky.ui.fragments.dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.CharactersAdapter;
import vuong.hx.tayduky.constants.ReqCode;
import vuong.hx.tayduky.helpers.TempDataHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Character;

public class ChooseCharacterDialogFragment extends DialogFragment implements CharactersAdapter.OnClickItem {
    private RecyclerView mRecyclerView;
    private EditText mEdtSearchCharacter;
    private Button mBtnChoose;
    private CharactersAdapter mAdapter;
    private String mUserToken;
    private Character mChosenCharacter;


    public static ChooseCharacterDialogFragment newInstance(List<Character> CharacterList) {
        
        Bundle args = new Bundle();

        args.putSerializable("CharactersList", (Serializable) CharacterList);

        ChooseCharacterDialogFragment fragment = new ChooseCharacterDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        getDialog().getWindow().setLayout(width, height);

        View view = inflater.inflate(R.layout.dialog_fragment_choose_character, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mBtnChoose = view.findViewById(R.id.btnChooseCharacter);
        mEdtSearchCharacter = view.findViewById(R.id.edtCharacterSearch);


        mBtnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity().getIntent();
                if (mChosenCharacter != null){
                    intent.putExtra("chosenCharacter", mChosenCharacter.getName());

                    intent.putExtra("chosenCharacterFullInfo", mChosenCharacter);

                    getTargetFragment().onActivityResult(ReqCode.CHOOSE_CHARACTER, Activity.RESULT_OK, intent);
                }else{
                    getTargetFragment().onActivityResult(ReqCode.CHOOSE_CHARACTER, Activity.RESULT_CANCELED, intent);
                }

                dismiss();
            }
        });


        mUserToken = TempDataHelper.getUserToken();

        Bundle bundle = getArguments();

        List<Character> Characters = (List<Character>) bundle.getSerializable("CharactersList");

        setAdapter(Characters);

        return view;
    }

    private void setAdapter(List<Character> Characters){

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mAdapter = new CharactersAdapter(Characters, getContext());
        mAdapter.setListener(this);

        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClickCharacter(Character Character) {

        ToastHelper.showLongMess(getContext(), Character.getName() + " chosen!");
        mEdtSearchCharacter.setText(Character.getName());
        mChosenCharacter = Character;

    }


}
