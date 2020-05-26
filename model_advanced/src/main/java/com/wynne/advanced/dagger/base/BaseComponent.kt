package com.wynne.advanced.dagger.base

import com.wynne.advanced.dagger.ClothHandler
import com.wynne.advanced.dagger.MainModule
import com.wynne.advanced.dagger.SubMainComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BaseModule::class])
interface BaseComponent {
    fun getClothHandler(): ClothHandler

    fun getCubMainComponent(module: MainModule): SubMainComponent
}