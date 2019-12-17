package com.wynne.math.linked

import android.util.Log
import android.view.View
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.math.R

/**
 * @author Wynne
 */
class LinkedListActivity : BaseActivity() {
    lateinit var singleLinked: SingleLinked
    lateinit var firstLinked: SingleLinked
    lateinit var secondLinked: SingleLinked
    lateinit var recycleSingleLinked: RecyleSingleLinked
    lateinit var pairLinked: PairLinked
    override fun initView() {
        singleLinked = SingleLinked()
        singleLinked.insertNode(Node("a"))
        singleLinked.insertNode(Node("b"))
        singleLinked.insertNode(Node("c"))
//        singleLinked.insertNode(Node("d"))
//        singleLinked.insertNode(Node("e"))
//        singleLinked.insertNode(Node("f"))
//        singleLinked.insertNode(Node("g"))


        secondLinked = SingleLinked()
        secondLinked.insertNode(Node("2"))
        secondLinked.insertNode(Node("3"))
        secondLinked.insertNode(Node("12"))
        secondLinked.insertNode(Node("28"))

        firstLinked = SingleLinked()
        firstLinked.insertNode(Node("4"))
        firstLinked.insertNode(Node("8"))
        firstLinked.insertNode(Node("23"))

        recycleSingleLinked = RecyleSingleLinked()
        recycleSingleLinked.addNode(Node("android"))
        recycleSingleLinked.addNode(Node("ios"))
        recycleSingleLinked.addNode(Node("web"))
        recycleSingleLinked.addNode(Node("java"))
        recycleSingleLinked.addNode(Node("a"))
        recycleSingleLinked.addNode(Node("b"))
        recycleSingleLinked.addNode(Node("c"))
        recycleSingleLinked.addNode(Node("d"))
        recycleSingleLinked.addNode(Node("e"))
        recycleSingleLinked.addNode(Node("f"))
        recycleSingleLinked.addNode(Node("g"))
        recycleSingleLinked.addNode(Node("d"))

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
                var palindormeLike = SingleLinked()

                Log.d("XXW", "是否为回文链表: " + palindormeLike.linkedIsPalindrome())
            }
            R.id.btnLinkedCheck -> {
                Log.d("xxw", "isExist :" + recycleSingleLinked.isExistLoop())
            }
            R.id.btnLinkedMiddleNode -> {
                Log.d("XXW", " middle " + singleLinked.obtainMiddleNode().data)
            }
            R.id.btnSingleReversal -> {
                singleLinked.studyRestNode(singleLinked.head)?.let {
                    singleLinked.toPrint(it)
                }

            }
            R.id.btnLinkedDeleteN -> {
                singleLinked.deleteReciprocalNode(5)
                singleLinked.toPrint()
            }
            R.id.btnLinkedMerge -> {
                firstLinked.mergeOrderlyLinkeds(firstLinked.head, secondLinked.head)?.let {
                    firstLinked.toPrint(it)
                }

            }
        }

    }


}
