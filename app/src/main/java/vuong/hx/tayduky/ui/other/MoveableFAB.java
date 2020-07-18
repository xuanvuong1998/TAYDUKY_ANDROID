package vuong.hx.tayduky.ui.other;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MoveableFAB extends FloatingActionButton implements View.OnTouchListener{

    private final static float CLICK_DRAG_TOLERANCE = 10;
    // Often, there will be a slight, unintentional, drag when the user taps the FAB, so we need to account for this.

    private float downRawX, downRawY;
    private float dX, dY;
    public MoveableFAB(@NonNull Context context) {
        super(context);
    }

    private void init(){
        setOnTouchListener(this);
    }




    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
