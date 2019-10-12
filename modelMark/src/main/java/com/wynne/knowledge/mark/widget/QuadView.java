package com.wynne.knowledge.mark.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Wynne
 * @date 2018/10/30
 */

public class QuadView extends View {
    private Paint mPaint;
    private Path path = new Path();
    private float x, y;

    public QuadView(Context context) {
        super(context);
        init();
    }

    public QuadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//
//        path.moveTo(100, 300);
//        path.quadTo(200, 200, 300, 300);
//        path.quadTo(400, 400, 500, 300);
//
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                float endx = (x + event.getX()) / 2;
                float endy = (y + event.getY()) / 2;
                path.quadTo(x, y, endx, endy);
                x = event.getX();
                y = event.getY();
                postInvalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
