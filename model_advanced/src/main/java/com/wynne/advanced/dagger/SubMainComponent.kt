package com.wynne.advanced.dagger

import dagger.Subcomponent

@PreActivity
@Subcomponent(modules = [MainModule::class])
interface SubMainComponent {
    fun inject(daggerActivity: DaggerActivity)
}