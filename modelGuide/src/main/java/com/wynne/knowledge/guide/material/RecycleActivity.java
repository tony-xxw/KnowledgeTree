package com.wynne.knowledge.guide.material;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.guide.R;

import java.util.Arrays;
import java.util.List;

/**
 * @author Wynne
 * @date 2018/4/27
 */

public class RecycleActivity extends BaseActivity {
    private String[] arrys = {
            "Item1", "Item2", "Item3", "Item4",
            "Item5", "Item6", "Item7", "Item8",
            "Item9", "Item10", "Item11", "Item12",
            "Item13", "Item14", "Item15", "Item16",
            "Item17", "Item18", "Item19", "Item20"
    };
    private List<String> mList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_layout);
        initView();

    }

    @Override
    public void initView() {
        mList = Arrays.asList(arrys);
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecycleViewAdapter adapter = new RecycleViewAdapter(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public int getLayoutId() {
        return R.layout.mine_layout;
    }


    public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
        private Context mContext;

        public RecycleViewAdapter(Context context) {
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.d("XXW", "viewType :" + viewType);
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setText(mList.get(position) + " === " + position);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView mTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }


    }
}
