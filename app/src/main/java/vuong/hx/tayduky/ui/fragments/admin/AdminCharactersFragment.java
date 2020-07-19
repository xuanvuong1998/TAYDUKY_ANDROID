package vuong.hx.tayduky.ui.fragments.admin;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.CharactersAdapter;
import vuong.hx.tayduky.constants.ReqCode;
import vuong.hx.tayduky.constants.ReqTag;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Character;
import vuong.hx.tayduky.presenters.ManageCharactersPresenter;
import vuong.hx.tayduky.ui.fragments.dialogs.CharacterDetailsDialogFragment;
import vuong.hx.tayduky.ui.view_interfaces.CharacterListView;


public class AdminCharactersFragment extends Fragment
        implements CharactersAdapter.OnClickItem, CharacterListView {

    private ManageCharactersPresenter mPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private CharactersAdapter mCharacterAdapter;
    private RecyclerView mRecyclerView;
    private List<Character> mCharactersList;
    private Button mBtnAddNew;



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
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mBtnAddNew = view.findViewById(R.id.btnAddRole);

        final CharacterDetailsDialogFragment frg = CharacterDetailsDialogFragment.newInstance(null);
        frg.setTargetFragment(this, ReqCode.CHARACTER_DETAILS);
        mBtnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frg.show(getActivity().getSupportFragmentManager(), "create_character");
            }
        });

        mSwipeRefreshLayout = view.findViewById(R.id.swipe_layout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadCharactersList();
            }
        });
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //mPresenter.setCharacterListView(null);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ReqCode.CHARACTER_DETAILS){
            if (resultCode == Activity.RESULT_OK){
                showToastMessage("Saved!");
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
    public void onClickCharacter(Character character) {
        CharacterDetailsDialogFragment fr = CharacterDetailsDialogFragment.newInstance(character);

        fr.setTargetFragment(this, ReqCode.CHARACTER_DETAILS);
        fr.show(getActivity().getSupportFragmentManager(), ReqTag.CHARACTER_DETAILS);
    }

    /**
     * Receive
     * @param characters
     */
    @Override
    public void loadCharactersList(List<Character> characters) {

        // data received! remove refreshing icon
        mSwipeRefreshLayout.setRefreshing(false);
        mCharactersList = characters;

        mCharacterAdapter = new CharactersAdapter(mCharactersList, getContext());

        mCharacterAdapter.setListener(this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mRecyclerView.setAdapter(mCharacterAdapter);

    }

    @Override
    public void showToastMessage(String message) {
        // data received! remove refreshing icon
        mSwipeRefreshLayout.setRefreshing(false);
        ToastHelper.showLongMess(getContext(), message);
    }
}