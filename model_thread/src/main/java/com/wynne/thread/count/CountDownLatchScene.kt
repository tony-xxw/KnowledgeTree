package com.wynne.thread.count

import android.util.Log
import java.util.concurrent.CountDownLatch

/**
 * 假设一条流水线上有三个工作者：worker0，worker1，worker2。
 * 有一个任务的完成需要他们三者协作完成，
 * worker2可以开始这个任务的前提是worker0和worker1完成了他们的工作，
 * 而worker0和worker1是可以并行他们各自的工作的
 */
object CountDownLatchScene {


    fun start() {
//        threadVersion()
        countDownVersion()
    }


    fun threadVersion() {
        val worker = Worker("work0", (Math.random() * 2000 + 3000).toLong())
        val worker1 = Worker("work1", (Math.random() * 2000 + 3000).toLong())
        val worker2 = Worker("work2", (Math.random() * 2000 + 3000).toLong())

        worker.start()
        worker1.start()

        worker.join()
        worker1.join()

        Log.d("CountDownLatchScene", "准备工作就绪")
        worker2.start()
    }


    fun countDownVersion() {
        val countDownLatch = CountDownLatch(2)
        val worker = Worker1("work0", (Math.random() * 2000 + 3000).toLong(), countDownLatch)
        val worker1 = Worker1("work1", (Math.random() * 2000 + 3000).toLong(), countDownLatch)
        val worker2 = Worker1("work2", (Math.random() * 2000 + 3000).toLong(), countDownLatch)

        worker.start()
        worker1.start()

       countDownLatch.await()

        Log.d("CountDownLatchScene", "准备工作就绪")
        worker2.start()
    }
}

class Worker1(name: String, time: Long, var countDownLatch: CountDownLatch) : Thread() {
    var mName: String = name
    var mTime: Long = time
    var mCountDownLatch: CountDownLatch = countDownLatch


    override fun run() {
        super.run()
        Log.d("CountDownLatchScene", "${mName}: 开始工作1")
        sleep(mTime)
        Log.d("CountDownLatchScene", "${mName}: 工作完成,耗费时间 $mTime")
        mCountDownLatch.countDown()
        Log.d("CountDownLatchScene", "CountDownLatchScene.getCount = ${mCountDownLatch.count}")

        sleep(2000)
        Log.d("CountDownLatchScene", "${mName}: 开始工作2")
    }
}


class Worker(name: String, time: Long) : Thread() {
    var mName: String = name
    var mTime: Long = time


    override fun run() {
        super.run()

        Log.d("CountDownLatchScene", "${mName}: 开始工作")
        sleep(mTime)
        Log.d("CountDownLatchScene", "${mName}: 工作完成,耗费时间 $mTime")

    }
}
