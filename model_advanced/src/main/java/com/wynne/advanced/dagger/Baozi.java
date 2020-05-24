package com.wynne.advanced.dagger;

import androidx.annotation.NonNull;

import javax.inject.Inject;

public class Baozi {
    String name;

    @Inject
    public Baozi() {
        name = "小笼包";
    }

    public Baozi(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
