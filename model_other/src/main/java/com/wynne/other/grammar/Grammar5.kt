package com.wynne.other.grammar

import androidx.annotation.Nullable

fun main() {
    var str: String? = null;
    println(str?.length)
//    str = "11"
    val value = str ?: "1111"
    println("value $value")


    //类型判断
    val demo = when (val obj = Any()) {
        obj is String -> "1"
        obj !is String -> "2"
        else -> "3"
    }
    println(demo)

    val animal: Animal = Fish(1, 1)
    println("weight: ${animal.weight}   height :${animal.height}")

    //数组
    val array = intArrayOf()
    genericity()


}


class Kot {
    //语义转换
    val stud: Student? = getStud()

    fun dealStu() {
        if (stud != null) {
            print(stud.name)
        }
//        stud?.let { print(it.name) }
    }
}

open class Animal(val weight: Int, val height: Int) {

}

class Fish(weight: Int, height: Int) : Animal(weight, height) {
}

fun Nothings() {

}

fun getStud(): Student {
    return Student("11")
}

data class Student(val name: String)

fun genericity() {
    //5.4.2
    val arrayList = arrayListOf<String>()
    arrayList.add("one")
    println(arrayList.find("one"))

    //5.4.3
    val applePlate = FruitPlate<Apple>(Apple(100.0))
//    val noodles =FruitPlate<Noodles>(Noodles(100.0))


    cut(Watermelon(10.0))
//    cut(Banana(11.0))

    //5.5
    val appleList = arrayListOf<Apple>()
    println(appleList.javaClass)
    val appleArray = arrayOfNulls<Apple>(3)
//    val anyArray : Array<Any?> = appleArray;

    //5.6
    val array = arrayOf(1, 2, "2")
    val lists = mutableListOf<String>("1", "2")
    val objects: List<Any> = lists

    //协变 与逆变
    //1. 协变
    val production1: Production<Food> = FoodStore().also { it.produce() }
    val production2: Production<Food> = FastStore().also { it.produce() }
    val production3: Production<Food> = BurgerStore().also { it.produce() }

    val consumer: Consumer<Burger> = EveryBody()
    val consumer1: Consumer<Burger> = ModernPeople()
    val consumer2: Consumer<Burger> = American()


}

fun <T> ArrayList<T>.find(t: T): T? {
    val index = this.indexOf(t)
    return if (index >= 0) this[index] else null
}

open class Fruit(val weight: Double)

class Apple(weight: Double) : Fruit(weight)

class Banana(weight: Double) : Fruit(weight)

class FruitPlate<T : Fruit>(val t: T)

class Noodles(weight: Double)

interface Ground {}
class Watermelon(weight: Double) : Fruit(weight), Ground

fun <T> cut(t: T) where T : Fruit, T : Ground {
    println("You can cut me  ${t.weight}")
}


interface Production<out T> {
    fun produce(): T
}

interface Consumer<in T> {
    fun consume(item: T)
}

open class Food
open class FastFood : Food()

class Burger : FastFood()

class FoodStore : Production<Food> {
    override fun produce(): Food {
        println("FoodStore food ")
        return Food()
    }
}

class FastStore : Production<FastFood> {
    override fun produce(): FastFood {
        println("FastStore food")
        return FastFood()
    }
}

class BurgerStore : Production<Burger> {
    override fun produce(): Burger {
        println("Burger food")
        return Burger()
    }
}

class EveryBody : Consumer<Food> {
    override fun consume(item: Food) {
        println("Eat food")
    }

}

class ModernPeople : Consumer<FastFood> {
    override fun consume(item: FastFood) {
        println("Eat fast food")
    }

}

class American : Consumer<Burger> {
    override fun consume(item: Burger) {
        println("Eat burger")
    }

}