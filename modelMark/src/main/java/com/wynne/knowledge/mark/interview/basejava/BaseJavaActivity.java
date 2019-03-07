package com.wynne.knowledge.mark.interview.basejava;

import android.util.Log;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xxw
 */
public class BaseJavaActivity extends BaseActivity {


    @Override
    public void initView() {

        hashMapReview();
    }

    private void hashMapReview() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("java", 1);
        hashMap.put("object-c", 2);
        hashMap.put("html", 3);
        hashMap.put("python", 4);
        hashMap.put("c", 5);
        hashMap.put("css", 6);
        hashMap.put("fuller", 7);


        for (Map.Entry<String, Integer> stringIntegerEntry : hashMap.entrySet()) {
            Log.d("XXW", stringIntegerEntry.getKey() + "----" + stringIntegerEntry.getValue());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.base_java_activity;
    }
}
