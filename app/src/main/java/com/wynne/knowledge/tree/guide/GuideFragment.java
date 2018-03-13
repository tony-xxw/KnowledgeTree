package com.wynne.knowledge.tree.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.guide.appbar.AppbarActivity;
import com.wynne.knowledge.tree.guide.constrain.ConstraintActivity;
import com.wynne.knowledge.tree.guide.filter.ImplicitActivity;
import com.wynne.knowledge.tree.guide.notification.NotificationActivity;
import com.wynne.knowledge.tree.guide.service.ServiceActivity;
import com.wynne.knowledge.tree.guide.systemstatus.StatusBarActivity;

/**
 * @author Wynne
 * @date 2018/2/28
 */

public class GuideFragment extends Fragment implements View.OnClickListener {
    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = LayoutInflater.from(getActivity()).inflate(R.layout.guide_fragment, container, false);
        initClickListener();
        return mContentView;
    }


    public void initClickListener() {
        mContentView.findViewById(R.id.btn_implicit).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_cl).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_notification).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_actionbar).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_status).setOnClickListener(this);
        mContentView.findViewById(R.id.btn_service).setOnClickListener(this);
    }

    public static GuideFragment getInstance() {
        GuideFragment fragment = new GuideFragment();

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
            default:
                break;
        }
    }

}
