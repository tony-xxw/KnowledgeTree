package com.wynne.other.grammar


fun main() {
    println("第六章 Lambda ")
    chapter1()
    println()
    chapter2()
    println()
    chapter3()
    println()
    chapter4()
    println()
    chapter5()
    getType<Int>()

}


fun chapter1() {
    println("6.1")
    //待接收者的Lambda

    val sum: Int.(Int) -> Int = { other -> plus(other) }
    println("sum ${1.sum(2)}")
//    html {body() }
    val html = html { test(it) }

    with(html) {
        println("with width ${this.width} height ${this.height}")
    }

    html.apply {
        println("apply width ${this.width} height ${this.height}")
    }
}

class HTML {
    var width: Int = 0
    var height: Int = 0
    fun body() {
        println("html")
    }

    fun test(test: Int) {
        println("test $test")
    }
}

fun html(init: HTML.(Int) -> Unit): HTML {
    val html = HTML()
    html.init(1)
    return html
}

data class Stu(val name: String, val age: Int, val sex: String, val score: Int)

fun chapter2() {
    println("6.2")
    val list = arrayOf(1, 2, 3, 4)
    val newList = list.map { it * 2 }
    val zhangsan = Stu("zhangsan", 30, "m", 85)
    val lisi = Stu("lisi", 19, "f", 45)
    val shaw = Stu("shaw", 24, "m", 41)
    val jack = Stu("jack", 13, "f", 67)
    val lisa = Stu("lisa", 49, "f", 91)
    val pan = Stu("pan", 34, "m", 23)
    println("filter, count")
    val students = listOf<Stu>(zhangsan, lisi, shaw, jack, lisa, pan)
    val filterStu = students.filter { it.sex == "m" }
    val filterNoStu = students.filterNot { it.sex == "m" }
    val filterNoNull = students.filterNotNull()
    val count = students.count { it.score > 60 }
    println("origin  $students")
    println("filter  $filterStu")
    println("filterNoStu  $filterNoStu")
    println("count  $count")

    println("sumBy、fold、reduce")
    val sumBy = students.sumBy { it.score } / students.size
    val fold = students.fold(0) { accumulator, student -> accumulator + student.score }
    val reduceOrigin = arrayOf(1, 2, 3, 4, 5)
    val reduce = reduceOrigin.reduce { init, index -> init + index }
    println("sumBy $sumBy")
    println("fold $fold")
    println("reduce $reduce")
    println("groupBy")
    val groupBy = students.groupBy { it.sex }
    println("groupBy  $groupBy")

    //fla
    val flattenTestList = arrayOf(arrayOf(lisa, lisa), arrayOf(pan, shaw, jack))
    println("flattenTestList  $flattenTestList")
    val flatten = flattenTestList.flatten()
    println("flatten $flatten")
    val flatMap = flattenTestList.flatMap { it.map { stu -> stu.name } }
    println("flatMap $flatMap")


}


fun chapter3() {
    println("6.3")
    println("6.3.1")
    val list = listOf(1, 2, 3, 4, 5)
    println("list $list")
    val set = setOf(1, 1, 2, 2, 3, 3, 4, 4, 5, 5)
    println("set $set")
    val map = mapOf(1 to 1, 2 to 2, 2 to 3, 3 to 3)
    println("map $map")
    println("6.3.2")
    val mutableListOf = mutableListOf(1, 2, 3, 4)
    mutableListOf[0] = -1
    println("mutable $mutableListOf")
    var readList = listOf(1, 2, 3, 4)
//    readList[0] = 1 false 不可变
    //改变只读属性
    readList = mutableListOf
    println("readList $readList")
    mutableListOf[0] = 10
    println("readList $readList")

}

fun chapter4() {
    println("6.4")
    println("6.4.1")
    val list = listOf(1, 2, 3)
    list.filter {
        println("filter $it")
        it > 1
    }.map {
        println("map $it")
        it * 2
    }
    println("6.4.2")
    //当数据量很大 会有性能问题, 因为会先走filter，在走map,而且会产生一个临时对象
    //利用序列提高效率, asSequence不会产生临时对象
    // asSequence 是惰性求职, 会先有一个中间操作,在执行末端操作
    list.asSequence().filter {
        println("asSequence filter $it")
        it > 1
    }.map {
        println("asSequence map $it")
        it * 2
    }.toList()
    println("6.4.3")
    var naturalNumList = generateSequence(0) { it + 1 }
    println("naturalNumList ${naturalNumList.takeWhile { it <= 9 }.toList()}")
}


fun chapter5() {
    println("6.5")
    // 加入关键字Inline 减少开销
    // 没有加入 Inline 关键字,编译为字节码后 反编译会通过反射来调用 增加开支
    // 加入后 会直接copy过来,减少开支
    // 使用场景: 以lambda表达式为参数的函数使用,减少开支
    foo {
        println("kotlin")
    }

    foo({
        println("kotlin")
    }, {
        println("kotlin1")
    })

    localFoo {
        return
    }


}

inline fun foo(block: () -> Unit) {
    println("before lock")
    block()
    println("after lock")
}

inline fun foo(block: () -> Unit, noinline block1: () -> Unit) {
    println("before lock")
    block()
    block1()
    println("after lock")
}

inline fun localFoo(block: () -> Unit) {
    println("localFoo before lock")
    block()
    println("localFoo after lock")
}

inline fun <reified T> getType() {
    println(T::class)
}