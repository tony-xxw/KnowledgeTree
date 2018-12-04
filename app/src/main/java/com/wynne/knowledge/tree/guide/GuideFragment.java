package com.wynne.knowledge.tree.guide;

import android.content.Intent;
import android.view.View;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.base.view.BaseFragment;
import com.wynne.knowledge.tree.design.LoginActivity;
import com.wynne.knowledge.tree.design.LoginContext;
import com.wynne.knowledge.tree.design.LogoutState;
import com.wynne.knowledge.tree.appbar.AppbarActivity;
import com.wynne.knowledge.tree.guide.constrain.ConstraintActivity;
import com.wynne.knowledge.tree.guide.filter.ImplicitActivity;
import com.wynne.knowledge.tree.guide.material.MaterialActivity;
import com.wynne.knowledge.tree.guide.notification.NotificationActivity;
import com.wynne.knowledge.tree.guide.service.ServiceActivity;
import com.wynne.knowledge.tree.guide.systemstatus.StatusBarActivity;
import com.wynne.knowledge.tree.guide.webview.WebViewActivity;

/**
 * @author Wynne
 * @date 2018/2/28
 */

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

    public static com.wynne.knowledge.tree.home.GuideFragment getInstance() {
        com.wynne.knowledge.tree.home.GuideFragment fragment = new com.wynne.knowledge.tree.home.GuideFragment();

        return fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_implicit:
                startActivity(new Intent(getActivity(), ImplicitActivity.class));
                break;
            case R.id.btn_cl:
                startActivity(new Intent(getActivity(), ConstraintActivity.class));
                break;
            case R.id.btn_notification:
                startActivity(new Intent(getActivity(), NotificationActivity.class));
                break;
            case R.id.btn_actionbar:
                startActivity(new Intent(getActivity(), AppbarActivity.class));
                break;
            case R.id.btn_status:
                startActivity(new Intent(getActivity(), StatusBarActivity.class));
                break;
            case R.id.btn_service:
                startActivity(new Intent(getActivity(), ServiceActivity.class));
                break;
            case R.id.btn_webview:
                startActivity(new Intent(getActivity(), WebViewActivity.class));
                break;
            case R.id.btn_material:
                startActivity(new Intent(getActivity(), MaterialActivity.class));
                break;
            case R.id.btn_clone:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.btn_forward:
                LoginContext.getLoginContext().forWard(getActivity());
                break;
            case R.id.btn_logout:
                LoginContext.getLoginContext().setState(new LogoutState());
                break;
            default:
                break;
        }
    }

}
