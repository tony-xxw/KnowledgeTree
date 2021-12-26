package com.wynne.kotlin

import kotlinx.coroutines.*
import kotlin.math.log
import kotlin.system.measureTimeMillis


fun launchBase() {
    runBlocking {
        launch {
            repeat(5) {
                delay(100)
                println("launch A")
            }
        }

        launch {
            repeat(5) {
                delay(100)
                println("launch B")
            }
        }
    }
}

fun launchStatusJob() {
    val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
        for (i in 0..100) {
            delay(100)
        }
    }

    //isActive job 未被取消或没有失效,则处于active状态为true
    "1. job.isActive ${job.isActive}".log()
    //isCancelled job 主动取消或者异常结束 为true
    "1. job isCancelled ${job.isCancelled}".log()
    //isCompleted job 正常结束或者异常结束 为true
    "1. job isCompleted ${job.isCompleted}".log()

    //启动job 如果此时状态为 active 为false 则返回true, 如果状态为 started or completed ,则返回false
    job.start().toString().log()

    "2. job.isActive ${job.isActive}".log()
    "2. job isCancelled ${job.isCancelled}".log()
    "2. job isCompleted ${job.isCompleted}".log()

    Thread.sleep(400)
    //手动取消
    job.cancel(CancellationException("test"))

    Thread.sleep(400)

    "3. job.isActive ${job.isActive}".log()
    "3. job isCancelled ${job.isCancelled}".log()
    "3. job isCompleted ${job.isCompleted}".log()

    //当Job 结束时运行回调此方法,可用于接受可能存在的运行异常
    job.invokeOnCompletion {
        "invokeOnCompletion $it".log()
    }
}

fun launchAsyncOrAwait() {
    val measureTimeMillis = measureTimeMillis {
        runBlocking {
            val async = async {
                delay(3000)
                1
            }

            val async1 = async {
                delay(4000)
                2
            }
//            (async.await()).toString().log()
//            "合并执行".log()
//            (async1.await()).toString().log()
            val listOf = listOf(async, async1)
            listOf.awaitAll()
        }
    }
    measureTimeMillis.toString().log()
}