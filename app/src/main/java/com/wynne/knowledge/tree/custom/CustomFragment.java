package com.wynne.knowledge.tree.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wynne.knowledge.tree.R;

/**
 * @author XXW
 * @date 2018/2/28
 */

public class CustomFragment extends Fragment {


    public static CustomFragment getInstance() {
        CustomFragment fragment = new CustomFragment();
        return fragment;
    }

    View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_custom, null);
        return mContentView;
    }
}
