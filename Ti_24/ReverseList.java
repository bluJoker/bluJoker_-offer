/**
 *
 * head=1
 *    pNext=9
 *    resultNode = RLR(9) --> head=9
 * 							  pNext=19
 * 							  resultNode = RLR(19)--> head=19
 * 													  pNext=55
 * 													  resultNode = RLR(55)--> head=55
 * 																			  if (head == null || head.next == null)
 * 																 =55		  <--		return 55
 * 													  55.next = 19;
 * 													  19.next = null;
 * 													  <==>        null<--19<--55
 * 					                     = 55		  <-- return 55;
 * 							  19.next = 9;
 * 							  9.next = null;
 * 							  <==>        null<--9<--19<--55
 * 							  <-- return 55;
 *    9.next = 1;
 *    1.next = null;
 *    <==>        null<--1<--9<--19<--55
 *    <-- return 55;
 *
 * */
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
    public ListNode reverseList1(ListNode head) {
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
    public ListNode reverseListRecursion(ListNode head) {

        // 链表为空或只有一个结点或已递归到尾结点
        if (head == null || head.next == null){
            return head;
        }

        // 保存当前结点的下一结点
        ListNode pNext = head.next;

        // 通过递归栈保存了head的前一结点。然后从后往前反转链表
        ListNode resultNode = reverseListRecursion(head.next);

        // 将当前结点的下一结点指向自己
        pNext.next = head;
        // 将自己的next置为空
        head.next = null;

        // 返回的总是最后一次return的head，后面一直不变
        return resultNode;
    }

    public ListNode reverseList2(ListNode head) {
        // 链表为空
        if (head == null){
            return null;
        }

        // 保存当前结点的下一结点
        ListNode pPrev = head;
        ListNode pNode = head.next;

        while (pNode != null){
            ListNode pNext = pNode.next;
            pNode.next = pPrev;

            if (pPrev == head){
                pPrev.next = null;
            }

            pPrev = pNode;
            pNode = pNext;
        }

        return pPrev;
    }

    public void print(ListNode head){
        ListNode node = head;

        while (node != null){

            System.out.print(node.val + " -> ");
            node = node.next;
        }
    }
    public static void main(String[] args){

        ReverseList list1 = new ReverseList();

        list1.add(1);
        list1.add(9);
        list1.add(19);
        list1.add(55);

        // 递归
//        ListNode listNode = list1.ReverseListRecursion(list1.head);

        ListNode listNode = list1.reverseList2(list1.head);


        list1.print(listNode);


//        迭代
//        ListNode listNode = list1.ReverseList(list1.head);

//        System.out.println(" = ");
    }
}
