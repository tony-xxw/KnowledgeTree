package com.wynne.knowledge.mark.home;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wynne.knowledge.base.base.view.BaseFragment;
import com.wynne.knowledge.base.constant.ARouterPath;
import com.wynne.knowledge.mark.R;
import com.wynne.knowledge.mark.activity.CustomActivity;
import com.wynne.knowledge.mark.interview.InterViewActivity;
import com.wynne.knowledge.mark.jetpack.JetPackActivity;
import com.wynne.knowledge.mark.art.ArtActivity;
import com.wynne.knowledge.mark.memo.MemoActivity;

/**
 * @author xxw
 */
@Route(path = ARouterPath.FRAGMENT_BOOKMARK)
public class BookMarkFragment extends BaseFragment implements View.OnClickListener {
    private Button btnMemo, btnArt, btnCustom, btnJetpack, btnInterView;

    @Override
    public void initView() {

        findByView();
        setOnClick();
    }

    private void setOnClick() {
        btnMemo.setOnClickListener(this);
        btnArt.setOnClickListener(this);
        btnCustom.setOnClickListener(this);
        btnJetpack.setOnClickListener(this);
        btnInterView.setOnClickListener(this);
    }

    private void findByView() {
        btnMemo = mContentView.findViewById(R.id.btn_memo);
        btnArt = mContentView.findViewById(R.id.btn_art);
        btnCustom = mContentView.findViewById(R.id.btn_custom);
        btnJetpack = mContentView.findViewById(R.id.btn_jetpack);
        btnInterView = mContentView.findViewById(R.id.btn_inter_view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.boorkmark_layout;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_memo) {
            startActivity(new Intent(getActivity(), MemoActivity.class));

        }
        if (i == R.id.btn_art) {
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

    }
}
