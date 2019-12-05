package com.wynne.math.linked;

public class DoubleNode {
    DoubleNode next;
    DoubleNode pre;
    Object data;

    public DoubleNode(DoubleNode next, DoubleNode pre, Object data) {
        this.next = next;
        this.pre = pre;
        this.data = data;
    }

    public DoubleNode(Object data) {
        this(null, null, data);
    }
}
