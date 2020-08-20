package Ti16_25;

import java.util.HashMap;

/**
 * 感觉写的没有左程云书P465中的解法清晰
 *
 * */
public class Cache {
    private int maxCacheSize;
    private HashMap<Integer, LinkedListNode> map = new HashMap<Integer, LinkedListNode>();

    private LinkedListNode listHead = null;
    public LinkedListNode listTail = null;

    public Cache(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }


    /* 从链表中移除节点 */
    private void removeFromLinkedList(LinkedListNode node){
        if (node == null){
            return;
        }
        if (node.prev != null){
            node.prev.next = node.next;
        }
        if (node.next != null){
            node.next.prev = node.prev;
        }
        if (node == listHead){
            listHead = node.next;
        }
        if (node == listTail){
            listTail = node.prev;
        }
    }

    /* 插入到链表前端 */
    private void insertAtFrontOfLinkedList(LinkedListNode node){
        if (listHead == null){
            listHead = node;
            listTail = node;
        }else {
            listHead.prev = node;
            node.next = listHead;
            listHead = node;
        }
    }

    /* 将键值对从缓存中移除，即从链表和散列表中移除 */
    public boolean removeKey(int key){
        LinkedListNode node = map.get(key);
        removeFromLinkedList(node);
        map.remove(key);
        return true;
    }

    /* 将键值对插入到缓存中。如果需要则删除旧的值。
     * 将键值对插入到链表和散列表中 */
//    public void seKeyValue

    private static class LinkedListNode{
        private LinkedListNode next, prev;
        public int key;
        public String value;

        public LinkedListNode(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
