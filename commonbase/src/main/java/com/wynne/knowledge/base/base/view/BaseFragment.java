package com.wynne.knowledge.base.base.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * @author xxw
 */
public abstract class BaseFragment extends Fragment {
    protected View mContentView;

    boolean isViewInitiated = false;
    boolean isDataLoaded = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(getLayoutId(), container, false);
        return mContentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
//        prepareRequestData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("XXW","setUserVisibleHint");
        prepareRequestData();
    }

    private boolean prepareRequestData() {
        return prepareRequestData(false);
    }

    public boolean prepareRequestData(boolean forceUpdate){
        Log.d("XXW","prepareRequestData: " + getUserVisibleHint());
        if (getUserVisibleHint() && isViewInitiated && (!isDataLoaded || forceUpdate)){
            isDataLoaded =true;
            return true;
        }
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    /**
     * 初始化
     *
     * @return
     */
    public abstract void initView();

    /**
     * 返回布局id
     *
     * @return
     */
    public abstract int getLayoutId();


}