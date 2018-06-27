package com.wynne.knowledge.tree.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wynne.knowledge.tree.R;

/**
 * Created by Wynne on 2018/6/27.
 */

public class StarView extends View {
    private int starSize;
    private int starSpace;
    private int starCount;
    private Drawable starDark;
    private Bitmap starLight;
    private Paint mPaint;
    private float startMark;

    private boolean isInteggerMark;


    public StarView(Context context) {
        super(context);
    }

    public StarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributeSet(context, attrs);
    }

    private void initAttributeSet(Context context, AttributeSet attrs) {
        TypedArray attrsType = context.obtainStyledAttributes(attrs, R.styleable.StarView);
        starSize = (int) attrsType.getDimension(R.styleable.StarView_starSize, 0);
        starSpace = (int) attrsType.getDimension(R.styleable.StarView_starSpace, 0);
        starCount = attrsType.getInteger(R.styleable.StarView_starCount, 0);
        starDark = attrsType.getDrawable(R.styleable.StarView_starDefault);
        starLight = drawableToBitmap(attrsType.getDrawable(R.styleable.StarView_starFill));
        attrsType.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(new BitmapShader(starLight, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));

    }

    /**
     * 设置是否整数显示
     *
     * @param integgerMark
     */
    public void setInteggerMark(boolean integgerMark) {
        isInteggerMark = integgerMark;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(starSize * starCount + starSpace * (starCount - 1), starSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (starLight == null || starDark == null) {
            return;
        }

        for (int i = 0; i < starCount; i++) {
            starDark.setBounds((starSpace + starSize) * i, 0, (starSpace + starSize) * i + starSize, starSize);
            starDark.draw(canvas);
        }

        if (startMark > 1) {
            canvas.drawRect(0, 0, starSize, starSize, mPaint);
            if (startMark - (int) (startMark) == 0) {
                for (int i = 1; i < startMark / 2; i++) {
                    canvas.translate(starSpace + starSize, 0);
                    canvas.drawRect(0, 0, starSize, starSize, mPaint);
                }
            } else {
                canvas.drawRect(0, 0, starSize * startMark, starSize, mPaint);
            }

        }

    }


    public void setStartMark(int mark) {
        if (isInteggerMark) {
            startMark = (int) Math.ceil(mark);
        } else {
            startMark = Math.round(mark * 10) * 1.0f / 10;
        }

        invalidate();
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(starSize, starSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, starSize, starSize);
        drawable.draw(canvas);
        return bitmap;
    }
}
