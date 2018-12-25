package com.wynne.knowledge.main.audio;

import android.media.MediaPlayer;
import android.os.Environment;


import android.util.Log;
import android.view.View;

import com.wynne.knowledge.main.R;
import com.wynne.knowledge.base.base.BaseActivity;

import java.io.File;
import java.io.IOException;

/**
 * @author Wynne
 * @date 2018/4/8
 */

public class AudioActivity extends BaseActivity implements View.OnClickListener {
    private String path;
    private MediaPlayer mediaPlayer;


    @Override
    public void initView() {
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.end_start).setOnClickListener(this);
        findViewById(R.id.play_start).setOnClickListener(this);

        path = getSDPath() + "/record/" + System.currentTimeMillis();
        Log.d("XXW", "path " + path);
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int getLayoutId() {
        return R.layout.audio_layout;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_start) {
            AudioRecorderUtil.getInstance().startRecord(path);

        } else if (i == R.id.end_start) {
            AudioRecorderUtil.getInstance().stopRecord();

        } else if (i == R.id.play_start) {
            playMusic();

        } else {
        }
    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }


    private final class PrepareListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            // TODO Auto-generated method stub
            mediaPlayer.start();//开始播放
        }

    }

    /**
     * 播放录制的音频
     */
    private void playMusic() {

        File file = new File(path);
        if (file.exists()) {
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();//进行数据缓冲

                mediaPlayer.setOnPreparedListener(new PrepareListener());
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}