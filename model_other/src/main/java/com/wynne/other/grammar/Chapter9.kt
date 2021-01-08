package com.wynne.myapplication

import java.lang.IllegalArgumentException
import java.util.*
import kotlin.properties.Delegates

fun main() {
    chapterFactory()
    chapterBuild()
    chapterObserver()
    chapterStrategy()
    chapterTemplate()
}

fun chapterTemplate() {
    //模板模式
    PullSocialSecurity().execute()
    println()
    PullSocialSecurity().execute(::pullSocialSecurity)
}

abstract class CivicCenterTask {
    fun execute() {
        this.lineup()
        this.askForHelp()
        this.evaluate()
    }

    fun execute(askForHelp: () -> Unit) {
        this.lineup()
        askForHelp()
        this.evaluate()
    }

    private fun lineup() {
        println("line up to take a number")
    }

    private fun evaluate() {
        println("evaluaten service attitude")
    }

    abstract fun askForHelp()
}

fun pullSocialSecurity(){
    println("ask for pulling ")
}

class PullSocialSecurity : CivicCenterTask() {
    override fun askForHelp() {
        println("ask for pull the scocial security")
    }
}

class ApplyForCitizenCard : CivicCenterTask() {
    override fun askForHelp() {
        println("applay for a citizen card")
    }
}

fun chapterStrategy() {
    //策略模式
    //java
    val weekendShaw = Swimmer(Freestyle())
    weekendShaw.swim()
    val weekDayShaw = Swimmer(Breaststroke())
    weekDayShaw.swim()
    //kotlin
    Swimmer1(::backstroke).swim()

}

interface SwimStrategy {
    fun swim()
}

class Breaststroke : SwimStrategy {
    override fun swim() {
        println("i am breaststrokeing...")
    }
}

class Backstroke : SwimStrategy {
    override fun swim() {
        println("i am backstroke...")
    }
}

class Freestyle : SwimStrategy {
    override fun swim() {
        println("i am freestyle...")
    }
}

class Swimmer(val strategy: SwimStrategy) {
    fun swim() {
        strategy.swim()
    }
}

fun breaststroke() {
    println("i am breaststrokeing...")
}

fun freestyle() {
    println("i am freestyle...")
}

fun backstroke() {
    println("i am backstroke...")
}

class Swimmer1(val strategy: () -> Unit) {
    fun swim() {
        strategy()
    }
}


fun chapterObserver() {
    println("观察者")
    val su = StockUpdate()
    val sd = StockDisplay()
    su.observers.add(sd)
    su.setStockChanged(100)
    println()
    val su1 = StockUpdate1()
    val sd1 = StockDisplay1()
    su1.listeners.add(sd1)
    su1.price = 100
    su1.price = 98
    su1.value = 1
    println(su1.value)
    su1.value = -1
    println(su1.value)
}

interface StockUpdateListener {
    fun onRise(price: Int)
    fun onFall(price: Int)
}

class StockDisplay1 : StockUpdateListener {
    override fun onRise(price: Int) {
        println("the latest stock price has risen to $price")
    }

    override fun onFall(price: Int) {
        println("the latest stock price has fell to $price")
    }
}

class StockUpdate1 {
    val listeners = mutableSetOf<StockUpdateListener>()

    var price: Int by Delegates.observable(0) { _, old, new ->
        listeners.forEach {
            if (new > old) it.onRise(price) else it.onFall(price)
        }

    }

    var value: Int by Delegates.vetoable(0) { _, oldValue, newValue ->
        println("oldValue $oldValue    newValue $newValue")
        newValue > 0
    }
}


class StockUpdate : Observable() {
    val observers = mutableSetOf<Observer>()

    fun setStockChanged(price: Int) {
        this.observers.forEach { it.update(this, price) }
    }
}

class StockDisplay : Observer {
    override fun update(o: Observable, price: Any) {
        if (o is StockUpdate) {
            println("lasted stock price is $price")
        }
    }

}

fun chapterBuild() {
    println("构建模式")
    //Java写法
    val rebot = Rebot.Builder("007").setBattery("R6").setHeight(100).setWeight(80).build()
    println(rebot.toString())
    //具体参数化
    Rebot1("007")
}

class Rebot1(
        val code: String,
        val battery: String? = null,
        height: Int? = null,
        weight: Int? = null
) {
    init {
        require(weight == null || battery != null) {
            "not null"
        }
    }
}

class Rebot private constructor(
        val code: String,
        val battery: String?,
        val height: Int?,
        var weight: Int?
) {

    class Builder(val code: String) {
        private var battery: String? = null
        private var height: Int? = null
        private var weight: Int? = null

        fun setBattery(battery: String?): Builder {
            this.battery = battery
            return this
        }

        fun setHeight(height: Int?): Builder {
            this.height = height
            return this
        }

        fun setWeight(weight: Int?): Builder {
            this.weight = weight
            return this
        }

        fun build(): Rebot {
            if (weight != null && battery == null) {
                throw  IllegalArgumentException("not null")
            }
            return Rebot(code, battery, height, weight)
        }
    }

    override fun toString(): String {
        return "code $code battery $battery weight $weight height $height"
    }
}

fun chapterFactory() {
    println("工厂")
    //普通工厂
    BaseComputerFactory().produce(ComputerType.PC).cpu.let { println(it) }
    //单例工厂
    println(ObjcetComputerFactory(ComputerType.Server).cpu)
    //伴生对象静态工厂
    Computer(ComputerType.Server).cpu.let { println(it) }
    //扩展伴生对象工厂
    Computer.cpuFrom("Core").let { println(it) }
    //抽象工厂
    println(AbstractFactory(XiaoMiFactory()).produce())
    println(AbstractFactory<Apple>().produce())

}

interface Computer {
    val cpu: String

    companion object Factory {
        operator fun invoke(type: ComputerType): Computer = when (type) {
            ComputerType.PC -> Pc()
            ComputerType.Server -> Service()
        }
    }
}

interface Computer1
class Apple : Computer1
class XiaoMi : Computer1
class HuaiWei : Computer1

class AppleFactory : AbstractFactory() {
    override fun produce() = Apple()
}

class XiaoMiFactory : AbstractFactory() {
    override fun produce() = XiaoMi()
}

class HuaiWeiFactory : AbstractFactory() {
    override fun produce() = HuaiWei()
}

abstract class AbstractFactory {
    abstract fun produce(): Computer1

    companion object {
        operator fun invoke(factory: AbstractFactory): AbstractFactory {
            return factory
        }

        inline operator fun <reified T : Computer1> invoke(): AbstractFactory = when (T::class) {
            Apple::class -> AppleFactory()
            XiaoMi::class -> XiaoMiFactory()
            HuaiWei::class -> HuaiWeiFactory()
            else -> throw IllegalArgumentException()
        }
    }
}

fun Computer.Factory.cpuFrom(cpu: String): ComputerType? = when (cpu) {
    "Core" -> ComputerType.PC
    "Xeon" -> ComputerType.Server
    else -> null
}


class Pc(override val cpu: String = "Core") : Computer
class Service(override val cpu: String = "Xeon") : Computer

enum class ComputerType {
    PC, Server
}


class BaseComputerFactory {
    fun produce(type: ComputerType): Computer = when (type) {
        ComputerType.PC -> Pc()
        ComputerType.Server -> Service()
    }
}

object ObjcetComputerFactory {

    operator fun invoke(type: ComputerType): Computer = when (type) {
        ComputerType.PC -> Pc()
        ComputerType.Server -> Service()
    }
}



