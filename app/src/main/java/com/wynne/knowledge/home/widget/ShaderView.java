package com.wynne.knowledge.home.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wynne.knowledge.home.R;

/**
 * @author Wynne
 * @date 2018/11/1
 */

public class ShaderView extends View {
    Paint mPaint;
    Bitmap bitmap, bitmapBG;
    int dx, dy;

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_advertisement);

//        mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dx = (int) event.getX();
                dy = (int) event.getY();
                postInvalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                dx = (int) event.getX();
                dy = (int) event.getY();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                dx = -1;
                dy = -1;
                break;
            default:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmapBG == null) {
            bitmapBG = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas1 = new Canvas(bitmapBG);
            canvas1.drawBitmap(bitmapBG, null, new Rect(0, 0, getWidth(), getHeight()), mPaint);
        }

        if (dx != -1 && dy != -1) {
            mPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
            canvas.drawCircle(dx, dy, 150, mPaint);
        }
    }
}
