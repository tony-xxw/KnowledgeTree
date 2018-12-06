package com.wynne.knowledge.mark.memo;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.widget.EditText;

public class NoteEditText extends AppCompatEditText {
    public NoteEditText(Context context) {
        super(context);
    }

    public NoteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Memoto createMemoto() {
        Memoto noteMemoto = new Memoto();
        noteMemoto.text = getText().toString();
        noteMemoto.cursor = getSelectionStart();
        return noteMemoto;
    }

    public void resetMemoto(Memoto memoto) {
        setText(memoto.text);
        setSelection(memoto.cursor);
    }


}
