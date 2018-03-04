package com.wynne.knowledge.tree.guide;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wynne.knowledge.tree.R;

/**
 * @author XXW
 * @date 2018/3/4
 */

public class ImplicitActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.implicit_activity);
        findViewById(R.id.btn_implicit_check).setOnClickListener(this);
        findViewById(R.id.btn_implicit_share).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_implicit_check:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //判断是否存在组件
                if (getPackageManager().resolveActivity(cameraIntent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
                    try {
                        startActivity(cameraIntent);
                    } catch (ActivityNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                //PackageManager.MATCH_DEFAULT_ONLY 默认一个处理的应用  为0 则是查找所有
                if (getPackageManager().queryIntentActivities(cameraIntent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0) {
                    Log.d("XXW", "size==" + getPackageManager().queryIntentActivities(cameraIntent, PackageManager.MATCH_DEFAULT_ONLY).size());
                }
                break;
            case R.id.btn_implicit_share:
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                String title = getString(R.string.share_tilte);
                Intent shareIntent = Intent.createChooser(sendIntent, title);
                //至少存在一个处理项
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(shareIntent);
                }
                break;
            default:
                break;
        }
    }
}
