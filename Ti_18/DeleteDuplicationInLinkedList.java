public class DeleteDuplicationInLinkedList {

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


    public ListNode deleteDuplication(ListNode pHead)
    {
        if (pHead == null){
            return null;
        }
        if (pHead.next == null){
            return pHead;
        }

        ListNode pNodePrev = pHead;
        ListNode pNodeNext = pHead.next;

        while (pNodeNext != null){
            if (pNodePrev.val == pNodeNext.val){
                pNodeNext = pNodeNext.next;
                pNodePrev.next = pNodeNext;
//                pNodeNext = null;
            }else {
                pNodePrev = pNodePrev.next;
                pNodeNext = pNodeNext.next;
            }
        }
        return pHead;
    }

    public ListNode deleteDuplicationAll(ListNode pHead)
    {
        if (pHead == null){
            return null;
        }

        ListNode pPrev = null;
        ListNode pNode = pHead;

        while (pNode != null){

            boolean needDelete = false;
            ListNode pNext = pNode.next;
            if (pNext != null && pNode.val == pNext.val){
                needDelete = true;
            }

            if (!needDelete){
                // 删除多个重复元素后pPrev需要重新指向删除后的新元素
                pPrev = pNode;
                pNode = pNode.next;
            }else {
                // 不能使用外层循环的pNode
                ListNode pDelete = pNode;
                int value = pNode.val;

                // Java中不需要手动垃圾回收，省略此处的pNext
//                while (pDelete.val == value){
//                    pNext = pDelete.next;
//                    pDelete = null;
//                    pDelete = pNext;
//                }

                // 删除多个重复循环条件（其val等于pNode.val的结点）
                while (pDelete != null && pDelete.val == value){
                    pDelete = pDelete.next;
                }
                if (pPrev == null){
                    // 1  ->  1  ->  2..
                    //              phead
                    //              pNext
                    pHead = pDelete;
                }else {
                    // 确保pPrev始终与下一个没有重复的结点连接在一起
                    pPrev.next = pDelete;
                }
                pNode = pDelete;
            }
        }
        return pHead;
    }


    public static void main(String[] args){

        DeleteDuplicationInLinkedList demo = new DeleteDuplicationInLinkedList();

        demo.add(1);
        demo.add(2);
        demo.add(3);
        demo.add(3);
        demo.add(4);
        demo.add(4);
        demo.add(5);

        DeleteDuplicationInLinkedList demo2 = new DeleteDuplicationInLinkedList();

        demo2.add(1);
        demo2.add(1);
        demo2.add(1);
        demo2.add(1);
        demo2.add(1);
        demo2.add(1);
        demo2.add(1);

//        System.out.println("list = " + demo.printListFromTailToHeadIteration(demo.head));

        System.out.println("list = " + demo2.deleteDuplicationAll(demo2.head));
        System.out.println();
    }
}
