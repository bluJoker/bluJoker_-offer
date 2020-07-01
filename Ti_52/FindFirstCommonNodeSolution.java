public class FindFirstCommonNodeSolution {
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

    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null){
            return null;
        }
        ListNode p1 = pHead1;
        ListNode p2 = pHead2;

        int pLength1 = 0;
        int pLength2 = 0;

        while (p1 != null){
            p1 = p1.next;
            pLength1++;
        }
        while (p2 != null){
            p2 = p2.next;
            pLength2++;
        }

        int length = pLength1 - pLength2;
        p1 = pHead1;
        p2 = pHead2;
        if (length > 0){
            for (int i = 0; i < length; i++) {
                p1 = p1.next;
            }
        }else {
            for (int i = 0; i < length; i++) {
                p2 = p2.next;
            }
        }

        while (p1 !=null && p2 !=null){
            if (p1 == p2){
                return p1;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;
    }


    public static void main(String[] args) {
        FindFirstCommonNodeSolution findFirstCommonNodeSolution1 = new FindFirstCommonNodeSolution();
        findFirstCommonNodeSolution1.add(1);
        findFirstCommonNodeSolution1.add(2);
        findFirstCommonNodeSolution1.add(3);

        // 非相交
        findFirstCommonNodeSolution1.add(6);
        findFirstCommonNodeSolution1.add(7);

        FindFirstCommonNodeSolution findFirstCommonNodeSolution2 = new FindFirstCommonNodeSolution();
        findFirstCommonNodeSolution2.add(4);
        findFirstCommonNodeSolution2.add(5);

        // 非相交
        findFirstCommonNodeSolution2.add(6);
        findFirstCommonNodeSolution2.add(7);

        FindFirstCommonNodeSolution findFirstCommonNodeSolution3 = new FindFirstCommonNodeSolution();


        findFirstCommonNodeSolution3.head = FindFirstCommonNodeSolution.FindFirstCommonNode(findFirstCommonNodeSolution1.head, findFirstCommonNodeSolution2.head);

        System.out.println();
    }
}
