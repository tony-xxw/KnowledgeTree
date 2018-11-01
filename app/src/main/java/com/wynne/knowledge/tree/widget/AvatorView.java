package com.wynne.knowledge.tree.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wynne.knowledge.tree.R;

/**
 *
 * @author Wynne
 * @date 2018/11/1
 */

public class AvatorView extends View {
    private Paint paint;
    private Bitmap bitmap;
    private BitmapShader shader;

    public AvatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_advertisement);
        paint = new Paint();

        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        float scale = getWidth() / bitmap.getWidth();
        matrix.setScale(scale, scale);
        shader.setLocalMatrix(matrix);
        paint.setShader(shader);

        float half = getWidth() / 2;
        canvas.drawCircle(half, half, getWidth() / 2, paint);
    }
}
