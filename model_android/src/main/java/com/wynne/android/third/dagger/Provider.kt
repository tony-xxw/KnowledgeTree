package com.wynne.android.third.dagger

import com.wynne.knowledge.base.base.BaseActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

class Baozi {
    var name: String

    @Inject
    constructor() {
        name = "小笼包"
    }

    constructor(name: String) {
        this.name = name
    }

    override fun toString(): String {
        return name
    }

}

open class Noodle1 constructor() {
    override fun toString(): String = "面条"
}

class Tongyi @Inject constructor() : Noodle1() {
    override fun toString(): String = "统一面条"
}

class Kongshifu @Inject constructor() : Noodle1() {
    override fun toString(): String = "康师傅面条"
}

class Zhainan1 @Inject constructor() {
    @JvmField
    @Inject
    var noodle: Noodle1? = null

    @JvmField
    @Inject
    var bread: Baozi? = null

    @JvmField
    @Inject
    var shopName: String? = null

    fun eat(): String {
        var content = ""
        shopName?.let {
            content += "店铺名是 $it"
        }
        bread.let {
            content += it.toString()
        }

        noodle?.let {
            content += it.toString()
        }
        return content
    }
}

@Module
class ShenzhenModule(var shopName: String) {

    //    @Provides
//    fun providerNoodle(): Noodle1 = Noodle1()

    @Provides
    fun providerNoodle(tongyi: Tongyi): Noodle1 = tongyi

    @Provides
    fun providerShopName(): String = shopName

    @Provides
    fun providerBaozi(): Baozi = Baozi("豆沙包")

//    @Provides
//    fun providerNoodle(kangshifu: Kongshifu): Noodle1 = kangshifu
}

@Module
class ActivityModule {

    @Provides
    fun providerIntValue(): Int = 4999

    @Provides
    @Named("phone")
    fun obtainPhone(): String = "手机"

    @Provides
    @Named("number")
    fun obtainNumber(): String = "数字"
}

@Component(modules = [ShenzhenModule::class, ActivityModule::class])
interface ShopPlatform {
    fun waimai(): Zhainan1

    fun input(zhainan1: Zhainan1)

    fun inject(daggerActivity: DaggerActivity)
}

@Singleton
class TestSingleton @Inject constructor() {

}

@Singleton
@Component
interface ActivityComponent {

    fun inject(baseActivity: DaggerSecondActivity)
}