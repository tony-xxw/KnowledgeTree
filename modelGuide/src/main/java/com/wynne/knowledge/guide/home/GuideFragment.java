package com.wynne.knowledge.guide.home;

import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wynne.knowledge.guide.R;
import com.wynne.knowledge.guide.material.MaterialActivity;
import com.wynne.knowledge.guide.systemstatus.StatusBarActivity;

import com.wynne.knowledge.guide.appbar.AppbarActivity;
import com.wynne.knowledge.base.base.view.BaseFragment;
import com.wynne.knowledge.base.constant.ARouterPath;
import com.wynne.knowledge.guide.filter.ImplicitActivity;
import com.wynne.knowledge.guide.constrain.ConstraintActivity;
import com.wynne.knowledge.guide.notification.NotificationActivity;
import com.wynne.knowledge.guide.service.ServiceActivity;
import com.wynne.knowledge.guide.webview.WebViewActivity;

/**
 * @author Wynne
 * @date 2018/2/28
 */

@Route(path = ARouterPath.FRAGMENT_GUIDE)
public class GuideFragment extends BaseFragment implements View.OnClickListener {


    @Override
    public void initView() {
        initClickListener();
    }

    @Override
    public int getLayoutId() {
        return R.layout.guide_fragment;
    }


    public void initClickListener() {
        mContentView.findViewById(R.id.btn_implicit).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_cl).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_notification).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_actionbar).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_status).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_service).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_webview).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_material).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_clone).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_forward).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_logout).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_implicit) {
            startActivity(new Intent(getActivity(), ImplicitActivity.class));

        } else if (i == R.id.btn_cl) {
            startActivity(new Intent(getActivity(), ConstraintActivity.class));

        } else if (i == R.id.btn_notification) {
            startActivity(new Intent(getActivity(), NotificationActivity.class));

        } else if (i == R.id.btn_actionbar) {
            startActivity(new Intent(getActivity(), AppbarActivity.class));

        } else if (i == R.id.btn_status) {
            startActivity(new Intent(getActivity(), StatusBarActivity.class));

        } else if (i == R.id.btn_service) {
            startActivity(new Intent(getActivity(), ServiceActivity.class));

        } else if (i == R.id.btn_webview) {
            startActivity(new Intent(getActivity(), WebViewActivity.class));

        } else if (i == R.id.btn_material) {
            startActivity(new Intent(getActivity(), MaterialActivity.class));

        } else if (i == R.id.btn_clone) {
//            startActivity(new Intent(getActivity(), LoginActivity.class));

        } else if (i == R.id.btn_forward) {
//            LoginContext.getLoginContext().forWard(getActivity());

        } else if (i == R.id.btn_logout) {
//            LoginContext.getLoginContext().setState(new LogoutState());

        } else {
        }
    }

}
