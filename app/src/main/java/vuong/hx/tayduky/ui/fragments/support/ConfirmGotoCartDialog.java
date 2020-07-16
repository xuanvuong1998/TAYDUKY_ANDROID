package vuong.hx.tayduky.ui.fragments.support;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import vuong.hx.tayduky.R;

public class ConfirmGotoCartDialog extends DialogFragment {
    private Button mBtnGotocart, mBtnAddMore;

    public ConfirmGotoCartDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return super.onCreateDialog(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_confirm_gotocart, container, false);

        mBtnAddMore = view.findViewById(R.id.btnAddMore);
        mBtnGotocart = view.findViewById(R.id.btnGotocart);

        mBtnGotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: CREATE CART

            }
        });

        mBtnAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
        return view;
    }
}
