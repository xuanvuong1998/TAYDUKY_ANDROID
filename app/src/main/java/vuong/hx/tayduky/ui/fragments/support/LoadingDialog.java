package vuong.hx.tayduky.ui.fragments.support;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import vuong.hx.tayduky.R;

public class LoadingDialog {
    private Activity activity;
    private AlertDialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }

    public void start(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_loading, null));

        dialog = builder.create();

        dialog.show();
    }

    public void stop(){
        if (dialog.isShowing()){
            dialog.dismiss();
        }
    }

}
