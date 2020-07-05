import java.util.LinkedList;
import java.util.Queue;

/**
* ======================================================================================================================================================================
 * 方法1递归图解：
 *
 *           8
 * 		   /   \
 * 		 6      14
 * 		/ \    /  \
 *     5   7  12   16
 *
 *
 * 						head=8
 * 						leftList=process(8.left=6)--->head=6
 * 													  leftList=process(6.left=5)--->head=5
 * 																					leftList=process(5.left=null)--->head=null
 * 																							=(null, null)        <---return new ReturnType(null, null);
 * 																					rightList = process(5.right=null)--->head=null
 * 																							  =(null, null)          <---return new ReturnType(null, null);
 * 																					5.left=null;
 * 																					5.right=null;
 * 															  =(5, 5)			    <---return new ReturnType(head=5,head=5)
 *
 * 													  rightList=process(6.right=7)--->head=7
 * 																				  <---同上，return new ReturnType(head=7,head=7)
 * 													  leftList.end.right = head; 5.right=6;    5---》6
 * 													  6.left=leftList.end=5;                   5《---》6
 * 													  6.right=rightList.start=7;               5《---》6---》7
 * 				(1)		   						      rightList.start.left = head; 7.left=6;   5《---》6《---》7
 * 													  return new ReturnType(leftList.start=5, rightList.end=7);
 * 								=(5, 7)
 * 						rightList=process(8.right=14)--->head=14
 * 														 ......
 * 														 leftList=(12,12)
 * 														 rightList=(16,16)
 * 														 leftList.end.right = head; 12.right=14;    12---》14
 * 														 14.left=leftList.end=12;                   12《---》14
 * 													     14.right=rightList.start=16;               12《---》14---》16
 * 				(2)									     rightList.start.left = head; 16.left=14;   12《---》14《---》16
 * 								=(12, 16)		     <---return new ReturnType(leftList.start=12, rightList.end=16);
 * 						leftList.end.right = head; 7.right=8;    7---》8
 * 						8.left=leftList.end=7;                   7《---》8
 * 						8.right=rightList.start=12;              7《---》8---》12
 * 				(3)		rightList.start.left = head; 12.left=8;  7《---》8《---》12
 * 						return new ReturnType(leftList.start=5, rightList.end=16);
 *
 * 						由(1)(2)(3)即得结果：
 * 						5《---》6《---》7《---》8《---》12《---》14《---》16
 * */
public class TreeToLinkedList {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode pHead;

    // 查找树
    public void put(int e){
        pHead = put(pHead, e);
    }
    private TreeNode put(TreeNode node, int e){
        if (node == null){
            return new TreeNode(e);
        }

        if (e < node.val){
            node.left = put(node.left, e);
        }else if (e > node.val){
            node.right = put(node.right, e);
        }else {}
        return node;
    }

    // =================================================================================================================
    // 方法1：树形dp套路。时间复杂度O(N)，额外空间复杂度O(h)，h为二叉树的高度。
    // 可以用process递归函数发生的次数来估算时间复杂度，process会处理所有的子树，子树的数量就是二叉树节点的个数。所以时间复杂度O(N)。
    // process递归函数最多占用二叉树高度为h的栈空间，所以额外空间复杂度O(h)。
    private class ReturnType{
        TreeNode start;
        TreeNode end;

        public ReturnType(TreeNode start, TreeNode end) {
            this.start = start;
            this.end = end;
        }
    }

    public TreeNode convert1(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        return process(pRootOfTree).start;
    }

    private ReturnType process(TreeNode head){

        if (head == null){
            return new ReturnType(null, null);
        }

        ReturnType leftList = process(head.left);
        ReturnType rightList = process(head.right);

        // =================连接链表start===================
        if (leftList.end != null){
            leftList.end.right = head;
        }

        head.left = leftList.end;
        head.right = rightList.start;

        if (rightList.start != null){
            rightList.start.left = head;
        }
        // =================连接链表end=====================

        return new ReturnType(leftList.start != null ? leftList.start : head,
                rightList.end != null ? rightList.end : head);
    }


    // =================================================================================================================
    // 方法2：中序遍历。用队列等容器收集二叉树中序遍历结果的方法。
    // 时间复杂度O(N)，额外空间复杂度O(N)，具体过程如下：
    // 1. 生成一个队列，记为queue，按照二叉树中序遍历的顺序，将每个节点放入queue中。
    // 2. 从queue中依次弹出节点，并按照弹出的顺序重连所有的节点即可。
    public TreeNode convert2(TreeNode head){
        Queue<TreeNode> queue = new LinkedList<>();
        inOrderToQueue(head, queue);

        head = queue.poll();
        TreeNode prev = head;

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();

            prev.right = node;
            node.left = prev;

            prev = node;
        }
        return head;
    }

    private void inOrderToQueue(TreeNode head, Queue<TreeNode> queue){
        if (head == null){
            return;
        }

        inOrderToQueue(head.left, queue);
        queue.offer(head);
        inOrderToQueue(head.right, queue);
    }


    // -----------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        TreeToLinkedList treeJZ = new TreeToLinkedList();

        treeJZ.put(8);
        treeJZ.put(6);
        treeJZ.put(14);
        treeJZ.put(5);
        treeJZ.put(7);
        treeJZ.put(12);
        treeJZ.put(16);
//        TreeNode head1 = treeJZ.convert1(treeJZ.pHead);
        TreeNode head2 = treeJZ.convert2(treeJZ.pHead);

        System.out.println();
    }
}
