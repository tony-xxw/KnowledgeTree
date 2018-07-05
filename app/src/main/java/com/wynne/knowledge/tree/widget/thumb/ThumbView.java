package com.wynne.knowledge.tree.widget.thumb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.Utils;

/**
 * Created by Wynne on 2018/7/3.
 */

public class ThumbView extends View {

    private Paint mBitmapPaint;
    private Bitmap mThumbUp;
    private Bitmap mShining;
    private Bitmap mThumbNormal;
    private int mThumbHeight;
    private int mThumbWidth;
    private TuvPoint mShiningPoint;
    private TuvPoint mThumbPoint;
    private int mShiningHeight;
    private int mShiningWidth;
    private Paint mCirclePaint;
    private TuvPoint mCirclePoint;
    private float mRadiusMax;
    private int mRadiusMin;
    private Path mClipPath;
    private boolean mIsThumbpUp;

    private int mClickCount;
    private int mEndCount;
    private ThumbUpClickListener mThumbUpClickListener;
    private boolean isThumbUp;

    public ThumbView(Context context) {
        super(context);
    }

    public ThumbView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ThumbView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        initBitmapInfo();

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(Utils.dip2px(getContext(), 2));

        mCirclePoint = new TuvPoint();
        mCirclePoint.x = mThumbPoint.x + mThumbWidth / 2;
        mCirclePoint.y = mThumbPoint.y + mThumbHeight / 2;

        mRadiusMax = Math.max(mCirclePoint.x - getPaddingLeft(), mCirclePoint.y - getPaddingTop());
        mRadiusMin = Utils.dip2px(getContext(), 8);//这个值是根据点击效果调整得到的
        mClipPath = new Path();
        mClipPath.addCircle(mCirclePoint.x, mCirclePoint.y, mRadiusMax, Path.Direction.CW);
    }

    private void initBitmapInfo() {
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        resetBitmap();

        mThumbWidth = mThumbUp.getWidth();
        mThumbHeight = mThumbUp.getHeight();

        mShiningWidth = mShining.getWidth();
        mShiningHeight = mShining.getHeight();

        mShiningPoint = new TuvPoint();
        mThumbPoint = new TuvPoint();
        //XXW
        mShiningPoint.x = getPaddingLeft() + Utils.dip2px(getContext(), 2);
        mShiningPoint.y = getPaddingTop();
        //XXW
        mThumbPoint.x = getPaddingLeft();
        mThumbPoint.y = getPaddingTop() + Utils.dip2px(getContext(), 8);

    }

    private void resetBitmap() {
        mThumbUp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected);
        mThumbNormal = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_unselected);
        mShining = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected_shining);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(
                Utils.getDefaultSize(widthMeasureSpec, getContentWidth() + getPaddingLeft() + getPaddingRight()),
                Utils.getDefaultSize(heightMeasureSpec, getContentHeight() + getPaddingTop() + getPaddingBottom())
        );
    }

    private int getContentHeight() {
        float minTop = Math.min(mShiningPoint.y, mThumbPoint.y);
        float maxBottom = Math.max(mShiningPoint.y + mShiningHeight, mThumbPoint.y + mThumbHeight);
        return (int) (maxBottom - minTop);
    }


    private int getContentWidth() {
        float minLeft = Math.min(mShiningPoint.x, mThumbPoint.x);
        float maxRight = Math.max(mShiningPoint.x + mShiningWidth, mThumbPoint.x + mThumbWidth);
        return (int) (maxRight - minLeft);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isThumbUp) {
            if (mClipPath != null) {
                canvas.save();
                canvas.clipPath(mClipPath);
                canvas.drawBitmap(mShining, mShiningPoint.x, mShiningPoint.y, mBitmapPaint);
                canvas.restore();
            }
            canvas.drawBitmap(mThumbNormal, mThumbPoint.x, mThumbPoint.y, mBitmapPaint);
        } else {
            canvas.drawBitmap(mThumbNormal, mThumbPoint.x, mThumbPoint.y, mBitmapPaint);
        }
    }

    public TuvPoint getCirclePoint() {
        return mCirclePoint;
    }

    public void setIsThumbUp(boolean isThumbUp) {
        mIsThumbpUp = isThumbUp;
        mClickCount = mIsThumbpUp ? 1 : 0;
        mEndCount = mClickCount;
        postInvalidate();
    }

    public void setThumbUpClickListener(ThumbUpClickListener thumbUpClickListener) {
        this.mThumbUpClickListener = thumbUpClickListener;
    }

    public interface ThumbUpClickListener {
        //点赞回调
        void thumbUpFinish();

        //取消回调
        void thumbDownFinish();
    }
}
