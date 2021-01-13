package com.wynne.design.prototype


//原型模式
fun main() {
    val document = WorkDocument()
    document.text = "这是文档"
    document.imageList.add("图片1")
    document.imageList.add("图片2")
    document.imageList.add("图片3")
    document.showDocument()

    val document1 = document.clone()
    document1.showDocument()
    document1.text = "这是文档12222"
    //string 是基础数据类型 不受引用类型影响
    document1.imageList.add("111")
    document1.showDocument()

    document.showDocument()
}

class WorkDocument(var text: String = "", var imageList: ArrayList<String> = arrayListOf<String>()) : Cloneable {


    init {
        println("-----WorkDocument 构造函数-----")
    }

    public override fun clone(): WorkDocument {
        val document = super.clone() as WorkDocument
        document.text = this.text
        //深拷贝
        document.imageList = this.imageList.clone() as ArrayList<String>
        return document
    }

    fun showDocument() {
        println()
        println("-----WorkDocument start-----")
        println("Text: $text")
        println("imageList: $imageList")
        println("-----WorkDocument end-----")
        println()
    }
}

