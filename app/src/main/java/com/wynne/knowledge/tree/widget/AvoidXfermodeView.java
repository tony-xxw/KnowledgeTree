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
 *
 * @author Wynne
 * @date 2018/11/5
 */

public class AvoidXfermodeView extends View {
    private Paint mPaint;
    private Bitmap mBmp;

    public AvoidXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mBmp = BitmapFactory.decodeResource(getResources(), R.drawable.bg_advertisement);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int height = width * mBmp.getHeight() / getWidth();

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mBmp, null, new Rect(0, 0, width, height), mPaint);

//        mPaint.setXfermode(new AvoidXfermode(Color.WHITE,100,AvoidX))
    }
}
