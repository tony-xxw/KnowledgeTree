package com.wynne.math.linked

import android.util.Log

class PairLinked {
    internal var head: DoubleNode = DoubleNode(null)
    var size = 0


    fun addNode(doubleNode: DoubleNode) {
        var temp = head
        while (temp.next != null) {
            temp.next.pre = temp
            temp = temp.next
            size++
        }
        temp.next = doubleNode

    }

    fun toPrint() {

        var temp = head
        while (temp.next != null) {
            temp.next.pre = temp
            temp = temp.next

            if (temp.next == null) {
                Log.d("xxw", "当前content : ${temp.data}  " +
                        "上一个链表的content:${if (temp.pre.data != null) temp.pre.data else "head"} " +
                        "这是最后一个链表")
            } else {
                Log.d("xxw", "当前content : ${temp.data}  " +
                        "上一个链表的content:${if (temp.pre.data != null) temp.pre.data else "head"} " +
                        "下一个链表的content ${if (temp.next != null) temp.next.data else "end"}")
            }

        }
    }

}
