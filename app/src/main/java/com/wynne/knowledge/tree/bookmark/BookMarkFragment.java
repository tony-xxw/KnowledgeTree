package com.wynne.knowledge.tree.bookmark;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wynne.knowledge.tree.R;

/**
 * Created by XXW on 2018/3/9.
 */

public class BookMarkFragment extends Fragment {
    private View mContentView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.boorkmark_layout, null);
        return mContentView;
    }


}
