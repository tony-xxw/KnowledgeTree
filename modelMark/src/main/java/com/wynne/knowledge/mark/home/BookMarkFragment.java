package com.wynne.knowledge.mark.home;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wynne.knowledge.base.base.view.BaseFragment;
import com.wynne.knowledge.base.constant.ARouterPath;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.activity.CustomActivity;
import com.wynne.knowledge.mark.activity.HandlerActivity;
import com.wynne.knowledge.mark.art.ArtActivity;
import com.wynne.knowledge.mark.art.chapter1.UserManager;
import com.wynne.knowledge.mark.interview.InterViewActivity;
import com.wynne.knowledge.mark.jetpack.JetPackActivity;
import com.wynne.knowledge.mark.structure.StructureActivity;

/**
 * okhttp 拦截器流程
 *
 * @author xxw
 */
public class BookMarkFragment extends BaseFragment implements View.OnClickListener {
    private Button btnArt, btnCustom, btnJetpack, btnInterView, btnStructure,btnHandler;


    @Override
    public void initView() {

        findByView();
        setOnClick();
    }

    private void setOnClick() {
        btnArt.setOnClickListener(this);
        btnCustom.setOnClickListener(this);
        btnJetpack.setOnClickListener(this);
        btnInterView.setOnClickListener(this);
        btnStructure.setOnClickListener(this);
        btnHandler.setOnClickListener(this);
    }

    private void findByView() {
        btnArt = mContentView.findViewById(R.id.btn_art);
        btnCustom = mContentView.findViewById(R.id.btn_custom);
        btnJetpack = mContentView.findViewById(R.id.btn_jetpack);
        btnInterView = mContentView.findViewById(R.id.btn_inter_view);
        btnStructure = mContentView.findViewById(R.id.btn_structure);
        btnHandler = mContentView.findViewById(R.id.btn_handler);

    }

    @Override
    public int getLayoutId() {
        return R.layout.boorkmark_layout;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_art) {
            UserManager.sUserId = 2;
            Log.d("XXW", "userId: " + UserManager.sUserId);
            startActivity(new Intent(getActivity(), ArtActivity.class));

        }
        if (i == R.id.btn_custom) {
            startActivity(new Intent(getActivity(), CustomActivity.class));
        }
        if (i == R.id.btn_jetpack) {
            startActivity(new Intent(getActivity(), JetPackActivity.class));
        }
        if (i == R.id.btn_inter_view) {
            startActivity(new Intent(getActivity(), InterViewActivity.class));
        }
        if (i == R.id.btn_structure) {
            startActivity(new Intent(getActivity(), StructureActivity.class));
        }  if (i == R.id.btn_handler) {
            startActivity(new Intent(getActivity(), HandlerActivity.class));
        }

    }


}
