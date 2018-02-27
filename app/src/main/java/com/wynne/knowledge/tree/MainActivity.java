package com.wynne.knowledge.tree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * @author XXW
 */
public class MainActivity extends AppCompatActivity implements InitContent {
    private RecyclerView mRvContent;
    private String[] mStrings = {};
    private List<String> mListContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDate();
        initView();
    }

    @Override
    public void initView() {
        mRvContent = (RecyclerView) findViewById(R.id.rv_content);
    }

    @Override
    public void initDate() {
        mListContent = Arrays.asList(mStrings);
    }


    public class RvAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
