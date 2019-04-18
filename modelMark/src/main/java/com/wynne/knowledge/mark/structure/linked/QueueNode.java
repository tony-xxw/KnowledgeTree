package com.wynne.knowledge.mark.structure.linked;

public class QueueNode {
    /**
     * 节点存储的数据
     */
    Object data;
    /**
     * 下一个节点的指针
     */
    QueueNode next;


    public QueueNode() {
        this(null, null);
    }


    public QueueNode(Object data) {
        this(data, null);
    }

    public QueueNode(Object data, QueueNode next) {
        this.data = data;
        this.next = next;
    }

}
