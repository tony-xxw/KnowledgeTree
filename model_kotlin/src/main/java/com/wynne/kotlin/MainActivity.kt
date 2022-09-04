package com.wynne.kotlin

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.utils.LogUtil
import com.wynne.kotlin.coroutine.*
import com.wynne.kotlin.databinding.ActivtyMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class MainActivity : BaseActivity() {


    val binding: ActivtyMainBinding by lazy { ActivtyMainBinding.bind(root) }

    override fun initView() {

        binding.btnViewBind.setOnClickListener {
            LogUtil.d(javaClass.name, "ViewBinding!!!!!")
        }
    }

    override val layoutId: Int = R.layout.activty_main


    fun onClick(v: View) {
        when (v.id) {
            R.id.btnCoroutineBase -> {
                coroutineBase2()
            }
            R.id.btnCoroutineWeight -> {
//                baseCoroutineDispatcher()
//                baseCoroutineStart()
//                baseCoroutineScope()
//                baseCoroutineCoroutineScope()
//                baseCoroutineSupervisorScope()
                baseCoroutineWithSupervisorScope()
            }
            R.id.btnFlow -> {
                baseFlow()
            }
            R.id.btnFlowScheduler -> {
                flowScheduler()
            }
            R.id.btnFlowSign -> {
                flowSign()
            }
        }
    }

    /**
     * 禁止在flow代码块中进行线程调度
     */
    private fun baseFlow() {
        val job = lifecycleScope.launch {
            flow<Int> {
                for (i in 1..3) {
                    delay(100)
                    emit(i)
                }
            }.collect {
                LogUtil.d("flow: $it")
            }
        }
        //flow取消
        job.cancel()

        //asflow
        lifecycleScope.launch {
            (1..3).asFlow().collect { LogUtil.d("asFlow $it") }
        }
        //flowOf
        lifecycleScope.launch {
            flowOf<String>("1", "2", "3").collect {
                LogUtil.d("flowOf $it")
            }
        }
    }

    /**
     * flowOn可以将执行此流的上下文更改为指定上下文
     * flowOn可以组合使用
     * flowOn只影响前面没有自己上下文的操作符
     * 不管flowOn怎么切换,collect最终都会在当前协程调度器上执行
     */
    private fun flowScheduler() {
        lifecycleScope.launch() {
            LogUtil.d("thread-name :${Thread.currentThread().name}")
            flow<Int> {
                LogUtil.d("Flow thread-name :${Thread.currentThread().name}")
                for (i in 1..10) {
                    delay(100)
                    emit(i)
                }
            }
                .flowOn(Dispatchers.IO)
                .map {
                    it * 2
                }
                .flowOn(Dispatchers.IO).collect {
                    LogUtil.d("value: $it  thread-name ${Thread.currentThread().name}")
                }
        }
    }

    private fun flowSign() {
        lifecycleScope.launch {
            flow<Int> {
                LogUtil.d("flow")
                emit(1)
            }.onStart {
                //流程操作符
                LogUtil.d("onStart")
            }.onEach {
                //流程操作符
                LogUtil.d("onEach $it")
                throw NullPointerException()
            }.catch {
                //异常操作符
                LogUtil.d("catch $this")
                emit(2)
            }.transform {
                emit(3)
                emit(4)
            }.map {
                it * 5
            }.filter {
                it < 4
            }.onCompletion { cause ->
                //流程操作符
                LogUtil.d("onCompletion catch $cause")
            }.take(2)
                .buffer()
                .collect { value ->
                    LogUtil.d("collect $value")
                }
        }

        val flow = (1..4).asFlow()
        val flow1 = flowOf(1, 2, 3)
        lifecycleScope.launch {

            flow.zip(flow1) { value1, value2 ->

                value1 * value2
            }.collect {
                LogUtil.d("value ---> $it")
            }
        }
    }


}