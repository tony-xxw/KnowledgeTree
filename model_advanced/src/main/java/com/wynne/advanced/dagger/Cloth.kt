package com.wynne.advanced.dagger

import javax.inject.Inject


class Cloth @Inject constructor() {


    var mColor: String = "绿色";


    override fun toString(): String {
        return "$mColor 布料"
    }
}

