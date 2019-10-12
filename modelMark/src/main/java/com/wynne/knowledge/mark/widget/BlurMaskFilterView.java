package com.wynne.knowledge.mark.widget;

/**
 * @author Wynne
 */

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Wynne
 * @date 2018/10/31
 */
public class BlurMaskFilterView extends View {
    Paint paint;

    public BlurMaskFilterView(Context context) {
        super(context);
    }

    public BlurMaskFilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
    }

    public void setMaskFilter(BlurMaskFilter.Blur blur) {
        paint.setMaskFilter(new BlurMaskFilter(50, blur));
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(100, 100, 50, paint);
    }
}
