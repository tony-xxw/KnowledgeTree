package com.wynne.knowledge.tree.bookmark;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.BaseFragment;
import com.wynne.knowledge.tree.bookmark.activity.BitmapActivity;
import com.wynne.knowledge.tree.bookmark.activity.HandlerActivity;
import com.wynne.knowledge.tree.bookmark.activity.TaskActivity;
import com.wynne.knowledge.tree.bookmark.activity.ThreadActivity;

/**
 * @author XXW
 * @date 2018/3/9
 */

public class BookMarkFragment extends BaseFragment implements View.OnClickListener {
    public static BookMarkFragment fragment;

    public static BookMarkFragment getInstance() {
        if (fragment == null) {
            fragment = new BookMarkFragment();
        }
        return fragment;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_task:
                startActivity(new Intent(getActivity(), TaskActivity.class));
                break;
            case R.id.btn_handler:
                startActivity(new Intent(getActivity(), HandlerActivity.class));
                break;
            case R.id.btn_bitmap:
                startActivity(new Intent(getActivity(), BitmapActivity.class));
                break;
            case R.id.btn_thread:
                startActivity(new Intent(getActivity(), ThreadActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void initView() {
        mContentView.findViewById(R.id.btn_task).setOnClickListener(this::onClick);
        mContentView.findViewById(R.id.btn_handler).setOnClickListener(this::onClick);
        mContentView.findViewById(R.id.btn_bitmap).setOnClickListener(this::onClick);
        mContentView.findViewById(R.id.btn_thread).setOnClickListener(this::onClick);
    }

    @Override
    public int getLayoutId() {
        return R.layout.boorkmark_layout;
    }
}
