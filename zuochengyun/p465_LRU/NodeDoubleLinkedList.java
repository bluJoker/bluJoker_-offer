package p465_LRU;

public class NodeDoubleLinkedList<V> {
    private Node<V> head;
    private Node<V> tail;

    public NodeDoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // 当加入
    public void addNode(Node<V> newNode) {
        if (newNode == null) {
            return;
        }
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            newNode.last = this.tail;
            this.tail = newNode;
        }
    }

    //
    public void moveNodeToTail(Node<V> node) {
        if (this.tail == node) {
            return;
        }
        if (this.head == node) {
            this.head = node.next;
            this.head.last = null;
        } else {
            node.last.next = node.next;
            node.next.last = node.last;
        }
        node.last = this.tail;
        node.next = null;
        this.tail.next = node;
        this.tail = node;
    }


    //
    public Node<V> removeHead() {
        if (this.head == null) {
            return null;
        }
        Node<V> res = this.head;

        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = res.next;
            res.next = null;
            this.head.last = null;
        }
        return res;
    }
}
