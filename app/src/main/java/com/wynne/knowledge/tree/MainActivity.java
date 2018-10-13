package com.wynne.knowledge.tree;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;

import com.wynne.knowledge.tree.base.BaseActivity;
import com.wynne.knowledge.tree.bookmark.BookMarkFragment;
import com.wynne.knowledge.tree.custom.CustomFragment;
import com.wynne.knowledge.tree.guide.GuideFragment;

/**
 * @author XXW
 */
public class MainActivity extends BaseActivity {
              

    @Override
    public void initView() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, GuideFragment.getInstance(), "guide")
                .addToBackStack("guide")
                .commit();

        BottomNavigationView mBngMenu = findViewById(R.id.bnm_menu);
        mBngMenu.setOnNavigationItemSelectedListener(listener);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("XXW", "onConfigurationChanged : " + newConfig.orientation);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.d("XXW", "onSaveInstanceState ");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.d("XXW", "onRestoreInstanceState ");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("XXW", "onNewIntent ");
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }


    /**
     * popBackStack()
     */
    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.custom:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content, CustomFragment.getInstance(), "custom")
                            .addToBackStack("custom")
                            .commit();
                    return true;
                case R.id.guide:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content, GuideFragment.getInstance(), "guide")
                            .addToBackStack("guide")
                            .commit();
                    return true;
                case R.id.bookmark:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content, BookMarkFragment.getInstance(), "bookmark")
                            .addToBackStack("bookmark")
                            .commit();
                    //模块化
                    return true;
                default:
                    break;
            }
            return false;
        }
    };


}
