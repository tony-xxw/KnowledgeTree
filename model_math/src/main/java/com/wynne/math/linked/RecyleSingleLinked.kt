package com.wynne.math.linked

import android.util.Log

class RecyleSingleLinked {
    var head: Node = Node(null)
    var size = 0


    fun addNode(node: Node) {
        var temp = head

        while (temp.next != null && temp.next != head) {
            temp = temp.next
            size++
        }
        if (size == 0) {
            temp.next = node
        } else {
            node.next = head
            temp.next = node
        }
    }

    fun toPrint() {
        var temp = head
        while (temp.next != null && temp.next != head) {
            temp = temp.next
            Log.d("recycle", "content: ${temp.data}   size: $size")
        }

        if (temp.next == head) {
            Log.d("recycle", "尾部: ${temp.data}  尾部的下一个head 头部为: ${temp.next.data}")
        }
    }

}