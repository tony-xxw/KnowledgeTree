package com.wynne.advanced.dagger

import javax.inject.Inject

class Tongyi @Inject constructor() : Noodle() {

    override fun toString(): String {
        return "统一方便面"
    }
}