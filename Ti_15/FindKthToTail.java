public class FindKthToTail {

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

    // 链表中倒数第k个结点
    public ListNode FindKthToTail(ListNode head,int k) {
        if (head == null){
            return null;
        }

        ListNode p1 = head;
        int n = 0;
        while (p1 != null){
            n++;
            p1 = p1.next;
        }

        if (n < k){
            return null;
        }

        ListNode p2 = head;
        for (int i = 0; i < n-k; i++) {
            p2 = p2.next;
        }
        return p2;
    }




    public static void main(String[] args){

        FindKthToTail linkedList = new FindKthToTail();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        ListNode listNode = linkedList.FindKthToTail(linkedList.head, 1);

        System.out.println("listNode.val = " + listNode.val);
    }
}
