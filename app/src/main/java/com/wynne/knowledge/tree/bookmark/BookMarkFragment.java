package com.wynne.knowledge.tree.bookmark;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.bookmark.activity.HandlerActivity;
import com.wynne.knowledge.tree.bookmark.activity.TaskActivity;

/**
 * @author XXW
 * @date 2018/3/9
 */

public class BookMarkFragment extends Fragment implements View.OnClickListener {
    private View mContentView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.boorkmark_layout, null);
        mContentView.findViewById(R.id.btn_task).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_handler).setOnClickListener(this);
        return mContentView;
    }


    public static BookMarkFragment getInstance() {
        BookMarkFragment fragment = new BookMarkFragment();
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
            default:
                break;
        }
    }

}
