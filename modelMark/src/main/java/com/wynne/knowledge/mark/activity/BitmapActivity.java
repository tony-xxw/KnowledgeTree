package com.wynne.knowledge.mark.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

import java.lang.ref.WeakReference;

/**
 * @author kf-010
 */
public class BitmapActivity extends BaseActivity {
    ImageView mBitmapIv;
    final MyHandler handler = new MyHandler(BitmapActivity.this);


    @Override
    public void initView() {
        mBitmapIv = findViewById(R.id.iv_bitmap);
        mBitmapIv.setImageBitmap(getBitmap(100, 100));

        Glide.with(this).load(R.drawable.icon_large).into(mBitmapIv);


    }


    private Bitmap getBitmap(int requestX, int requestY) {
        Bitmap bitmapFactory = BitmapFactory.decodeResource(getResources(), R.drawable.icon_large);
        Log.d("XXW", "压缩前的值内存大小: " + bitmapFactory.getByteCount());

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.icon_large, options);
        int width = options.outWidth;
        int height = options.outHeight;

        String imageType = options.outMimeType;
        Log.d("XXW", "width ->" + width + " == height ->" + height + "== type ->" + imageType);

        options.inSampleSize = calculateInSampleSize(options, requestX, requestY);

        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_large, options);
        Log.d("XXW", "压缩后的值内存大小: " + bitmap.getByteCount());
        return bitmap;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int requestX, int requestY) {
        final int width = options.outWidth;
        final int height = options.outHeight;
        int sampleSize = 1;

        if (height > requestY || width > requestX) {
            final int halfWidth = width / 2;
            final int halfHeight = height / 2;

            while (halfWidth / sampleSize > requestX && halfHeight / sampleSize > requestY) {
                sampleSize *= 2;
            }
        }
        return sampleSize;
    }

    @Override
    public int getLayoutId() {
        return R.layout.bitmap_activity;
    }


    static class MyHandler extends Handler {

        public MyHandler(BitmapActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        WeakReference<BitmapActivity> weakReference;


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            BitmapActivity activity = weakReference.get();
        }
    }
}
