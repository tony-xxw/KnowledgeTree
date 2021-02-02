package com.wynne.android.third.dagger

import dagger.Component
import javax.inject.Inject

class Bread @Inject constructor() {
    override fun toString(): String = "小笼包"
}


class Noodle @Inject constructor() {
    override fun toString(): String = "面条"
}

class Zhainan @Inject constructor() {
    @JvmField
    @Inject
    var noodle: Noodle? = null

    @JvmField
    @Inject
    var bread: Bread? = null

    fun eat(): String {
        var content = ""
        bread.let {
            content += it.toString()
        }

        noodle?.let {
            content += it.toString()
        }
        return content
    }
}

@Component
interface Platform {
    fun waimai(): Zhainan
}

class Foo {
    companion object
    class Bar {
        @Component
        interface FooComponent {}
    }
}