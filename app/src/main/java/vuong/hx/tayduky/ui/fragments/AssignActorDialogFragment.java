package vuong.hx.tayduky.ui.fragments;

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
import vuong.hx.tayduky.adapters.ActorsAdapter;
import vuong.hx.tayduky.helpers.ToastHelper;
import vuong.hx.tayduky.models.Actor;

public class AssignActorDialogFragment extends DialogFragment implements ActorsAdapter.OnClickItem {
    private RecyclerView mRecyclerView;
    private EditText mEdtSearchActor;
    private Button mBtnChoose;
    private ActorsAdapter mAdapter;
    private String mUserToken;
    private Actor mChosenActor;
    private final int CHOOSE_ACTOR = 17;

    public static AssignActorDialogFragment newInstance(String userToken, List<Actor> actorList) {
        
        Bundle args = new Bundle();

        args.putString("userToken", userToken);
        args.putSerializable("actorsList", (Serializable) actorList);

        AssignActorDialogFragment fragment = new AssignActorDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        getDialog().getWindow().setLayout(width, height);

        View view = inflater.inflate(R.layout.dialog_fragment_chose_actors, container, false);

        mRecyclerView = view.findViewById(R.id.rcActorList);
        mBtnChoose = view.findViewById(R.id.btnChooseActor);
        mEdtSearchActor = view.findViewById(R.id.edtActorSearch);


        mBtnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity().getIntent();
                if (mChosenActor != null){
                    intent.putExtra("chosenActor", mChosenActor.getUsername());
                    getTargetFragment().onActivityResult(CHOOSE_ACTOR, Activity.RESULT_OK, intent);
                }else{
                    getTargetFragment().onActivityResult(CHOOSE_ACTOR, Activity.RESULT_CANCELED, intent);
                }

                dismiss();
            }
        });

        Bundle bundle = getArguments();

        mUserToken = bundle.getString("userToken");

        List<Actor> actors = (List<Actor>) bundle.getSerializable("actorsList");

        setAdapter(actors);

        return view;
    }

    private void setAdapter(List<Actor> actors){

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mAdapter = new ActorsAdapter(actors, getContext());
        mAdapter.setListener(this);

        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onClickActor(Actor actor) {

        ToastHelper.showLongMess(getContext(), actor.getName() + " chosen!");
        mEdtSearchActor.setText(actor.getName());
        mChosenActor = actor;

    }
}
