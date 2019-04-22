package com.wynne.knowledge.mark.structure;

import android.util.Log;
import android.view.View;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.structure.linked.QueueLinked;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author xxw
 */
public class StructureActivity extends BaseActivity {

    @Override
    public void initView() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.structure_activity;
    }


    public void onClick(View view) {
        if (view.getId() == R.id.btn_linked) {
            QueueLinked q = new QueueLinked();
            q.enqueue("Android");
            q.enqueue("IOS");
            q.enqueue("Web");
            q.enqueue("Java");

            for (int i = 0; i < 4; i++) {
                Log.d("XXW", "取出: " + q.dequeue().toString());
            }
        } else if (view.getId() == R.id.btn_linkedList) {
            LinkedList<String> linkedList = new LinkedList<>();
            linkedList.add("Android");
            linkedList.add("IOS");
            linkedList.add("Web");
            linkedList.add("Java");

            linkedList.add(2, "随机插入");
            new ArrayList<>();


            linkedList.get(0);
            Log.d("XXW", "First :" + linkedList.getFirst());
            Log.d("XXW", "Last :" + linkedList.getLast());

        }
    }
}
