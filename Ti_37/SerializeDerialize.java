import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 方法1：Ti_7 可以从前序序列和中序序列重建二叉树。故可以先把一棵二叉树序列化成一个前序遍历序列和一个中序遍历序列，然后在反序列化时通过这两个序列
 * 重构出原二叉树
 *
 * PS：这种思路有两个缺点：一是该方法要求二叉树中不能有数值重复的节点；
 * 二是只有当两个序列中所有数据都读出后才能开始反序列化。如果两个遍历序列的数据是从一个流里读出来的，那么可能需要等待较长的时间。
 *
 *
 *
 * 方法2：前序+null+分隔符
 * 如果二叉树的序列化是从根节点开始的，那么相应的反序列化在根节点的数值读出来的时候就可以开始了。
 *
 * 递归图：
 * 		         12
 * 		       /    \                序列化：12! 3! #! #! #!
 *           3     null
 * 	       /   \
 *      null  null
 *
 * 	 queue(12, 3, #, #, #)
 *   head=(12)
 *   head.left=Deserialize(queue)--->queue(3, #, #, #)
 *    					             head=(3)
 *        							 head.left=Deserialize(queue)--->queue(#, #, #)
 *     													             queue.poll().equals("#")
 *   							    		  =null			     <---return null
 * 									 head.right=Deserialize(queue)--->queue(#, #)
 * 																	  queue.poll().equals("#")
 *     										   =null			  <---return null
 *    																  return 3		    3
 *      																			  /   \
 * 																	                null  null
 *   head.right=Deserialize(queue)--->queue(#)
 *     								  queue.poll().equals("#")
 * 		       =null			  <---return null
 *   return 12	   12
 * 		         /    \
 * 			    3     null
 * 			  /   \
 * 		   null  null
 *
 * 使用queue，这样每次递归可以取出一个结点做操作，不需要使用数组并用下标指示，简化了。
 *
 *
 * */

public class SerializeDerialize {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode pHead;

    public TreeNode constructTree(){
        pHead = new TreeNode(1);
        pHead.left = new TreeNode(2);
        pHead.right = new TreeNode(3);
        pHead.left.left = new TreeNode(4);
        pHead.right.left = new TreeNode(5);
        pHead.right.right = new TreeNode(6);
        return pHead;
    }

    String serialize(TreeNode root) {
        if (root == null){
            return "#,";
        }
        String res = root.val + ",";
        res += serialize(root.left);
        res += serialize(root.right);
        return res;
    }


    TreeNode deserialize(String str) {
        String[] values = str.split(",");
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i < values.length; i++) {
            queue.offer(values[i]);
        }
        return deserialize(queue);
    }
    TreeNode deserialize(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")){
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(value));
        head.left = deserialize(queue);
        head.right = deserialize(queue);
        return head;
    }

    public static void main(String[] args) {
        SerializeDerialize treeJZ = new SerializeDerialize();
        treeJZ.pHead = treeJZ.constructTree();
        String str = treeJZ.serialize(treeJZ.pHead);

        TreeNode head1 = treeJZ.deserialize(str);

        System.out.println(str);
    }
}
