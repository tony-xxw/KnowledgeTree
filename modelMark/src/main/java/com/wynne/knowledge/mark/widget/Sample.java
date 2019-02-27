package com.wynne.knowledge.mark.widget;

import android.util.Log;

public class Sample {


    Sample() {

    }

    public static final class Build {
        String a;

        public Build() {
            a = "10";
            Log.d("XXW", "a : " + a);
        }

        public Sample build() {
            return new Sample();
        }
    }
}
