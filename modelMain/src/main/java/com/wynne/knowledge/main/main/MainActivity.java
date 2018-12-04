package com.wynne.knowledge.main.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.wynne.knowledge.main.R;
import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.base.service.ServiceFactory;

import static com.wynne.knowledge.base.constant.FlagConstant.BOOK_TAG_TASK;
import static com.wynne.knowledge.base.constant.FlagConstant.CUSTOM_TAG_TASK;
import static com.wynne.knowledge.base.constant.FlagConstant.GUIDE_TAG_TASK;

/**
 * @author Wynne
 */
public class MainActivity extends BaseActivity {
    @Override
    public void initView() {
        ServiceFactory.getInstance().getAccountService().newUserFragment
                (MainActivity.this, R.id.fl_content, getSupportFragmentManager(), GUIDE_TAG_TASK, GUIDE_TAG_TASK);
        BottomNavigationView mBngMenu = findViewById(R.id.bnm_menu);
        mBngMenu.setOnNavigationItemSelectedListener(listener);

    }


    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }


    /**
     * popBackStack()
     */
    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.custom) {
                ServiceFactory.getInstance().getAccountService().newUserFragment
                        (MainActivity.this, R.id.fl_content, getSupportFragmentManager(), CUSTOM_TAG_TASK, CUSTOM_TAG_TASK);
                return true;
            } else if (i == R.id.guide) {
                ServiceFactory.getInstance().getAccountService().newUserFragment
                        (MainActivity.this, R.id.fl_content, getSupportFragmentManager(), GUIDE_TAG_TASK, GUIDE_TAG_TASK);
                return true;
            } else if (i == R.id.bookmark) {
                ServiceFactory.getInstance().getAccountService().newUserFragment
                        (MainActivity.this, R.id.fl_content, getSupportFragmentManager(), BOOK_TAG_TASK, BOOK_TAG_TASK);
                return true;
            }
            return false;
        }
    };


}
