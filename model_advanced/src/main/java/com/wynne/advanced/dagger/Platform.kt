package com.wynne.advanced.dagger

import dagger.Component

@Component()
public interface Platform {
    fun waimai(): Person
}