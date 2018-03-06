package com.wynne.knowledge.tree.custom;

import android.content.Intent;
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

public class CustomFragment extends Fragment implements View.OnClickListener {


    public static CustomFragment getInstance() {
        CustomFragment fragment = new CustomFragment();
        return fragment;
    }

    View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.custom_fragment, null);
        mContentView.findViewById(R.id.btn_classloader).setOnClickListener(this);
        return mContentView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_classloader:
                startActivity(new Intent(getActivity(), ClassLoaderActivity.class));
                break;
            default:
                break;
        }
    }
}
