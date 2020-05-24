package com.wynne.advanced.dagger

import dagger.Component

@Component(modules = ShangjiaModel::class)
public interface Platform {
    fun waimai(): Person
}