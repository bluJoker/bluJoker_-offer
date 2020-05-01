import java.util.ArrayList;
import java.util.Stack;

/**
 * 从尾到头打印链表
 * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
 *
 * 从头到尾故：使用栈
 * */
public class PrintListFromTailToHeadDemo {

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

    // 使用栈+迭代：
    public ArrayList<Integer> printListFromTailToHeadIteration(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        while (listNode != null){

            stack.push(listNode.val);
            listNode = listNode.next;
        }

        while (!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }

    public static ArrayList<Integer> listRecursion = new ArrayList<>();

    // 递归：
    public ArrayList<Integer> printListFromTailToHeadRecursion(ListNode listNode) {

        if (listNode != null){

            if (listNode.next != null){

                printListFromTailToHeadRecursion(listNode.next);
            }
            listRecursion.add(listNode.val);
        }

        return listRecursion;
    }

    public static void main(String[] args){

        PrintListFromTailToHeadDemo demo = new PrintListFromTailToHeadDemo();

        demo.add(1);
        demo.add(5);
        demo.add(3);
        demo.add(4);
        demo.add(7);

//        System.out.println("list = " + demo.printListFromTailToHeadIteration(demo.head));

        System.out.println("list = " + demo.printListFromTailToHeadRecursion(demo.head));
    }
}
