package com.wynne.kotlin.coroutine

import android.util.Log
import com.wynne.knowledge.base.utils.LogUtil
import kotlinx.coroutines.*

/**
 * 知识点
 * CoroutineDispatcher
 * CoroutineContext
 * CoroutineStart
 * CoroutineScope
 * suspend
 *
 * @author xiangxianwen
 * @date 2022-09-01
 */

/**
 * 协程调度器
 *
 * MainScope、lifecycleScope、viewModelScope。 Dispatchers.Main
 *
 *  Dispatchers.Main UI调度器
 *  Dispatchers.Default CPU密集型任务调度器,适合处理后台计算,JSON,数据计算等，默认调度器
 *  Dispatchers.IO  IO调度器，网络请求、数据库、文件读写
 *  Dispatchers.Unconfined 不要求协程执行在特定线程
 *
 *  withContext 切换调度器
 */
fun baseCoroutineDispatcher() {
    LogUtil.d("Thread Name ${Thread.currentThread().name}")
    GlobalScope.launch(Dispatchers.Unconfined) {
        LogUtil.d("Thread launch ${Thread.currentThread().name}")
        val result = withContext(Dispatchers.IO) {
            LogUtil.d("witchContext ${Thread.currentThread().name}")
            "请求结果"
        }
        LogUtil.d(result)
    }
}

/**
 * 协程上下文
 *
 * CoroutineContext包含了用户定义的不同element对象集合
 * Job,CoroutineDispatcher,CoroutineExceptionHandler,CoroutineInterceptor,CoroutineName
 * plus会覆盖相同类型的上下文
 */
fun baseCoroutineContext() {
    val coroutineContext = Job() + CoroutineName("第一个")
    LogUtil.d("coroutineContext $coroutineContext")
    val coroutineContext1 = coroutineContext + Dispatchers.Default + CoroutineName("第二个")
    LogUtil.d("coroutineContext1 $coroutineContext1")
    val coroutineContext2 = coroutineContext1 + Dispatchers.Main + CoroutineName("第三个")
    LogUtil.d("coroutineContext2 $coroutineContext2")
}

/**
 * 协程启动器
 * DEFAULT 默认启动模式, 会立即开始调度,在遇到挂起点之前可以被取消。因为是立即调度，而不是立即启动
 * LAZY 懒加载启动模式，只要在手动调用的时候才会调度
 * ATOMIC 一样是立即开始调度，但是与DEAFULT不同的是 它在遇到挂起点之前不能被取消，
 * 换句话就是说只有在执行第一个调度之后才会被取消
 * UNDISPATCHED 这个模式会在当前线程下执行,直到运行到第一个挂起点，与ATOMIC模式类似，
 * 但是UNDISPATCHED在执行完调度点之后取决本身调度器
 */
fun baseCoroutineStart() {
    val defaultJob = GlobalScope.launch() {
        LogUtil.d("defaultJob", "Coroutine.Default ---- Thread ${Thread.currentThread().name}")
    }
    defaultJob.cancel()


    val lazyJob = GlobalScope.launch(start = CoroutineStart.LAZY) {
        LogUtil.d("lazyJob", "CoroutineLazy ---- Thread ${Thread.currentThread().name}")
    }
    lazyJob.start()

    val atomicJob = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
        LogUtil.d("atomicJob", "atomic 挂起前 ---- Thread ${Thread.currentThread().name}")
        delay(1000)
        LogUtil.d("atomicJob", "atomic 挂起后 ---- Thread ${Thread.currentThread().name}")
    }
    atomicJob.cancel()

    val unDispatcherJob = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
        LogUtil.d("unDispatchJob", "unDispatched 挂起前 ---- Thread ${Thread.currentThread().name}")
        delay(1000)
        LogUtil.d("unDispatchJob", "unDispatched 挂起后 ---- Thread ${Thread.currentThread().name}")
    }
    unDispatcherJob.cancel()
}

/**
 * 协程作用域
 * 顶级作用域 --> 没有父协程的协程所在的作用域称之为顶级作用域。
 *
 * 协同作用域 --> 在协程中启动一个协程，新协程为所在协程的子协程。子协程所在的作用域默认为协同作用域。
 * 此时子协程抛出未捕获的异常时，会将异常传递给父协程处理，如果父协程被取消，则所有子协程同时也会被取消。
 *
 * 主从作用域(SupervisorJob,SupervisorScope) --> 官方称之为监督作用域。与协同作用域一致，区别在于该作用域下的协程取消操作的单向传播性，
 * 子协程的异常不会导致其它子协程取消。但是如果父协程被取消，则所有子协程同时也会被取消。
 *
 */
fun baseCoroutineScope() {
    GlobalScope.launch(Dispatchers.Main) {
        LogUtil.d("父协程上下文", "$coroutineContext")
        launch(CoroutineName("第一个子协程")) {
            LogUtil.d("第一个子协程上下文", "$coroutineContext")
        }
        launch(Dispatchers.Unconfined) {
            LogUtil.d("第二个子协程上下文", "$coroutineContext")
        }
    }

}

val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    LogUtil.d("exceptionHandler", "${coroutineContext[CoroutineName]} $throwable")
}

fun baseCoroutineCoroutineScope() {
    GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
        LogUtil.d("scope", "--------- 1")
        launch(CoroutineName("scope2") + exceptionHandler) {
            LogUtil.d("scope", "--------- 2")
            throw  NullPointerException("空指针")
            LogUtil.d("scope", "--------- 3")
        }
        val scope3 = launch(CoroutineName("scope3") + exceptionHandler) {
            LogUtil.d("scope", "--------- 4")
            delay(2000)
            LogUtil.d("scope", "--------- 5")
        }
        scope3.join()
        LogUtil.d("scope", "--------- 6")
    }
}

fun baseCoroutineSupervisorScope() {
    GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
        supervisorScope {
            LogUtil.d("scope", "--------- 1")
            launch(CoroutineName("scope2")) {
                LogUtil.d("scope", "--------- 2")
                throw  NullPointerException("空指针")
                LogUtil.d("scope", "--------- 3")
                val scope3 = launch(CoroutineName("scope3")) {
                    LogUtil.d("scope", "--------- 4")
                    delay(2000)
                    LogUtil.d("scope", "--------- 5")
                }
                scope3.join()
            }
            val scope4 = launch(CoroutineName("scope4")) {
                LogUtil.d("scope", "--------- 6")
                delay(2000)
                LogUtil.d("scope", "--------- 7")
            }
            scope4.join()
            LogUtil.d("scope", "--------- 8")
        }
    }
}

fun baseCoroutineWithSupervisorScope(){
    val coroutineScope = CoroutineScope(SupervisorJob() +CoroutineName("coroutineScope"))
    GlobalScope.launch(Dispatchers.Main + CoroutineName("scope1") + exceptionHandler) {
        with(coroutineScope){
            val scope2 = launch(CoroutineName("scope2") + exceptionHandler) {
                LogUtil.d("scope", "1--------- ${coroutineContext[CoroutineName]}")
                throw  NullPointerException("空指针")
            }
            val scope3 = launch(CoroutineName("scope3") + exceptionHandler) {
                scope2.join()
                LogUtil.d("scope", "2--------- ${coroutineContext[CoroutineName]}")
                delay(2000)
                LogUtil.d("scope", "3--------- ${coroutineContext[CoroutineName]}")
            }
            scope2.join()
            LogUtil.d("scope", "4--------- ${coroutineContext[CoroutineName]}")
            coroutineScope.cancel()
            scope3.join()
            LogUtil.d("scope", "5--------- ${coroutineContext[CoroutineName]}")
        }
        LogUtil.d("scope", "6--------- ${coroutineContext[CoroutineName]}")
    }
}

/**
 * 挂起函数
 */
fun baseSuspend() {

    GlobalScope.launch {

    }
}