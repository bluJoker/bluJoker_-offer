public class Merge {
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

    // 合并两个排序的链表
    // 递归：
    public static ListNode MergeRecursion(ListNode list1,ListNode list2) {
        ListNode mergeList = null;
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }

        if (list1.val < list2.val){
            mergeList = list1;
            mergeList.next = MergeRecursion(list1.next, list2);
        }else {
            mergeList = list2;
            mergeList.next = MergeRecursion(list1, list2.next);    // 2. 返回的较小者挂在上一层递归的结点的next，用来构建新链表
        }
        return mergeList;                                          // 1. 将较小者返回
    }

    // 迭代
    public ListNode MergeIteration(ListNode list1,ListNode list2) {
        // 1、初始有链表为空
        if (list1 == null){
            return  list2;
        }
        if (list2 == null){
            return list1;
        }

        // 2、mergeList作为遍历用的结点，dummy为要返回的头结点
        ListNode mergeList = new ListNode(-1);
        ListNode dummy = mergeList;

        // 3、遍历直到某个链表为空
        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                mergeList.next = list1;
                list1 = list1.next;
                mergeList = mergeList.next;
            }else {
                mergeList.next = list2;
                list2 = list2.next;
                mergeList = mergeList.next;
            }
        }

//        if (list1 == null){
//            while (list2 != null){
//                mergeList.next = list2;
//                list2 = list2.next;
//                mergeList = mergeList.next;
//            }
//        }
//
//        if (list2 == null){
//            while (list1 != null){
//                mergeList.next = list1;
//                list1 = list1.next;
//                mergeList = mergeList.next;
//            }
//        }

        // 4、遍历仅剩的一条链表
        // PS: 运行到此处不需要加if判断，因为肯定至少有一个链表已为null
        while (list1 != null){
            mergeList.next = list1;
            list1 = list1.next;
            mergeList = mergeList.next;
        }

        while (list2 != null){
            mergeList.next = list2;
            list2 = list2.next;
            mergeList = mergeList.next;
        }

        return dummy.next;
    }

    public static void main(String[] args){

        Merge list1 = new Merge();
        Merge list2 = new Merge();

        list1.add(1);
        list1.add(9);
        list1.add(19);
        list1.add(55);

        list2.add(5);
        list2.add(8);
        list2.add(39);
        list2.add(53);
        list2.add(59);
        list2.add(100);

        // 迭代
        ListNode merge = list1.MergeIteration(list1.head, list2.head);

//        递归
//        ListNode merge = list1.MergeRecursion(list1.head, list2.head);

        System.out.println(" = ");
    }
}
