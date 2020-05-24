package com.wynne.other.grammar

 import android.util.Log
 import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.knowledge.base.utils.LogUtil
import com.wynne.other.R

class KotlinGrammarActivity : BaseActivity(),KotlinInterface {
    override val prop: Int= 29

    override fun bar() {

    }

    private val items = listOf("android", "kotlin", "java")
    private val grammars = listOf<Grammar>(Grammar("android"), Grammar("kotlin"), Grammar("java"), Grammar(null))

    override fun initView() {
        LogUtil.d(propertyWithImplementation)
        baseGrammar()

    }

    private fun baseGrammar() {
        //        stringTemplate()

//        symbolIs("a")

//        foreach()

//        mapGrammar()

        grammars.forEach {
            it.name?.let { value ->
                LogUtil.d(value)
            }
        }

        LogUtil.d(SingleObject.name)
        LogUtil.d(items[0].extendToUpperCase())

        LogUtil.d(SingleObject.apply {
            age = 1
        }.age.toString())
    }

    private fun String.extendToUpperCase(string: String = "android") = string.toUpperCase()

    /**
     * 集合语法
     */
    private fun mapGrammar() {
        when {
            "b" in items -> {
                LogUtil.d("b in items")
            }
            "d" in items -> {
                LogUtil.d("d in items")
            }

            Grammar("android") in grammars -> {
                LogUtil.d("android class in grammars")
            }
        }


        items.filter { it.startsWith("a") }
                .sortedBy { it }
                .map { it.toUpperCase() }
                .forEach { LogUtil.d(it) }

    }

    /**
     * for循环
     */
    private fun foreach() {

        for (item in items) {
            LogUtil.d(item)
        }
        for (index in items.indices) {
            LogUtil.d(index.toString())
        }

        items.forEach {
            LogUtil.d(it)
        }
        // in 可以取反 来判断在范围外

        if (-1 !in 0..items.lastIndex) {
            LogUtil.d("true")
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_grammar_other_layout

    /**
     * 字符串模板
     */
    private fun stringTemplate() {
        var a = 1
        val template = "a is $a"

        a = 2
        val template1 = "${template.replace("is", "was")},but now is $a"
        LogUtil.d(template1)

    }

    /**
     * is 转换符 如果是某类型则转换,在判断的这个闭包类自动转换为某类型,出包则又恢复
     */
    private fun symbolIs(obj: Any) {
        if (obj is String) LogUtil.d(obj)
        LogUtil.d(obj.toString())
    }
}