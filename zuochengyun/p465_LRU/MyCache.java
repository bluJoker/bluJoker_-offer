package p465_LRU;

import java.util.HashMap;

public class MyCache<K, V> {
    private HashMap<K, Node<V>> keyNodeMap;
    private HashMap<Node<V>, K> nodeKeyMap;
    private NodeDoubleLinkedList<V> nodeList;
    private int capacity;

    public MyCache(int capacity) {
        if (capacity < 1) {
            throw new RuntimeException("should be more than 0.");
        }

        this.keyNodeMap = new HashMap<K, Node<V>>();
        this.nodeKeyMap = new HashMap<Node<V>, K>();
        this.nodeList = new NodeDoubleLinkedList<>();
        this.capacity = capacity;
    }

    public V get(K key) {
        if (this.keyNodeMap.containsKey(key)) {
            Node<V> res = this.keyNodeMap.get(key);
            this.nodeList.moveNodeToTail(res);
            return res.value;
        }
        return null;
    }

    public void set(K key, V value) {
        if (this.keyNodeMap.containsKey(key)) {
            Node<V> node = this.keyNodeMap.get(key);
            node.value = value;
            this.nodeList.moveNodeToTail(node);
        } else {
            Node<V> newNode = new Node<V>(value);
            this.keyNodeMap.put(key, newNode);
            this.nodeKeyMap.put(newNode, key);
            this.nodeList.addNode(newNode);
            if (this.keyNodeMap.size() == this.capacity + 1) {
                this.removeMostUnusedCache();
            }
        }
    }

    private void removeMostUnusedCache() {
        Node<V> removeNode = this.nodeList.removeHead();
        K removeKey = this.nodeKeyMap.get(removeNode);
        this.nodeKeyMap.remove(removeNode);
        // 如果没有nodeKeyMap找到要删除结点对应的key，则keyNodeMap无法删除该Node
        this.keyNodeMap.remove(removeKey);
    }
}
