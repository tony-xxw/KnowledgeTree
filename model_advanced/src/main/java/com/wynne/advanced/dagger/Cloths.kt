package com.wynne.advanced.dagger

import com.wynne.advanced.dagger.Cloth

class Cloths(cloth: Cloth) {
    var mCloth: Cloth = cloth

    override fun toString(): String {
        return mCloth.mColor + " 衣服"
    }
}