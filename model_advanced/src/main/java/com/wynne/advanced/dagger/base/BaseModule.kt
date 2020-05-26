package com.wynne.advanced.dagger.base

import com.wynne.advanced.dagger.ClothHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule {

    @Singleton
    @Provides
    fun getClothsHandler(): ClothHandler {
        return ClothHandler()
    }
}