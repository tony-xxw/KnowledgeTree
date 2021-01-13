package com.wynne.design.single

/**
 * 常见的四种单例模式
 */
fun main() {
    println("单例1 $SinglePattern1")
    println("单例1 $SinglePattern1")

    println()
    println("单例2 ${SinglePattern2.get()}")
    println("单例2 ${SinglePattern2.get()}")

    println()
    println("单例3 ${SinglePattern3.get()}")
    println("单例3 ${SinglePattern3.get()}")

    println()
    println("单例4 ${SinglePattern4.instance}")
    println("单例4 ${SinglePattern4.instance}")

    println()
    println("单例5 ${SinglePattern5.instance}")
    println("单例5 ${SinglePattern5.instance}")


    //构建模式 见other-> chapter9
}


object SinglePattern1

class SinglePattern2 private constructor() {
    companion object {
        private var instance: SinglePattern2? = null
            get() {
                if (field == null) {
                    field = SinglePattern2()
                }
                return field
            }

        fun get(): SinglePattern2 {
            return instance!!
        }
    }
}

class SinglePattern3 private constructor() {
    companion object {
        private var instance: SinglePattern3? = null
            get() {
                if (field == null) {
                    field = SinglePattern3()
                }
                return field
            }

        @Synchronized
        fun get(): SinglePattern3 {
            return instance!!
        }
    }
}

class SinglePattern4 private constructor() {
    companion object {
        val instance: SinglePattern4 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SinglePattern4()
        }
    }
}

class SinglePattern5 private constructor() {
    companion object {
        val instance = SinglePatternInner.instance
    }

    private object SinglePatternInner {
        val instance = SinglePattern5()
    }
}