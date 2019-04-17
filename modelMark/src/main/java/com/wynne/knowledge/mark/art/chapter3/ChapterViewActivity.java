package com.wynne.knowledge.mark.art.chapter3;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 分发机制与绘制流程
 *
 * @author Wynne
 */
public class ChapterViewActivity extends BaseActivity {
    @BindView(R.id.btn_scroll)
    Button btnScroll;
    @BindView(R.id.btn_all_scroll)
    Button btnAllScroll;

    int x, y;

    @Override
    public void initView() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("XXW", "X :" + event.getRawX());
            Log.d("XXW", "Y :" + event.getRawY());
            Log.d("XXW", "Y :" + event.getX());
            Log.d("XXW", "Y :" + event.getY());
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            int testX = (int) (event.getRawX() - x);
            int testY = (int) (event.getRawY() - y);
            Log.d("XXW", "testX :" + event.getRawX());
            Log.d("XXW", "testY :" + event.getRawY());
            ObjectAnimator.ofFloat(btnAllScroll, "translationX", x, testX).start();
            ObjectAnimator.ofFloat(btnAllScroll, "translationY", y, testY).start();

            x = testX;
            y = testY;

            Log.d("XXW", "testX :" + event.getRawX());
            Log.d("XXW", "testY :" + event.getRawY());
        }
        return super.onTouchEvent(event);
    }

    @Override
    public int getLayoutId() {
        return R.layout.art_view_activity;
    }

    @OnClick({R.id.btn_scroll})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btn_scroll) {
            Log.d("XXW", "before scrollX: " + btnScroll.getScaleX());
            Log.d("XXW", "before scrollY: " + btnScroll.getScaleY());
            Log.d("XXW", "before X: " + btnScroll.getLeft());
            Log.d("XXW", "before Y: " + btnScroll.getTop());
//            btnScroll.scrollTo(50, -10);
            ObjectAnimator.ofFloat(btnScroll, "translationX", 0, 100).setDuration(3000).start();
            Log.d("XXW", "after scrollX: " + btnScroll.getScaleX());
            Log.d("XXW", "after scrollY: " + btnScroll.getScaleY());
            Log.d("XXW", "after X: " + btnScroll.getLeft());
            Log.d("XXW", "after Y: " + btnScroll.getTop());

        }
    }
}
