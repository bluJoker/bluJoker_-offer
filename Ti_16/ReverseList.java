public class ReverseList {

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

    // 反转链表
    // 迭代：
    public ListNode ReverseList(ListNode head) {
        ListNode pReversedList = null;
        ListNode pNode = head;
        ListNode pPrev = null;

        while (pNode != null){
            // 还需要事先保存i的下一个结点j，以防止链表断开
            ListNode pNext = pNode.next;

            if (pNext == null){
                pReversedList = pNode;
            }

            // 在调整结点i的next指针时，需要知道i的前一个结点h，因为i的next要指向结点h
            pNode.next = pPrev;

            pPrev = pNode;
            pNode = pNext;
        }
        return pReversedList;
    }

    // 递归：
    public ListNode ReverseListRecursion(ListNode head) {

        // 链表为空或只有一个结点或已递归到尾结点
        if (head == null || head.next == null){
            return head;
        }

        // 保存当前结点的下一结点
        ListNode pNext = head.next;

        ListNode resultNode = ReverseListRecursion(head.next);

        // 将当前结点的下一结点指向自己
        pNext.next = head;
        // 将自己的next置为空
        head.next = null;

        return resultNode;
    }

    public static void main(String[] args){

        ReverseList list1 = new ReverseList();

        list1.add(1);
        list1.add(9);
        list1.add(19);
        list1.add(55);

        // 递归
        ListNode listNode = list1.ReverseListRecursion(list1.head);

//        迭代
//        ListNode listNode = list1.ReverseList(list1.head);

        System.out.println(" = ");
    }
}
