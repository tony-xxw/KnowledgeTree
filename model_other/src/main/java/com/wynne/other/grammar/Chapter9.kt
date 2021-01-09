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
    chapterIterator()
    chapterChain()
    chapterState()
    chapterDecorate()
}

fun chapterDecorate() {
    println("装饰模式")
    val macBook = MacBookPro()
    val process = ProcessUpgradeMacbookPro(macBook)
    println(process.getCost())
    println(process.getDesc())

    Printer().run {
        startDraw {
            drawLine()
        }
        startDraw {
            drawDottedLine()
        }
        startDraw {
            drawStars()
        }
    }
}

class Printer {
    fun drawLine() {
        println("______")
    }

    fun drawDottedLine() {
        println("-----")
    }

    fun drawStars() {
        println("*******")
    }
}

fun Printer.startDraw(decor: Printer.() -> Unit) {
    println("++start")
    this.decor()
    println("== end")
}

interface MacBook {
    fun getCost(): Int
    fun getDesc(): String
    fun getProDaTE(): String
}

class MacBookPro : MacBook {
    override fun getCost(): Int = 1000

    override fun getDesc(): String = "Macbook Pro"

    override fun getProDaTE(): String = "Late 2011"
}

class ProcessUpgradeMacbookPro(val macbook: MacBook) : MacBook by macbook {
    override fun getCost(): Int {
        return macbook.getCost() + 219
    }

    override fun getDesc(): String {
        return macbook.getDesc() + ", 1G Memory"
    }

}

fun chapterState() {
    println("状态模式")
    val waterMachine = WaterMachine()
    waterMachineOps(waterMachine, Moment.DRINKING_WARTER)
    waterMachineOps(waterMachine, Moment.INSTANCE_NOODLES)
    waterMachineOps(waterMachine, Moment.DRINKING_WARTER)
    waterMachineOps(waterMachine, Moment.AFTER_WORK)
}

sealed class WaterMachinesState(open val machine: WaterMachine) {
    fun turnHeating() {
        if (this !is Heating) {
            println("turn heating")
            machine.state = machine.heating
        } else {
            println("the state is already headting mode")
        }
    }

    fun turnCooling() {
        if (this !is Cooling) {
            println("turn Cooling")
            machine.state = machine.cooling
        } else {
            println("the state is already Cooling mode")
        }
    }

    fun turnOff() {
        if (this !is Off) {
            println("turn Off")
            machine.state = machine.off
        } else {
            println("the state is already Off mode")
        }
    }

}

enum class Moment {
    EARLY_MORNING,
    DRINKING_WARTER,
    INSTANCE_NOODLES,
    AFTER_WORK
}

fun waterMachineOps(machine: WaterMachine, moment: Moment) {
    when (moment) {
        Moment.EARLY_MORNING,
        Moment.DRINKING_WARTER -> when (machine.state) {
            !is Cooling -> machine.turnCooling()
        }
        Moment.INSTANCE_NOODLES -> when (machine.state) {
            !is Heating -> machine.turnHeating()
        }
        Moment.AFTER_WORK -> when (machine.state) {
            !is Off -> machine.turnOff()
        }
        else -> Unit
    }
}

class Off(override val machine: WaterMachine) : WaterMachinesState(machine)
class Heating(override val machine: WaterMachine) : WaterMachinesState(machine)
class Cooling(override val machine: WaterMachine) : WaterMachinesState(machine)

class WaterMachine {
    var state: WaterMachinesState
    val off = Off(this)
    val heating = Heating(this)
    val cooling = Cooling(this)

    init {
        this.state = off
    }

    fun turnHeating() {
        this.state.turnHeating()
    }

    fun turnCooling() {
        this.state.turnCooling()
    }

    fun turnOff() {
        this.state.turnOff()
    }
}

fun chapterChain() {
    println("责任链模式")
    val groupLeader = GroupLeader(Preside(College(null)))
    groupLeader.handleEvent(ApplyEvent(10, "buy a pen"))
    groupLeader.handleEvent(ApplyEvent(200, "team building"))
    groupLeader.handleEvent(ApplyEvent(600, "hold a debate match"))
    groupLeader.handleEvent(ApplyEvent(1200, "annual meeting of the college"))

    //Kotlin 偏函数实现
    val groupLeader1 = {
        val definerAt: (ApplyEvent) -> Boolean = { it.money <= 200 }
        val handler: (ApplyEvent) -> Unit =
                { println("Group Leader handled application: ${it.title}") }
        PartialFunction(definerAt, handler)
    }()

    val president = {
        val definerAt: (ApplyEvent) -> Boolean = { it.money <= 500 }
        val handler: (ApplyEvent) -> Unit =
                { println("President  handled application: ${it.title}") }
        PartialFunction(definerAt, handler)
    }()

    val college = {
        val definerAt: (ApplyEvent) -> Boolean = { true }
        val handler: (ApplyEvent) -> Unit = {
            when {
                it.money > 1000 -> println("College: This application is refused")
                else -> println("College  handled application: ${it.title}")
            }
        }
        PartialFunction(definerAt, handler)
    }()

    val applyChain = groupLeader1 orElse president orElse college
    applyChain(ApplyEvent(600, "hold a debate match"))
}

class PartialFunction<in P1, out R>(
        private val definerAt: (P1) -> Boolean,
        private val f: (P1) -> R
) : (P1) -> R {
    override fun invoke(p1: P1): R {
        if (definerAt(p1)) {
            return f(p1)
        } else {
            throw IllegalArgumentException("Value $p1 isn't supported by this.function")
        }
    }

    fun isDefinedAt(p1: P1) = definerAt(p1)
}

infix fun <P1, R> PartialFunction<P1, R>.orElse(that: PartialFunction<P1, R>): PartialFunction<P1, R> {
    return PartialFunction({ this.isDefinedAt(it) || that.isDefinedAt(it) }) {
        when {
            this.isDefinedAt(it) -> this(it)
            else -> that(it)
        }
    }
}

data class ApplyEvent(val money: Int, val title: String)
interface ApplyHandler {
    val success: ApplyHandler?
    fun handleEvent(event: ApplyEvent)
}

class GroupLeader(override val success: ApplyHandler?) : ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 100 -> println("Group leader handle application :${event.title}")
            else -> when (success) {
                is ApplyHandler -> success.handleEvent(event)
                else -> println("Group leader: this application cannot be handle")
            }
        }
    }
}

class Preside(override val success: ApplyHandler?) : ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 500 -> println("Preside leader handle application :${event.title}")
            else -> when (success) {
                is ApplyHandler -> success.handleEvent(event)
                else -> println("Preside leader: this application cannot be handle")
            }
        }
    }
}


class College(override val success: ApplyHandler?) : ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 1000 -> println("College leader handle application :${event.title}")
            else -> when (success) {
                is ApplyHandler -> success.handleEvent(event)
                else -> println("College leader: this application cannot be handle")
            }
        }
    }
}

fun chapterIterator() {
    println("迭代器模式")
    //迭代器模式
    val bookcase = Bookcase(listOf(Book("Dive into Kotlin"), Book("Thinking in Java")))

//    while (bookcase.hasNext()) {
//        println("The Book Name is ${bookcase.next().name}")
//    }
    for (book in bookcase) {
        println("The book name is ${book.name}")
    }
    println()
    //重载
    for (book in BookCase1(listOf(Book("Dive into Kotlin"), Book("Thinking in Java"))).iterator()) {
        println("The book name is ${book.name}")
    }


}


data class Book(val name: String)
class BookCase1(val books: List<Book>) {
    operator fun iterator(): Iterator<Book> = this.books.iterator()
}

class BookCase2(val books: List<Book>)

operator fun BookCase1.iterator(): Iterator<Book> = books.iterator()
operator fun BookCase2.iterator(): Iterator<Book> = object : Iterator<Book> {
    val iterator = books.iterator()
    override fun hasNext(): Boolean = iterator.hasNext()
    override fun next() = iterator.next()
}

class Bookcase(books: List<Book>) : Iterator<Book> {
    private val iterators: Iterator<Book> = books.iterator()

    override fun hasNext(): Boolean = iterators.hasNext()

    override fun next(): Book = iterators.next()

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

fun pullSocialSecurity() {
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
