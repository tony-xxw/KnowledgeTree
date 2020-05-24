package com.wynne.advanced.dagger

import javax.inject.Inject

class Noodle @Inject constructor() {
    override fun toString(): String {
        return "面条"
    }
}