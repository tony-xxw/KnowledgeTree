package com.wynne.knowledge.tree.custom.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/4/10
 */

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        findViewById(R.id.btn_view_position).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                VelocityTracker velocityTracker = VelocityTracker.obtain();
                velocityTracker.addMovement(event);
                velocityTracker.computeCurrentVelocity(1000);

                int xVelocity = (int) velocityTracker.getXVelocity();
                int yVelocity = (int) velocityTracker.getYVelocity();

                Log.d("XXW", "xVelocity :" + xVelocity);
                Log.d("XXW", "yVelocity :" + yVelocity);
                //View相对屏幕的x,y值
                Log.d("XXW", "RawY :" + event.getRawY());
                //view相对本身左上角的值
                Log.d("XXW", "Y :" + event.getY());


                velocityTracker.clear();
                velocityTracker.recycle();
                return false;
            }
        });


        Log.d("XXW", "滑动最小距离 :" + ViewConfiguration.get(getBaseContext()).getScaledTouchSlop());
        Log.d("XXW", "DPI  :" + getResources().getDisplayMetrics().density);
    }
}
