package com.wynne.thread

import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import java.util.*
import java.util.concurrent.*


class ExecutorsActivity : BaseActivity() {
    override fun initView() {

    }

    override val layoutId: Int = R.layout.activity_thread_executors_layout


    fun onClick(view: View) {
        when (view.id) {
            R.id.fixed -> {
                createFixed()
            }
            R.id.cache -> {
                createCache()
            }
            R.id.single -> {
                createSingle()
            }
            R.id.schedule -> {
                createSchedule()
            }
            R.id.custom -> {
                createCustom()
            }
            R.id.wait -> {
                createWait()
            }
            R.id.full -> {
                createFull()
            }
            R.id.reject -> {
                createReject()
            }
        }
    }

    private fun createReject() {
        val threadPoolExecutor = ThreadPoolExecutor(2, 3, 0, TimeUnit.MICROSECONDS, LinkedBlockingQueue(2), ThreadPoolExecutor.DiscardPolicy())
        for (i in 1..6) {
            var task = i;


            threadPoolExecutor.submit {
                println("线程: ${Thread.currentThread().name} 正在执行 task : $task")
                Thread.sleep(5000)
            }

            println("此时等待队列中有 ${threadPoolExecutor.queue.size}个")
        }
        threadPoolExecutor.shutdown()

    }

    private fun createFull() {
        val threadPoolExecutor = ThreadPoolExecutor(2, 10, 0, TimeUnit.MICROSECONDS, LinkedBlockingQueue(2))
        for (i in 1..10) {
            var task = i;


            threadPoolExecutor.submit {
                println("线程: ${Thread.currentThread().name} 正在执行 task : $task")
                Thread.sleep(5000)
            }

            println("此时等待队列中有 ${threadPoolExecutor.queue.size}个")
        }
        threadPoolExecutor.shutdown()

    }

    private fun createWait() {
        val threadPoolExecutor = ThreadPoolExecutor(2, 2, 0, TimeUnit.MICROSECONDS, LinkedBlockingQueue())
        for (i in 1..10) {
            var task = i;


            threadPoolExecutor.submit {
                println("线程: ${Thread.currentThread().name} 正在执行 task : $task")
                Thread.sleep(2000)
            }

            println("此时等待队列中有 ${threadPoolExecutor.queue.size}个")
        }
        threadPoolExecutor.shutdown()
    }

    private fun createCustom() {

        val threadPoolExecutor = ThreadPoolExecutor(2, 4, 0, TimeUnit.MICROSECONDS, LinkedBlockingQueue())
        for (i in 1..10) {
            var task = i;

            Thread.sleep(2000)

            threadPoolExecutor.submit {
                println("线程: ${Thread.currentThread().name} 正在执行 task : $task")
            }

        }
        threadPoolExecutor.shutdown()
    }

    private fun createFixed() {
        val newFixedThreadPool = Executors.newFixedThreadPool(3)

        for (i in 1..10) {
            var task = i;

            newFixedThreadPool.submit {
                println("线程: ${Thread.currentThread().name} 正在执行 task : $task")
            }


        }
        newFixedThreadPool.shutdown()
    }

    private fun createCache() {
        val newCachedThreadPool = Executors.newCachedThreadPool()
        for (i in 1..10) {
            var task = i;
            Thread.sleep(1000)
            newCachedThreadPool.submit {
                println("线程: ${Thread.currentThread().name} 正在执行 task : $task")
            }


        }

        newCachedThreadPool.shutdown()
    }


    private fun createSingle() {
        val newSingleThreadExecutor = Executors.newSingleThreadExecutor()

        for (i in 1..5) {
            var task = i;
            newSingleThreadExecutor.submit {
                println("线程: ${Thread.currentThread().name} 正在执行 task : $task")
            }
        }
        newSingleThreadExecutor.shutdown()

    }

    private fun createSchedule() {
        val newScheduledThreadPool = Executors.newScheduledThreadPool(2)
        newScheduledThreadPool.scheduleAtFixedRate({
            val date = Date()
            println("线程: ${Thread.currentThread().name}   报时: $date")
        }, 500, 500, TimeUnit.MICROSECONDS)

        Thread.sleep(5000)
        newScheduledThreadPool.shutdown()
    }

}