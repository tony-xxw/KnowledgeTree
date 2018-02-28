package com.wynne.knowledge.tree.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wynne.knowledge.tree.R;

/**
 * Created by Wynne on 2018/2/28.
 */

public class GuideFragment extends Fragment {
    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_guide, container, false);
        return mContentView;
    }

    public static GuideFragment getInstance() {
        GuideFragment fragment = new GuideFragment();

        return fragment;
    }
}
