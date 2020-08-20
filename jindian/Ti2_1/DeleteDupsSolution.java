package Ti2_1;

import java.util.HashSet;

public class DeleteDupsSolution {

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode head;

    // 在链表尾添加新的元素e
    public void add(int e){
        ListNode node = new ListNode(e);
        ListNode x = head;
        if (x == null){
            head = node;
        }else{
            while (x.next != null){
                x = x.next;
            }
            x.next = node;
        }
    }

    public void deleteDups(ListNode node){
        HashSet<Integer> set = new HashSet<Integer>();
        ListNode previous = null;
        while (node != null){
            if (set.contains(node.val)){
                previous.next = node.next;
            }else {
                set.add(node.val);
                previous = node;
            }
            node = node.next;
        }
    }


    public static void main(String[] args) {
        DeleteDupsSolution deleteDupsSolution = new DeleteDupsSolution();
        deleteDupsSolution.add(1);
        deleteDupsSolution.add(1);
        deleteDupsSolution.add(2);
        deleteDupsSolution.add(1);
        deleteDupsSolution.add(3);
        deleteDupsSolution.deleteDups(deleteDupsSolution.head);

        System.out.println("");
    }
}
