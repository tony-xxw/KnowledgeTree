package com.wynne.knowledge.guide.material;

import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.View;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.guide.R;

/**
 * @author xxw
 */
public class BottomSheetActivity extends BaseActivity {
    BottomSheetBehavior sheetBehavior;

    @Override
    public void initView() {

        sheetBehavior = BottomSheetBehavior.from(findViewById(R.id.cl_bottomSheet));
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {

                } else {

                }

                // Check Logs to see how bottom sheets behaves
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.e("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.e("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.e("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.e("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.e("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                    default:
                        break;
                }
            }


            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });


    }


    @Override
    public int getLayoutId() {
        return R.layout.bottom_activity;
    }
}
