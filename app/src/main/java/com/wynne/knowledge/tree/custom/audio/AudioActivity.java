package com.wynne.knowledge.tree.custom.audio;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wynne.knowledge.tree.R;

import java.io.File;
import java.io.IOException;

/**
 * @author Wynne
 * @date 2018/4/8
 */

public class AudioActivity extends AppCompatActivity implements View.OnClickListener {
    private String path;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_layout);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.end_start).setOnClickListener(this);
        findViewById(R.id.play_start).setOnClickListener(this);

        path = getSDPath() + "/record/" + System.currentTimeMillis();
        Log.d("XXW", "path " + path);
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                AudioRecorderUtil.getInstance().startRecord(path);
                break;
            case R.id.end_start:
                AudioRecorderUtil.getInstance().stopRecord();
                break;
            case R.id.play_start:
                playMusic();
                break;
            default:
                break;
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
