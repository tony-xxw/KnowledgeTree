package com.wynne.knowledge.tree.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/10/31
 */

public class ShadowView extends View {
    private Paint paint = new Paint();
    Bitmap bitmap;
    boolean isShadow;

    public ShadowView(Context context) {
        super(context);
    }

    public ShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.BLACK);
        paint.setTextSize(25);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_advertisement);

    }

    public void setShadow(boolean shadow) {
        isShadow = shadow;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isShadow) {
            paint.setShadowLayer(1, 10, 10, Color.GRAY);
        } else {
            paint.clearShadowLayer();
        }

        super.onDraw(canvas);
        canvas.drawText("Wynne", 100, 100, paint);
        canvas.drawCircle(100, 200, 50, paint);
        canvas.drawBitmap(bitmap, null, new Rect(100, 300, 200 + bitmap.getWidth(), 400 + bitmap.getHeight()), paint);
    }
}
