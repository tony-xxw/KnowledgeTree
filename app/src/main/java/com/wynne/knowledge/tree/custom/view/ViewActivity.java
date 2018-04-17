package com.wynne.knowledge.tree.custom.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/4/10
 */

public class ViewActivity extends AppCompatActivity implements View.OnTouchListener {
    private Button buttonScroll;
    private ScrollerView mScroller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);
        buttonScroll = (Button) findViewById(R.id.btn_view_scroller);
        mScroller = (ScrollerView) findViewById(R.id.sv_sample);
        mScroller.smoothScrollTo(200, 0);
        buttonScroll.setOnTouchListener(this);
        buttonScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollSample(200);
            }
        });
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
                Log.d("XXW", "Y :" + event.getY());


                velocityTracker.clear();
                velocityTracker.recycle();
                return false;

            }
        });


        Log.d("XXW", "滑动最小距离 :" + ViewConfiguration.get(getBaseContext()).getScaledTouchSlop());


    }

    /**
     * 使用ScrollTo来移动View的内容 绝对移动  在上一个x,y基础上移动
     *
     * @param x
     * @param y
     */
    private void scrollSample(int x, int y) {
        buttonScroll.scrollTo(x, y);
        Log.d("XXW", "scroll Y :" + buttonScroll.getScrollY());
        Log.d("XXW", " Y :" + y);
    }

    /**
     * 使用ScrollBy来移动View的内容 相对移动  在上一个scrollx,scrolly基础上移动
     *
     * @param x
     */
    private void scrollSample(int x) {
        buttonScroll.scrollBy(x, 0);


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return false;
    }


}
