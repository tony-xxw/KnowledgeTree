package com.wynne.other.grammar


fun main() {
    println("第六章 Lambda ")
    chapter1()
    println()
    chapter2()
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
    println()
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
    val numbers = listOf(5, 2, 10, 4)


//    val reduce = students.reduce { acc, stu -> acc.score = acc.score + stu.score }
    println("sumBy $sumBy")
    println("fold $fold")
    println("reduce $reduce")

}

data class Stu(val name: String, val age: Int, val sex: String, val score: Int)


