package com.wynne.knowledge.tree.custom.interfere;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.widget.HorizontalScrollViewEx;

/**
 * 场景1 外部滑动方向与内部方向不一样
 * 场景2 外埠滑动方向与内部方向一致
 * 场景3 1,2嵌套
 *
 * @author Wynne
 * @date 2018/9/27
 */

public class InterfereActivity extends AppCompatActivity {
    //外部拦截法
    private HorizontalScrollViewEx mListContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfere_layout);
        initView();
    }

    private void initView() {
        LayoutInflater layoutInflater = getLayoutInflater();
        mListContainer = findViewById(R.id.container);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;


        for (int i = 0; i < 3; i++) {

        }
    }
}
