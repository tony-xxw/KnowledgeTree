package com.wynne.knowledge.guide.material;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.guide.R;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author xxw
 */
public class BottomSheetActivity extends BaseActivity {
    BottomSheetBehavior sheetBehavior;
    RecyclerView recyclerView;

    private String[] arrys = {
            "Item1", "Item2", "Item3", "Item4",
            "Item5", "Item6", "Item7", "Item8",
            "Item9", "Item10", "Item11", "Item12",
            "Item13", "Item14", "Item15", "Item16",
            "Item17", "Item18", "Item19", "Item20"
    };
    private List<String> list;

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.rv_list);
        list = Arrays.asList(arrys);
        BottomAdapter adapter = new BottomAdapter();
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);

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


    public class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.ViewHolder> {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BottomSheetActivity.BottomAdapter.ViewHolder holder, int position) {
            holder.mTextView.setText(list.get(position) + " === " + position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView mTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.tv_item);
            }
        }
    }
}
