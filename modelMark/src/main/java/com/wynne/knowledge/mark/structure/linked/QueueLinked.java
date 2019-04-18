package com.wynne.knowledge.mark.structure.linked;

public class QueueLinked {
    QueueNode front;
    QueueNode rear;

    public QueueLinked() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(Object object) {
        if (rear == null && front == null) {
            rear = new QueueNode(object);
            front = rear;
        } else {
            QueueNode node = new QueueNode(object);
            rear.next = node;
            rear = rear.next;
        }
    }

    public Object dequeue() {
        if (front == null) {
            return null;
        }

        if (front == rear && rear != null) {
            QueueNode node = front;
            rear = null;
            front = null;
            return node.data;
        }

        Object obj = front.data;
        front = front.next;
        return obj;
    }
}
