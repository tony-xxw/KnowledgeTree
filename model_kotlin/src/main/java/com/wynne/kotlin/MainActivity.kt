package com.wynne.kotlin

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.utils.LogUtil
import com.wynne.kotlin.coroutine.*
import com.wynne.kotlin.databinding.ActivtyMainBinding
import kotlinx.android.synthetic.main.activty_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
            R.id.btnFlowState -> {
                flowStateSample()
            }
            R.id.btnFlowShare -> {
                flowShareSample()
            }
            R.id.btnCoroutineApplication -> {
                coroutineApplication()
            }
        }
    }


    private fun coroutineApplication() {
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
            LogUtil.d("throwable $throwable")
        }
        lifecycleScope.launch(handler) {
            launch {
                throw  NullPointerException("空指针")
            }
            val result = withContext(Dispatchers.IO) {
                //网络请求...
                "请求结果"
            }
            launch {
                //网络请求3...
            }
            btnCoroutineApplication.text = result
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

    val viewModel by lazy { FlowStateModel() }


    private fun flowStateSample() {
        lifecycleScope.launch {
            try {
                viewModel.state.collect {
                    LogUtil.d("carman", "state : $it")
                    if (it == 3) {
                        throw NullPointerException("终止第一个StateFlow的数据收集")
                    }
                }
            } catch (e: Exception) {
                LogUtil.d("carman", "e : $e")
            }
            viewModel.name.collect {
                LogUtil.d("carman", "name : $it")
            }
        }
        viewModel.download()

    }

    val shareModel by lazy { FlowShareModel() }

    private fun flowShareSample() {
        lifecycleScope.launch {
            launch {
                shareModel.share.collect {
                    LogUtil.d(
                        "carman",
                        "第一个state : $it   replayCache: ${viewModel.state.replayCache}"
                    )
                }
            }
            launch {
                delay(2000)
                shareModel.share.collect {
                    LogUtil.d("carman", "第二个state : $it")
                }
            }

        }
        shareModel.download()
    }


    class FlowStateModel : ViewModel() {
        private val _state: MutableStateFlow<Int> = MutableStateFlow(0)
        val state: StateFlow<Int> get() = _state

        private val _name: MutableStateFlow<String> = MutableStateFlow("第二个StateFlow")
        val name: StateFlow<String> get() = _name
        fun download() {
            for (state in 0..5) {
                viewModelScope.launch(Dispatchers.IO) {
                    delay(200L * state)
                    _state.value = state
                }
            }
        }

    }

    class FlowShareModel : ViewModel() {
        private val _share: MutableSharedFlow<Int> = MutableSharedFlow(2)
        val share: SharedFlow<Int> get() = _share

        fun download() {
            for (state in 0..5) {
                viewModelScope.launch(Dispatchers.IO) {
                    delay(200L * state)
                    _share.emit(state)
                }
            }
        }

    }
}