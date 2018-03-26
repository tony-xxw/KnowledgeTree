package com.wynne.knowledge.tree.custom.tray;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author Wynne
 * @date 2018/3/26
 */

public class TrayViewBottom extends View {

    private Paint mPaint;
    private Path mPath;
    PathEffect effect;
    private int defulatHeight = 100;


    public TrayViewBottom(Context context) {
        super(context);
    }

    public TrayViewBottom(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPath = new Path();
        effect = new CornerPathEffect(20);
        mPaint.setPathEffect(effect);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(200, 100);
        mPath.lineTo(900, 100);
        mPath.lineTo(1000, 200);
        mPath.lineTo(100, 200);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}
