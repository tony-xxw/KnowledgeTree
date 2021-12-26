package com.wynne.kotlin

import kotlinx.coroutines.*
import java.lang.Exception

fun stepBase() {

    GlobalScope.launch(context = Dispatchers.IO) {
        obtainHello()
        delay(1000)
        currentThread("")
    }


    Thread.sleep(3000)
    currentThread("")
}

fun stepRunBlockingLaunch() {
//    runBlockingFirst()
    runBlockingSecond()
}

fun runBlockingSecond() {
    GlobalScope.launch {
        delay(3000)
        currentThread("global")
    }

    runBlocking {
        delay(1000)
        currentThread(("runblocking"))
    }

    Thread.sleep(2000)
    currentThread("thread ")
}

fun runBlockingFirst() {
    runBlocking {
        launch {
            repeat(3) {
                currentThread("launch A $it")
            }
        }

        launch {
            repeat(3) {
                currentThread("launch B $it")
            }
        }

        GlobalScope.launch {
            repeat(3) {
                currentThread("coroutine C $it")
            }
        }
    }

    Thread.sleep(2000)
    currentThread("main")
}

fun stepCoroutineScope() {
    runBlocking {
        launch {
            delay(100)
            println("task from run runBlocking")
        }

        coroutineScope {
            launch {
                delay(500)
                println("task from nested launch")
            }

            delay(50)
            println("task from coroutine scope")
        }
        println("coroutine scope is over")
    }
}


/**
 * supervisor 可以在协程中抛出异常 但是不中断同级协程 和 父级协程
 */
fun stepSupervisorScope() {
    runBlocking {
        launch {
            delay(100)
            println("task from runblocking")
        }

        supervisorScope {
            launch {
                delay(500)
                println("task throw exception")
                throw Exception("failed")
            }
            launch {
                delay(600)
                println("task from nested launch")
            }
        }
        println("coroutine scope is over")
    }
}

fun stepCustomCoroutineScope() {
    runBlocking {
        //test
        val activity = Activity1()
        activity.onCreate()
        delay(1000)
        activity.onDestroy()
        delay(1000)
    }

}


class Activity {
    private val mainScope = MainScope()

    fun onCreate() {
        mainScope.launch {
            repeat(5) {
                delay(1000L * it)
            }
        }
        println("onCreated")
    }

    fun onDestroy() {
        mainScope.cancel()
        println("onDestroy")
    }
}

class Activity1 : CoroutineScope by CoroutineScope((Dispatchers.Default)) {
    fun onCreate() {
        launch {
            repeat(5) {
                delay(1000L * it)
                println(it)
            }
        }
        println("onCreated")
    }

    fun onDestroy() {
        cancel()
        println("onDestroy")
    }
}

suspend fun obtainHello() {
    delay(2000)
    println("coroutiner")
}


fun currentThread(any: Any) {
    println("$any ----------- the current thread name ${Thread.currentThread().name}")
}
