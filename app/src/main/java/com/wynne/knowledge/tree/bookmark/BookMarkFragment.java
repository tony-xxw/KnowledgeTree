package com.wynne.knowledge.tree.bookmark;

import android.content.Intent;
import android.view.View;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.ScrollingActivity;
import com.wynne.knowledge.tree.base.BaseFragment;
import com.wynne.knowledge.tree.bookmark.activity.BitmapActivity;
import com.wynne.knowledge.tree.bookmark.activity.HandlerActivity;
import com.wynne.knowledge.tree.bookmark.activity.TaskActivity;
import com.wynne.knowledge.tree.bookmark.activity.ThreadActivity;

/**
 * 艺术探索读书笔记
 *
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
            case R.id.btn_ui:
                /**getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {

                }
                });

                 new Handler().post(new Runnable() {
                @Override public void run() {

                }
                });*/
                startActivity(new Intent(getActivity(), ScrollingActivity.class));
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
        mContentView.findViewById(R.id.btn_ui).setOnClickListener(this::onClick);
    }

    @Override
    public int getLayoutId() {
        return R.layout.boorkmark_layout;
    }

/**
 *
 * MeasureSpec 是由自己的LayoutParams 和父容器的MeasureSpec来决定. 对于顶级View(DecoView) 是由窗口大小和自身LayoutParams来决定.
 *
 *  子类的MeasureSpec
 *  如果父类MeasureSpec为MATCH_PARENT
 *  子类的LayoutParams宽度>0
 *  子类大小为LayoutParams 宽度大小, 模式为EXACTLY
 *  子类的LayoutParams宽度等于MATCH_PARENT
 *  子类Size为LayoutParams 宽度测量出来的大小减去padding+margin+已经使用的宽度 模式为EXACTLY
 *  子类的LayoutParams宽度等于WARP_CONTENT
 *  子类Size为LayoutParams 宽度测量出来的大小减去padding+margin+已经使用的宽度 模式为AT_MOST
 *
 *  如果父类MeasureSpec为AT_MOST
 *  子类的LayoutParams宽度>0
 *  子类大小为LayoutParams 宽度大小, 模式为EXACTLY
 *  子类的LayoutParams宽度等于MATCH_PARENT
 *  子类Size为LayoutParams 宽度测量出来的大小减去padding+margin+已经使用的宽度 模式为AT_MOST
 *  子类的LayoutParams宽度等于WARP_CONTENT
 *  子类Size为LayoutParams 宽度测量出来的大小减去padding+margin+已经使用的宽度 模式为AT_MOST
 *
 *
 *  View在一个Activity启动时 获取某个View的宽高,因为View与Activity的生命周期不同步 所有有四种方法解决
 *  1.onWindowFocusChanged
 *  2.view.post(runnable)
 *  3.ViewTreeObserver
 *  4.measure
 */
}

