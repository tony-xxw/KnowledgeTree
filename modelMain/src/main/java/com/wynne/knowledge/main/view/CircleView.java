package com.wynne.knowledge.main.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wynne.knowledge.main.R;
import com.wynne.knowledge.base.utils.StringUtils;

/**
 * Created by Wynne on 2018/7/4.
 */

public class CircleView extends View {
    private Bitmap bitmap;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_advertisement);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        Paint paintR = new Paint();
        paintR.setColor(Color.RED);
        paintR.setStyle(Paint.Style.FILL);
        paintR.setAntiAlias(true);

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int width = getMeasuredWidth() - paddingLeft - paddingRight;
        int height = getMeasuredHeight() - paddingTop - paddingBottom;

        Path path = new Path();
        path.addCircle((width / 2) + paddingLeft, height / 2, height / 2, Path.Direction.CW);


        /**canvas.save();
         canvas.clipPath(path);
         canvas.drawBitmap(bitmap, 0, 0, paintR);
         canvas.restore();**/

        canvas.drawPath(path, paintR);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(StringUtils.getDefaultSize(widthMeasureSpec, getMinimumWidth()),
                StringUtils.getDefaultSize(heightMeasureSpec, getMinimumHeight()));
    }
}
