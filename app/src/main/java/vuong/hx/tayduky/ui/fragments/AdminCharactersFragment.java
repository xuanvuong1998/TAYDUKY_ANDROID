package vuong.hx.tayduky.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.CharactersAdapter;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.presenters.ManageCharactersPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ManageCharacterView;


public class AdminCharactersFragment extends Fragment
        implements CharactersAdapter.OnClickItem, ManageCharacterView {

    private ManageCharactersPresenter mPresenter;
    private CharactersAdapter mCharacterAdapter;
    private RecyclerView mRecyclerView;
    private List<Character> mCharactersList;
    private Button mBtnAddNew;
    private final int CREATE_CHARACTER = 87;

    public AdminCharactersFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_admin_characters, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view){
        mRecyclerView = view.findViewById(R.id.rcAdminCharacters);
        mBtnAddNew = view.findViewById(R.id.btnAddCharacter);

        final CreateCharacterDialogFragment frg = new CreateCharacterDialogFragment();
        frg.setTargetFragment(this, CREATE_CHARACTER);
        mBtnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frg.show(getActivity().getSupportFragmentManager(), "create_character");
            }
        });

        initData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_CHARACTER){
            if (resultCode == Activity.RESULT_OK){
                showToastMessage("CREATED!");
                mPresenter.loadCharactersList();
            }else if (resultCode == Activity.RESULT_CANCELED){
                showToastMessage("Nothing Changed!");
            }
        }
    }

    private void initData(){
        if (mPresenter == null){
            mPresenter = new ManageCharactersPresenter(this);
        }

        mPresenter.loadCharactersList();
    }

    @Override
    public void onClickDetails(Character character) {

    }

    @Override
    public void loadCharactersList(List<Character> characters) {

        mCharactersList = characters;

        mCharacterAdapter = new CharactersAdapter(mCharactersList, getContext());

        mCharacterAdapter.setListener(this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mRecyclerView.setAdapter(mCharacterAdapter);

    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }
}