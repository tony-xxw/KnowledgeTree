package com.wynne.math.linked

import java.util.*

var linkedList = SingleLinked()


fun main() {
    baseLinkedExercise()
}

fun baseLinked() {
    println("添加")
    addLinked()
    println()
    println("指定位置添加 :${4}")
    addPositionLinked(4)
    println()
    println("指定删除 ${3}")
    deleteLinked(3)
    println()
    println("指定删除倒数第N个几点")
    positionDelete()
    println()
    println("链表反转")
    reverseLinked()
}

fun addPositionLinked(index: Int) {
    linkedList.insertNodeByIndex(index, Node("php"))
    linkedList.toPrint()
}

fun addLinked() {
    linkedList.insertNode(Node("Java"))
    linkedList.insertNode(Node("JavaScript"))
    linkedList.insertNode(Node("Kotlin"))
    linkedList.insertNode(Node("Python"))
    linkedList.insertNode(Node("Go"))
    linkedList.insertNode(Node("C++"))
    linkedList.insertNode(Node("C"))

    linkedList.toPrint()
}

fun deleteLinked(index: Int) {
    linkedList.deletByIndex(index)
    linkedList.toPrint()
}

fun positionDelete() {
    linkedList.deleteReciprocalNode(3)
    linkedList.toPrint()
}

fun reverseLinked() {
    var cur = Node(null)
    var temp = linkedList.head.next
    var reverse = Node("head")

    while (temp.next != null) {
        cur = temp.next
        temp.next = reverse
        reverse = temp
        temp = cur
    }
    temp.next = reverse
    reverse = temp
    linkedList.toPrint(reverse)
}

fun baseLinkedExercise() {
    linkedList.insertNode(Node("1"))
    linkedList.insertNode(Node("3"))
    linkedList.insertNode(Node("4"))
    linkedList.insertNode(Node("61"))
    linkedList.insertNode(Node("89"))
    linkedList.insertNode(Node("114"))
    linkedList.insertNode(Node("279"))
    linkedList.insertNode(Node("360"))
    println("合并有序链表 (递归)")
    mergeSortLinked()
    println()
    println("获取单链表的中间节点")
    findMiddleNode()
    println()
    println("是否是回文链表")
    palindromeNode()
    println()
    println("链表中是否有环")
    isRuning()

}

fun mergeSortLinked() {
    val singleLinked = SingleLinked()
    singleLinked.insertNode(Node("23"))
    singleLinked.insertNode(Node("44"))
    linkedList.toPrint(linkedList.mergeOrderlyLinkeds(sencond = singleLinked.head)!!)
}

fun findMiddleNode() {
    println(linkedList.obtainMiddleNode(linkedList.head).data)
}


var frontPoint: Node? = null
fun palindromeNode() {
    val singleLinked = SingleLinked()
    singleLinked.insertNode(Node("a"))
    singleLinked.insertNode(Node("b"))
    singleLinked.insertNode(Node("c"))
    singleLinked.insertNode(Node("b"))
    singleLinked.insertNode(Node("a"))
//    println(singleLinked.linkedIsPalindrome(singleLinked.head))

    frontPoint = singleLinked.head.next

    println(recursivelyCheck(singleLinked.head.next))
}

fun recursivelyCheck(node: Node?): Boolean {
    if (node != null) {
        if (!recursivelyCheck(node.next)) {
            return false
        }
        if (node.data != frontPoint?.data) {
            return false
        }
        frontPoint = frontPoint?.next
    }
    return true
}

fun isRuning() {
    val singleLinked = SingleLinked()
    singleLinked.insertNode(Node("a"))
    singleLinked.insertNode(Node("b"))
    singleLinked.insertNode(Node("c"))
    singleLinked.insertNode(Node("d"))
    singleLinked.insertNode(Node("e"))
    singleLinked.insertNode(singleLinked.head)

    var fast = singleLinked.head.next
    var slow = singleLinked.head
    var result = false
    while (fast.next != null) {

        fast = fast.next.next
        slow = slow.next
        if (fast.data == slow.data) {
            result = true
            break
        }
    }
    println("存在环 ${result}")
}

fun doubleLinked() {

}


fun josephLinked() {

}
