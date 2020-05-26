package com.wynne.advanced.dagger

import com.wynne.advanced.dagger.base.BaseComponent
import dagger.Component
import javax.inject.Singleton

@PreActivity
@Component(modules = [SecondModule::class],dependencies = [BaseComponent::class])
interface SecondComponent {
    fun inject(daggerOneActivity: DaggerOneActivity)
}