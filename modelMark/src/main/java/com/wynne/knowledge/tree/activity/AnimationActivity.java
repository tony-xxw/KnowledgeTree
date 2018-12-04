package com.wynne.knowledge.tree.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wynne
 * @date 2018/10/18
 */

public class AnimationActivity extends BaseActivity {
    private AnimationAdapter animationAdapter;
    private String[] str = {"1", "2", "3", "4"};

    @Override
    public void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_animation);

        List<String> arry = Arrays.asList(str);
        animationAdapter = new AnimationAdapter(this, arry);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(animationAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.animation_activity;
    }


    class AnimationAdapter extends RecyclerView.Adapter<AnimationAdapter.AnimationViewHolder> {
        private Context mContext;
        private List<String> mList;

        public AnimationAdapter(Context context, List<String> list) {
            mContext = context;
            mList = list;
        }

        @Override
        public AnimationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_layout_recycle, null);
            return new AnimationViewHolder(view);
        }

        @Override
        public void onBindViewHolder(AnimationViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class AnimationViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public AnimationViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.btn_anim);
            }
        }
    }
}
