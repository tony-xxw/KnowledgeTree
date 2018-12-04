package com.wynne.knowledge.tree.tray;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wynne.knowledge.tree.tray.TrayViewGroup;

/**
 * @author Wynne
 * @date 2018/3/26
 */

public class TrayViewBottom extends View {

    private Paint mPaint;
    private Path mPath;
    PathEffect effect;
    private int DEFULATHEIGHT = 90;


    private TrayViewGroup mTrayViewGroup;

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

    public void setmTrayViewGroup(TrayViewGroup mTrayViewGroup) {
        this.mTrayViewGroup = mTrayViewGroup;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int tvGroupLeft = 0;
        int tvGroupRight = 0;
        if (mTrayViewGroup != null) {
            tvGroupLeft = mTrayViewGroup.getLeft();
            tvGroupRight = mTrayViewGroup.getRight();


        }
        mPath.moveTo(tvGroupLeft - 50, DEFULATHEIGHT);
        mPath.lineTo(tvGroupRight + 50, DEFULATHEIGHT);
        mPath.lineTo(tvGroupRight + 150, DEFULATHEIGHT * 2);
        mPath.lineTo(tvGroupLeft - 150, DEFULATHEIGHT * 2);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}
