package vuong.hx.tayduky.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vuong.hx.tayduky.R;
import vuong.hx.tayduky.adapters.ChallengesAdapter;
import vuong.hx.tayduky.constants.SharePreferenceKeys;
import vuong.hx.tayduky.helpers.SharePreferenceHelper;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Challenge;
import vuong.hx.tayduky.presenters.ManageChallengesPresenter;
import vuong.hx.tayduky.ui.view_interfaces.ManageChallengeView;


public class AdminChallengesFragment extends Fragment
                implements ChallengesAdapter.OnClickItem, ManageChallengeView {

    private RecyclerView mRecyclerView;
    private Spinner mChallengeFilter;

    private List<Challenge> mChallengesList, mChallengesListFilter;
    private ManageChallengesPresenter mChallengesPresenter;
    private ChallengesAdapter mChallengeAdapter;

    public AdminChallengesFragment() {
    }


    public static AdminChallengesFragment newInstance() {
        AdminChallengesFragment fragment = new AdminChallengesFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_admin_challenges
                            , container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view){
        mChallengeFilter = view.findViewById(R.id.spChallengeStt);
        mRecyclerView = view.findViewById(R.id.rcAdminChallenges);

        //setupDropdownList();
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mChallengesPresenter.setManageChallengeView(null);
    }

    private void initData(){
        if (mChallengesPresenter == null){
            mChallengesPresenter = new ManageChallengesPresenter(this);
        }

        String token = SharePreferenceHelper.getString(this.getContext(),
                            SharePreferenceKeys.USER_TOKEN);
        mChallengesPresenter.loadChallengesList(token);
    }

    private void setupDropdownList(){
        String[] stts = getResources().getStringArray(R.array.challenge_filter);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                    android.R.layout.simple_list_item_1, stts);

        mChallengeFilter.setAdapter(adapter);

        
        mChallengeFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mChallengesListFilter = mChallengesPresenter.filterListByStatus(
                        mChallengesList, position);

                mChallengeAdapter.setListData(mChallengesListFilter);
                mChallengeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    @Override
    public void onClickDetails(Challenge challenge) {
        ToastHelper.showLongMess(getContext(), "DETAILS");
    }

    @Override
    public void onClickEdit(Challenge challenge) {
        ToastHelper.showLongMess(getContext(), "EDITS");
    }

    @Override
    public void loadChallengesList(List<Challenge> challenges) {
        mChallengesList = challenges;

        if (mChallengeAdapter != null){
            mChallengeAdapter.setListData(challenges);
            mChallengeAdapter.notifyDataSetChanged();
        }else{
            mChallengeAdapter = new ChallengesAdapter(mChallengesList, getContext());

            mChallengeAdapter.setListener(this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());

            setupDropdownList();

            mRecyclerView.setHasFixedSize(true);

            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setAdapter(mChallengeAdapter);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void showToastMessage(String message) {
        ToastHelper.showLongMess(getContext(), message);
    }
}