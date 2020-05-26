package com.wynne.advanced.dagger

class ClothHandler {
    fun handle(cloth: Cloth): Cloths {
        return Cloths(cloth)
    }
}