package com.wynne.knowledge.tree.interfere;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.widget.HorizontalScrollViewEx;
import com.wynne.knowledge.tree.widget.HorizontalScrollViewEx2;
import com.wynne.knowledge.tree.widget.ListViewExt;

import java.util.ArrayList;

/**
 * 场景1 外部滑动方向与内部方向不一样
 * 场景2 外埠滑动方向与内部方向一致
 * 场景3 1,2嵌套
 *
 * @author Wynne
 * @date 2018/9/27
 */

public class InterfereActivity extends AppCompatActivity {
    /**
     * 外部拦截法
     */
    private HorizontalScrollViewEx mListContainer;
    /**
     * 内部拦截
     */
    private HorizontalScrollViewEx2 mListContainer2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfere_layout);
//        initView();
        initView2();
    }

    private void initView2() {
        LayoutInflater layoutInflater = getLayoutInflater();
        mListContainer2 = findViewById(R.id.container2);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;


        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) layoutInflater.inflate(R.layout.content_layout2, mListContainer2, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.title);
            textView.setText("page" + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList2(layout);
            mListContainer2.addView(layout);
        }
    }

    private void initView() {
        LayoutInflater layoutInflater = getLayoutInflater();
        mListContainer = findViewById(R.id.container);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;


        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) layoutInflater.inflate(R.layout.content_layout, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.title);
            textView.setText("page" + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> date = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            date.add("name " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_list_item, R.id.name, date);
        listView.setAdapter(adapter);
    }

    private void createList2(ViewGroup layout) {
        ListViewExt listView = (ListViewExt) layout.findViewById(R.id.list);
        ArrayList<String> date = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            date.add("name " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_list_item, R.id.name, date);
        listView.setHorizontalScrollViewEx2(mListContainer2);
        listView.setAdapter(adapter);
    }
}
