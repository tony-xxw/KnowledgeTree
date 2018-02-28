package com.wynne.knowledge.tree;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wynne.knowledge.tree.guide.GuideFragment;

import java.util.Arrays;
import java.util.List;

/**
 * @author XXW
 */
public class MainActivity extends FragmentActivity implements InitContent {
    private BottomNavigationView mBngMenu;
    private FragmentManager mManager;
    private FragmentTransaction mTranscation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDate();
        initView();
    }

    @Override
    public void initView() {

        mBngMenu = (BottomNavigationView) findViewById(R.id.bnm_menu);
        mBngMenu.setOnNavigationItemSelectedListener(listener);


        mTranscation.add(R.id.fl_content, GuideFragment.getInstance(), "guide");
        mTranscation.addToBackStack("guide");
        mTranscation.commit();
    }

    @Override
    public void initDate() {
        mManager = getSupportFragmentManager();
        mTranscation = mManager.beginTransaction();
    }


    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.guide:
                    Log.d("XXW", "guide");
                    return true;
                case R.id.custom:
                    Log.d("XXW", "custom");
                    return true;
                case R.id.train:
                    Log.d("XXW", "train");
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
