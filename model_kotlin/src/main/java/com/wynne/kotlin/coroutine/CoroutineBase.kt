package com.wynne.kotlin

import com.wynne.knowledge.base.utils.LogUtil
import kotlinx.coroutines.*


/**
 * runBlocking 阻塞式
 * async,launch 非阻塞式
 * launch会返回一个job
 * async通过await方法可以获取最后一行数据
 */
fun coroutineBase() {
    val blocking = runBlocking {
        LogUtil.d("runBlocking start coroutine")
        "runBlocking test"
    }
    LogUtil.d("blocking: $blocking")

    val job = GlobalScope.launch {
        LogUtil.d("launch start coroutine")
    }
    LogUtil.d("launch: $job")

    val deferred = GlobalScope.async {
        LogUtil.d("async start coroutine")
        "async test"
    }
    LogUtil.d("async: $deferred")
}

/**
 * 挂起函数必须在携程作用域执行
 */
fun coroutineBase1() {
    GlobalScope.launch {
        val job = launch {
            LogUtil.d("launch start coroutine")
        }
        LogUtil.d("launch: $job")

        val deferred = async {
            LogUtil.d("async start coroutine")
            "async test"
        }
        LogUtil.d("async await: ${deferred.await()}")
        LogUtil.d("async: $deferred")
    }
}

/**
 * 在Android中如果调度器是Main，则会同步执行，其他则不会
 */
fun coroutineBase2() {
    //Dispatchers.Main 则同步执行
    GlobalScope.launch(Dispatchers.Default) {
        for (index in 1 until 10) {
            launch {
                LogUtil.d("index: $index 启动一个协程")
            }
        }
    }
}



