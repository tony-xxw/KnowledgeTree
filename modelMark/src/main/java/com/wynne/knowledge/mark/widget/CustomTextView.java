package com.wynne.knowledge.mark.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomTextView extends View {
    private Paint mPaint;

    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setTextSize(18);
        mPaint.setColor(Color.RED);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("测试文本", 100, 200, mPaint);

        Log.d("XXW", "" + getHeight());
        Log.d("XXW", "" + getWidth());
        Log.d("XXW", "" + getRight());
        Log.d("XXW", "" + getBottom());
        Log.d("XXW", "" + getX());
        Log.d("XXW", "" + getY());


    }
}
