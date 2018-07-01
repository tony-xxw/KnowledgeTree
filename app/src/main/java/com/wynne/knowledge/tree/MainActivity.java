package com.wynne.knowledge.tree;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.wynne.knowledge.tree.bookmark.BookMarkFragment;
import com.wynne.knowledge.tree.custom.CustomFragment;
import com.wynne.knowledge.tree.guide.GuideFragment;

/**
 * @author XXW
 */
public class MainActivity extends FragmentActivity implements InitContent {
    private BottomNavigationView mBngMenu;
    private CustomFragment mCustomFragment;
    private GuideFragment mCuideFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initDate();
        initView();
        Log.d("XXW", "empty");
    }

    @Override
    public void initView() {

        mBngMenu = (BottomNavigationView) findViewById(R.id.bnm_menu);
        mBngMenu.setOnNavigationItemSelectedListener(listener);


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("XXW","如果是透明主题 则不会回调onPause");
    }

    @Override
    public void initDate() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_content, GuideFragment.getInstance(), "guide")
                .addToBackStack("guide")
                .commit();
    }


    /**
     * popBackStack()
     */
    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.custom:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content, CustomFragment.getInstance(), "custom")
                            .addToBackStack("custom")
                            .commit();
                    return true;
                case R.id.guide:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content, GuideFragment.getInstance(), "guide")
                            .addToBackStack("guide")
                            .commit();
                    return true;
                case R.id.bookmark:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content, BookMarkFragment.getInstance(), "bookmark")
                            .addToBackStack("bookmark")
                            .commit();
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    public class RvAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
