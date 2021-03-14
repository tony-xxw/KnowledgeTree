package com.wynne.thread;

import android.util.Log;

class Test {

    public static void main(String[] args) {
        final String name = "Wynne";

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("XXW", name);
            }
        });
    }
}
