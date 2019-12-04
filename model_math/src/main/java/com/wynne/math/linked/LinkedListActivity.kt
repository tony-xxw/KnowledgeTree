package com.wynne.math.linked

import android.view.View

import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.math.R

/**
 * @author Wynne
 */
class LinkedListActivity : BaseActivity() {
    lateinit var singleLinked: SingleLinked
    override fun initView() {
        singleLinked = SingleLinked()
        singleLinked.insertNode(Node("a"))
        singleLinked.insertNode(Node("b"))
        singleLinked.insertNode(Node("c"))

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_base_math_linked_layout
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btnSingle -> {
                singleLinked.deletByIndex(3)
                singleLinked.toPrint()
            }
            R.id.btnDouble -> {

            }
            R.id.btnRecycle -> {

            }
            R.id.btnSame -> {

            }
        }

    }


}
