package com.wynne.knowledge.mark.memo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NoteCaretaker {

    private static final int MAX = 30;

    List<Memoto> mMemotos = new ArrayList<>(MAX);

    int mIndex = 0;

    public void saveMemoto(Memoto memoto) {
        if (mMemotos.size() > MAX) {
            mMemotos.remove(0);
        }

        mMemotos.add(memoto);
        mIndex = mMemotos.size() - 1;
        Log.d("XXW", "size :" + mMemotos.size());
    }


    public Memoto getPrevMemoto() {
        mIndex = mIndex > 0 ? --mIndex : mIndex;
        return mMemotos.get(mIndex);
    }

    public Memoto getNextMemoto() {
        mIndex = mIndex < mMemotos.size() - 1 ? ++mIndex : mIndex;
        return mMemotos.get(mIndex);
    }


}
