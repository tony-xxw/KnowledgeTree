package com.wynne.knowledge.mark.memo;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wynne.knowledge.base.base.BaseActivity;
import com.wynne.knowledge.mark.R;

/**
 * 备忘录模式
 *
 * @author Wynne
 */
public class MemoActivity extends BaseActivity {
    NoteEditText editText;
    Button btnReplea, btnSave, btnRest;

    private NoteCaretaker caretaker = new NoteCaretaker();

    @Override
    public void initView() {

        editText = findViewById(R.id.et_content);
        btnReplea = findViewById(R.id.btn_repeal);
        btnSave = findViewById(R.id.btn_save);
        btnRest = findViewById(R.id.btn_rest);

        btnReplea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.resetMemoto(caretaker.getPrevMemoto());
                makeToast("撤销");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caretaker.saveMemoto(editText.createMemoto());
                makeToast("保存笔记");
            }
        });

        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.resetMemoto(caretaker.getNextMemoto());
                makeToast("重做");
            }
        });
    }

    private void makeToast(String msg) {
        Toast.makeText(this, msg + editText.getText() + ", 光标位置: " + editText.getSelectionStart(), Toast.LENGTH_LONG).show();

    }

    @Override
    public int getLayoutId() {
        return R.layout.memo_activity;
    }
}
