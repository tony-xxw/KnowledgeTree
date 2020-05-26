package com.wynne.advanced.dagger

import com.wynne.advanced.dagger.base.BaseComponent
import dagger.Component
import javax.inject.Singleton

@PreActivity
@Component(modules = [MainModule::class], dependencies = [BaseComponent::class])
interface MainComponent {
    fun injenct(activity: DaggerActivity)
}