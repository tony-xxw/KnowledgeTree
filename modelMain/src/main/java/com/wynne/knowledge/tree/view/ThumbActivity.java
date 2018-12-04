package com.wynne.knowledge.tree.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wynne.knowledge.tree.R;
import com.wynne.knowledge.tree.widget.ruler.Ruler;
import com.wynne.knowledge.tree.widget.ruler.UnitLayout;

/**
 * @author Wynne
 * @date 2018/7/5
 */

public class ThumbActivity extends AppCompatActivity {
    private Ruler mRuler;
    private UnitLayout mUnitLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thumb);

        mRuler = (Ruler) findViewById(R.id.ruler);
        mUnitLayout = (UnitLayout) findViewById(R.id.unit_layout);
        mUnitLayout.bindRuler(mRuler);
    }
}
