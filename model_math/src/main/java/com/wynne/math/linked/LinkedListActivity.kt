package com.wynne.math.linked

import android.view.View

import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.math.R

/**
 * @author Wynne
 */
class LinkedListActivity : BaseActivity() {
    lateinit var singleLinked: SingleLinked
    lateinit var recycleSingleLinked: RecyleSingleLinked
    lateinit var pairLinked: PairLinked
    override fun initView() {
        singleLinked = SingleLinked()
        singleLinked.insertNode(Node("a"))
        singleLinked.insertNode(Node("b"))
        singleLinked.insertNode(Node("c"))

        recycleSingleLinked = RecyleSingleLinked()
        recycleSingleLinked.addNode(Node("android"))
        recycleSingleLinked.addNode(Node("ios"))
        recycleSingleLinked.addNode(Node("web"))
        recycleSingleLinked.addNode(Node("java"))

        pairLinked = PairLinked()
        pairLinked.addNode(DoubleNode("android"))
        pairLinked.addNode(DoubleNode("ios"))
        pairLinked.addNode(DoubleNode("web"))
        pairLinked.addNode(DoubleNode("java"))

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
                pairLinked.toPrint()
            }
            R.id.btnRecycle -> {
                recycleSingleLinked.toPrint()
            }
            R.id.btnSame -> {

            }
            R.id.btnSingleReversal -> {
                singleLinked.resetNode(singleLinked.head)
                singleLinked.toPrint()
            }
        }

    }


}
