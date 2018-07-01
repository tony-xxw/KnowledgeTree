package com.wynne.knowledge.tree.guide.filter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
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
        findViewById(R.id.btn_implicit_filter).setOnClickListener(this);
        
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
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Share");
                sendIntent.setType("text/plan");
                String title = getString(R.string.share_title);
                //强制分享多个应用,如果想分享给默认应用则不需要通过createChoose来获取Intent
                Intent shareIntent = Intent.createChooser(sendIntent, title);
                //至少存在一个处理项
                if (sendIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(sendIntent);
                    startActivity(shareIntent);
                }
                break;
            case R.id.btn_implicit_filter:
                /**
                 *  过滤条件可以重复多个,
                 *  但是必须存在一个匹配,
                 *  如果不存在一个匹配则无法跳转
                 */
                Intent intent = new Intent();
                intent.setAction("com.wynne.knowledge.tree.Filter");
                intent.setType("video/*");
                intent.addCategory(Intent.CATEGORY_DEFAULT);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    Log.d("XXW", "OK");
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}
