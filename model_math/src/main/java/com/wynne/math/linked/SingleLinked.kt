package com.wynne.math.linked

import android.util.Log

class SingleLinked {
    var head: Node = Node("head")
    var size: Int = 0


    /**
     * 插入节点(从尾端)
     */
    fun insertNode(node: Node) {
        var temp = head
        while (temp.next != null) {
            temp = temp.next
        }
        temp.next = node
        size++
    }

    /**
     * 反转链表
     */
    fun studyRestNode(node: Node = head): Node {
        var cur = Node(null)
        var temp = node
        var reverse = Node("reverse")

        while (temp.next != null) {
            cur = temp.next
            temp.next = reverse
            reverse = temp
            temp = cur
        }
        temp.next = reverse
        head = temp
        return temp
    }

    /**
     * 删除倒数N的节点
     */
    fun deleteReciprocalNode(reciprocal: Int) {
        if (reciprocal < 0 || reciprocal > size) {
            Log.d("xxw", "当前不符合")
            return
        }
        var pre = head
        var cur = head
        var num = 0
        while (num < reciprocal) {
            cur = cur.next
            num++
        }
        while (cur.next != null) {
            cur = cur.next
            pre = pre.next
        }
        pre.next = pre.next.next
    }


    /**
     * 指定位置插入
     */
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

    /**
     * 指定位置删除
     */
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

    /**
     *
     * 合并有序链表 (递归)
     */
    fun mergeOrderlyLinkeds(first: Node = head, sencond: Node?): Node? {
        var linked: Node
        var tempA: Node
        var tempB: Node

        if (first == null || sencond == null) {

            if (first != null) {
                return first
            }

            if (sencond != null) {
                return sencond
            }
            return null
        } else {
            tempA = first
            tempB = sencond
        }
        if (tempA.data == "head" && tempB.data == "head") {
            tempA = tempA.next
            tempB = tempB.next
        }



        if (tempA.data.toString().toInt() > tempB.data.toString().toInt()) {

            linked = tempB
            linked.next = mergeOrderlyLinkeds(tempA, tempB.next)
        } else {
            linked = tempA
            linked.next = mergeOrderlyLinkeds(tempA.next, tempB)
        }

        return linked
    }


    /**
     * 获取单链表的中间节点
     */
    fun obtainMiddleNode(node: Node = head): Node {
        var pre: Node = node
        var preTwo: Node = node


        while (pre.next != null) {
            pre = pre.next
            if (preTwo.next == null || preTwo.next.next == null) {
                break
            } else {
                preTwo = preTwo.next.next
            }
        }

        return pre

    }

    /**
     * 是否回文
     */
    fun linkedIsPalindrome(node: Node = head): Boolean {
        var middle = obtainMiddleNode(node)
        //翻转
        studyRestNode(middle)?.let {
            middle = it
        }
        var temp = node.next
        var result: Boolean

        while (temp.next != null && middle.next != null) {
            result = temp.data == middle.data
            if (!result) {
                break
            }
            temp = temp.next
            middle = middle.next
        }
        //当middle.next || temp.next 有一个为null 表明已经是最后一个,此时需要单独判断
        result = temp.data == middle.data

        return result
    }


    fun toPrint(node: Node = head) {
        var temp = node
        while (temp.next != null) {
            println("content: ${temp.data}")
            temp = temp.next
        }
        println("content: ${temp.data}")
    }
}