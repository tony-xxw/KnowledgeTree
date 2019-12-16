package com.wynne.math.linked

import android.util.Log

class RecyleSingleLinked {
    var head: Node = Node(null)
    var size = 0
    var recycle = 0
    var value = "d"

    var recyleNode: Node? = null
    fun addNode(node: Node) {
        var temp = head


        while (temp.next != null) {
            if (temp.data == value) {
                recyleNode = temp
            }
            temp = temp.next
            size++
        }

        if (recyleNode != null && recyleNode!!.data == node.data) {
            temp.next = recyleNode
        } else {
            temp.next = node
        }


    }


    fun isExistLoop(): Boolean {
        var q = head
        var p = head.next

        while (p != null && p.next != null) {
            q = q.next
            p = p.next.next

            if (q == null) {
                return false
            } else if (p == q) {
                return true
            }
        }
        return false

    }

    fun toPrint() {
        var temp = head
        while (temp.next != null && temp.next != head) {
            if (temp.data == value) {
                recycle++
            }
            if (recycle == 3) {
                break
            }
            temp = temp.next
            Log.d("recycle", "content: ${temp.data}   size: $size")
        }

        if (temp.data == recyleNode!!.data) {
            Log.d("recycle", "尾部: ${temp.data}  尾部的下一个recyleNode头部为: ${temp.next.data}")
        }
    }

}