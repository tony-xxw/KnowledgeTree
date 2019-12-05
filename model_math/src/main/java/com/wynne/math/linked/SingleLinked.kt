package com.wynne.math.linked

import android.util.Log

class SingleLinked {
    var head: Node = Node(null)
    var current: Node = Node(null)
    var size: Int = 0

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun getLong() = size


    fun insertNode(node: Node) {
        var temp = head
        while (temp.next != null) {
            temp = temp.next
        }
        temp.next = node
        size++
    }

    fun resetNode(head: Node): Node {
        var heads = head
        var next: Node? = null
        var pre: Node? = null

        while (heads != null) {
            next = heads.next
            heads.next = pre
            pre = heads
            heads = next
        }
        Log.d("xxw", "${pre!!.data}")
        return pre
    }

    fun insertNodeByIndex(index: Int, node: Node) {
        //检查节点是否合法
        if (index < 1 || index > size + 1) {
            Log.d(SingleLinked::class.java.name, "插入的数据不合法")
            return
        }

        //记录当前指针的位置
        var length = 1
        var temp = head //从头开始的指针
        while (temp.next != null) {
            //当Index找到位置 先把下一个节点 赋值给当前插入的节点的next上,在把当前节点赋值给上一个
            if (index == length++) {
                node.next = temp.next
                temp.next = node
                size++
                return
            }
            temp = temp.next
        }
    }

    fun deletByIndex(index: Int) {
        if (index < 1 || index > size + 1) {
            Log.d(SingleLinked::class.java.name, "插入的数据不合法")
            return
        }
        //记录当前指针的位置
        var length = 1
        var temp = head //从头开始的指针
        while (temp.next != null) {
            if (index == length++) {
                //下一个节点是否为null
                //不为null
                temp.next = temp.next.next
                size--
                return
            }
            temp = temp.next
        }
    }

    fun toPrint() {
        var temp = head.next
        while (temp != null) {
            Log.d("xxw", "next content: ${temp.data}")
            temp = temp.next
        }
        Log.d(SingleLinked::class.java.name, "size: $size")
    }
}