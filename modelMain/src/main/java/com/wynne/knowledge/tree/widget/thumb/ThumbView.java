package com.wynne.knowledge.tree.widget.thumb;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.utils.StringUtils;

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
    private float mRadius;
    private Path mClipPath;
    private boolean mIsThumbUp;

    private int mClickCount;
    private int mEndCount;
    private ThumbUpClickListener mThumbUpClickListener;
    private boolean isThumbUp;
    private long mLastStartTime;
    private AnimatorSet mThumbUpAnim;

    private static final int START_COLOR = Color.parseColor("#00e24d3d");
    private static final int END_COLOR = Color.parseColor("#88e24d3d");

    private static final float SCALE_MIN = 0.9f;
    private static final float SCALE_MAX = 1f;


    //缩放动画的时间
    private static final int SCALE_DURING = 150;

    private static final int RADIUS_DURING = 100;


    public ThumbView(Context context) {
        this(context, null);
    }

    public ThumbView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
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
        mCirclePaint.setStrokeWidth(StringUtils.dip2px(getContext(), 2));

        mCirclePoint = new TuvPoint();
        mCirclePoint.x = mThumbPoint.x + mThumbWidth / 2;
        mCirclePoint.y = mThumbPoint.y + mThumbHeight / 2;

        mRadiusMax = Math.max(mCirclePoint.x - getPaddingLeft(), mCirclePoint.y - getPaddingTop());
        mRadiusMin = StringUtils.dip2px(getContext(), 8);//这个值是根据点击效果调整得到的
        mClipPath = new Path();
        mClipPath.addCircle(mCirclePoint.x, mCirclePoint.y, mRadiusMax, Path.Direction.CW);
    }

    private void initBitmapInfo() {
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);

        resetBitmap();

        mThumbWidth = mThumbUp.getWidth();
        mThumbHeight = mThumbUp.getHeight();


        Log.d("XXW", "dpi :" + getResources().getDisplayMetrics().density);
        Log.d("XXW", "width :" + mThumbWidth);
        Log.d("XXW", "height :" + mThumbHeight);

        mShiningWidth = mShining.getWidth();
        mShiningHeight = mShining.getHeight();

        mShiningPoint = new TuvPoint();
        mThumbPoint = new TuvPoint();
        //XXW
        mShiningPoint.x = getPaddingLeft() + StringUtils.dip2px(getContext(), 2);
        mShiningPoint.y = getPaddingTop();
        //XXW
        mThumbPoint.x = getPaddingLeft();
        mThumbPoint.y = getPaddingTop() + StringUtils.dip2px(getContext(), 8);

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
                StringUtils.getDefaultSize(widthMeasureSpec, getContentWidth() + getPaddingLeft() + getPaddingRight()),
                StringUtils.getDefaultSize(heightMeasureSpec, getContentHeight() + getPaddingTop() + getPaddingBottom())
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
        if (mIsThumbUp) {
            if (mClipPath != null) {
                canvas.save();
                canvas.clipPath(mClipPath);
                canvas.drawBitmap(mShining, mShiningPoint.x, mShiningPoint.y, mBitmapPaint);
                canvas.restore();
            }
            canvas.drawBitmap(mThumbUp, mThumbPoint.x, mThumbPoint.y, mBitmapPaint);
        } else {
            canvas.drawBitmap(mThumbNormal, mThumbPoint.x, mThumbPoint.y, mBitmapPaint);
        }
    }

    public TuvPoint getCirclePoint() {
        return mCirclePoint;
    }

    public void setIsThumbUp(boolean isThumbUp) {
        mIsThumbUp = isThumbUp;
        mClickCount = mIsThumbUp ? 1 : 0;
        mEndCount = mClickCount;
        postInvalidate();
    }

    public boolean isThumbUp() {
        return mIsThumbUp;
    }

    public void setThumbUpClickListener(ThumbUpClickListener thumbUpClickListener) {
        this.mThumbUpClickListener = thumbUpClickListener;
    }

    public void startAnim() {
        mClickCount++;
        boolean isFastAnim = false;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - mLastStartTime < 300) {
            isFastAnim = true;
        }
        mLastStartTime = currentTimeMillis;

        if (mIsThumbUp) {
            if (isFastAnim) {
                startFastAnim();
                return;
            }
            startThumbDownAnim();
            mClickCount = 0;
        } else {
            if (mThumbUpAnim != null) {
                mClickCount = 0;
            } else {
                startThumbUpAnim();
                mClickCount = 1;
            }
        }
        mEndCount = mClickCount;
    }

    private void startThumbUpAnim() {
        ObjectAnimator notThumbUpScale = ObjectAnimator.ofFloat(this, "notThumbUpScale", SCALE_MAX, SCALE_MIN);
        notThumbUpScale.setDuration(SCALE_DURING);
        notThumbUpScale.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mIsThumbUp = true;
            }
        });


        ObjectAnimator thumbUpScale = ObjectAnimator.ofFloat(this, "thumbUpScale", SCALE_MIN, SCALE_MAX);
        thumbUpScale.setDuration(SCALE_DURING);
        thumbUpScale.setInterpolator(new OvershootInterpolator());

        ObjectAnimator circleScale = ObjectAnimator.ofFloat(this, "circleScale", mRadiusMin, mRadiusMax);
        circleScale.setDuration(RADIUS_DURING);

        mThumbUpAnim = new AnimatorSet();
        mThumbUpAnim.play(thumbUpScale).with(circleScale);
        mThumbUpAnim.play(thumbUpScale).after(notThumbUpScale);
        mThumbUpAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mThumbUpAnim = null;
                if (mThumbUpClickListener != null) {
                    mThumbUpClickListener.thumbUpFinish();
                }
            }
        });
        mThumbUpAnim.start();
    }

    private void startThumbDownAnim() {
        ObjectAnimator thumbUpScale = ObjectAnimator.ofFloat(this, "thumbUpScale", SCALE_MIN, SCALE_MAX);
        thumbUpScale.setDuration(SCALE_DURING);
        thumbUpScale.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mIsThumbUp = false;
                setNotThumbUpScale(SCALE_MAX);
                if (mThumbUpClickListener != null) {
                    mThumbUpClickListener.thumbDownFinish();
                }
            }
        });

        thumbUpScale.start();
    }

    private void startFastAnim() {
        ObjectAnimator thumbUpScale = ObjectAnimator.ofFloat(this, "thumbUpScale", SCALE_MIN, SCALE_MAX);
        thumbUpScale.setDuration(SCALE_DURING);
        thumbUpScale.setInterpolator(new OvershootInterpolator());

        ObjectAnimator circleScale = ObjectAnimator.ofFloat(this, "circleScale", mRadiusMin, mRadiusMax);
        circleScale.setDuration(RADIUS_DURING);

        AnimatorSet set = new AnimatorSet();
        set.play(thumbUpScale).with(circleScale);

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mEndCount++;
                if (mClickCount != mEndCount) {
                    return;
                }

                if (mClickCount % 2 == 0) {
                    startThumbDownAnim();
                } else {
                    if (mThumbUpClickListener != null) {
                        mThumbUpClickListener.thumbUpFinish();
                    }
                }
            }
        });
        set.start();
    }

    public void setNotThumbUpScale(float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        mThumbNormal = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_unselected);
        mThumbNormal = Bitmap.createBitmap(mThumbNormal, 0, 0, mThumbNormal.getWidth(), mThumbNormal.getHeight(), matrix, true);
        postInvalidate();
    }

    public void setThumbUpScale(float scale) {
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        mThumbUp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected);
        mThumbUp = Bitmap.createBitmap(mThumbUp, 0, 0, mThumbUp.getWidth(), mThumbUp.getHeight(), matrix, true);
        postInvalidate();
    }

    public void setShiningScale(float scale) {
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        mShining = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected_shining);
        mShining = Bitmap.createBitmap(mShining, 0, 0, mShining.getWidth(), mShining.getHeight(), matrix, true);
        postInvalidate();
    }

    public void setCircleScale(float radius) {
        mRadius = radius;
        mClipPath = new Path();
        mClipPath.addCircle(mCirclePoint.x, mCirclePoint.y, mRadius, Path.Direction.CW);
        float fraction = (mRadiusMax - radius) / (mRadiusMax - mRadiusMin);
        mCirclePaint.setColor((int) StringUtils.evaluate(fraction, START_COLOR, END_COLOR));
        postInvalidate();
    }


    public interface ThumbUpClickListener {
        //点赞回调
        void thumbUpFinish();

        //取消回调
        void thumbDownFinish();
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }
}
