package com.wynne.advanced.dagger

import dagger.Component

@Component
public interface Platform {
    fun waimai(): Person
}

@Component(modules = [ShangjiaModule::class, ActivityModule::class])
public interface TakeOutPlatform {
    fun waimai(): Person1

    fun zhuru(waiMai: Person1)

    fun inject(activity: DaggerActivity)
}

