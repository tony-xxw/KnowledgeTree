package com.wynne.design.duty

import android.util.Log
import java.lang.RuntimeException
import java.math.BigDecimal
import java.util.function.BiConsumer


data class Request(val name: String, val amount: BigDecimal)


interface Handler {
    fun process(request: Request): Boolean
}


class ManagerHandler : Handler {
    override fun process(request: Request): Boolean {
        if (request.amount < BigDecimal(100)) {
            return false
        }
        return request.name.equals("bob", true)
    }
}

class DirectorHandler : Handler {
    override fun process(request: Request): Boolean {
        if (request.amount < BigDecimal(1000)) {
            return false
        }
        return request.name.equals("alice", true)
    }
}

class CEOHandler : Handler {
    override fun process(request: Request): Boolean {
        if (request.amount < BigDecimal(10000)) {
            return false
        }
        return request.name.equals("bill", true)

    }
}

class HandlerChain {
    private val handlers = mutableListOf<Handler>()

    fun addHandler(handler: Handler) {
        this.handlers.add(handler)
    }

    fun process(request: Request): Boolean {
        handlers.map { handle ->
            val r = handle.process(request)
            if (r) {
                println(request.toString() + " " + (if (r) "Approved by " else " Denined by") + handle.javaClass.simpleName)
                return r
            }
        }
        throw  RuntimeException("could not handle request: $request")
    }

}

fun main() {
    val handlerChain = HandlerChain()
    handlerChain.addHandler(ManagerHandler())
    handlerChain.addHandler(DirectorHandler())
    handlerChain.addHandler(CEOHandler())

    handlerChain.process(Request("Bob", BigDecimal("123.45")))
    handlerChain.process(Request("Alice", BigDecimal("1234.45")))
    handlerChain.process(Request("Bill", BigDecimal("12345.45")))


    var test = Test()
    val value  = test.getAge().plus(1)
    println("${test.getAge()} - $value")
}


class Test() {
    private var age: Int = 0
    var weight: Int = 0

    init {
        age = 25
        weight = 140
    }

    fun getAge(): Int {
        return age
    }

}