package com.wynne.math.linked

import android.util.Log

class SingleLinked {
    var head: Node = Node("head")
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

    fun studyRestNode(node: Node): Node? {
        var next: Node? = null
        var pre: Node? = null
        var temp: Node?
        temp = node

        while (temp != null) {
            next = temp.next
            temp.next = pre
            pre = temp
            temp = next
        }
        return pre
    }

    fun deleteReciprocalNode(reciprocal: Int) {
        if (reciprocal < 0 || reciprocal > size) {
            Log.d("xxw", "当前不符合")
            return
        }
        var temp = head
        for (index in 0..size) {
            if (temp.next != null) {
                if (reciprocal + index == size) {
                    temp.next = temp.next.next
                } else {
                    temp = temp.next
                }
            }
        }
    }

    fun resetNode(head: Node): Node? {
        var temp: Node?
        temp = head
        var next: Node? = null
        var pre: Node? = null

        while (temp != null) {
            next = temp.next
            temp.next = pre
            pre = temp
            temp = next
        }
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

    fun toPrint(head: Node) {
        var temp = head
        while (temp.next != null) {
            Log.d("xxw", "next content: ${temp.data}")
            temp = temp.next
        }
        Log.d("xxw", "next content: ${temp.data}")
    }

    fun mergeOrderlyLinked(first: SingleLinked, sencond: SingleLinked): SingleLinked {
        val mergeLinked = SingleLinked()


        if (first.size > sencond.size) {
            var tempFirst = first.head
            var tempSecond = sencond.head
            for (index in 1..first.size) {
                if (tempFirst.data == "head") {
                    //如果是头 直接跳出循环
                    tempFirst = tempFirst.next
                    continue
                }
                for (i in 1..sencond.size) {
                    if (tempSecond.data == "head") {
                        tempSecond = tempSecond.next
                        continue
                    }
                    val firstValue = tempFirst.data.toString().toInt()
                    val secondValue = tempSecond.data.toString().toInt()
                    if (firstValue > secondValue) {
                        mergeLinked.insertNode(tempSecond)
                    } else {
                        mergeLinked.insertNode(tempFirst)
                        tempFirst = tempFirst.next
                        break
                    }
                    tempSecond = tempSecond.next
                }
            }
        } else if (first.size < sencond.size) {


            var tempFirst = first.head
            var tempSecond = sencond.head
            for (index in 1..first.size) {
                if (tempFirst.data == "head" && tempSecond.data == "head") {
                    //如果是头 直接跳出循环
                    tempFirst = tempFirst.next
                    tempSecond = tempSecond.next
                    continue
                }
                for (i in 1..sencond.size) {
                    val firstValue = tempFirst.data.toString().toInt()
                    val secondValue = tempSecond.data.toString().toInt()
                    if (firstValue > secondValue) {
                        mergeLinked.insertNode(Node(tempSecond.data))
                        tempSecond = tempSecond.next
                    } else {
                        mergeLinked.insertNode(Node(tempFirst.data))
                        if (tempFirst.next != null) {
                            tempFirst = tempFirst.next
                        }
                    }
                }
            }

        } else {
            var tempFirst = first.head
            var tempSecond = sencond.head
            for (index in 1..first.size) {
                if (tempFirst.data == "head" && tempSecond.data == "head") {
                    //如果是头 直接跳出循环
                    tempFirst = tempFirst.next
                    tempSecond = tempSecond.next
                    continue
                }
                for (i in 1..sencond.size) {
                    val firstValue = tempFirst.data.toString().toInt()
                    val secondValue = tempSecond.data.toString().toInt()
                    if (firstValue > secondValue) {
                        mergeLinked.insertNode(Node(tempSecond.data))
                        tempSecond = tempSecond.next
                    } else {
                        mergeLinked.insertNode(Node(tempFirst.data))
                        if (tempFirst.next != null) {
                            tempFirst = tempFirst.next
                        } else {
                            mergeLinked.insertNode(Node(tempSecond.data))
                            break
                        }
                    }
                }
            }
        }


        return mergeLinked
    }


    fun returnSize(): Int = size

    fun mergeOrderlyLinkeds(first: Node?, sencond: Node?): Node? {
        var linked: Node
        var tempA: Node
        var tempB: Node

        if (first == null || sencond == null) {
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

        if (linked.next==null){

        }
        return linked

    }
}