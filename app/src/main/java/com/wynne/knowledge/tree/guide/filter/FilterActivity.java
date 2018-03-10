package com.wynne.knowledge.tree.guide.filter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.wynne.knowledge.tree.R;

/**
 * @author Wynne
 * @date 2018/3/5
 */

public class FilterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button mSend;
    private Spinner mSpinner;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_activity);
        mSend = (Button) findViewById(R.id.button_send);
        initSpinner();

    }

    private void initSpinner() {
        mSpinner = (Spinner) findViewById(R.id.sp_study);
        mSpinner.setOnItemSelectedListener(this);

        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_item, android.R.layout.simple_list_item_1);
        //设置为下拉的布局
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
    }


    public void onClick(View view) {
        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rb_yes:
                if (check) {
                    mSend.setText("Yes");
                }
                break;
            case R.id.rb_maybe:
                if (check) {
                    mSend.setText("Maybe");
                }
                break;
            case R.id.rb_no:
                if (check) {
                    mSend.setText("No");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String changeBtn = (String) parent.getItemAtPosition(position);
        mSend.setText(changeBtn);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
