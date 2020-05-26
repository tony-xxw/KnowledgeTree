package com.wynne.advanced.dagger

import com.wynne.advanced.dagger.DaggerActivity
import com.wynne.advanced.dagger.MainModule
import dagger.Component

//@Component
@Component(modules = [MainModule::class])
interface MainComponent {
    fun injenct(activity: DaggerActivity)
}