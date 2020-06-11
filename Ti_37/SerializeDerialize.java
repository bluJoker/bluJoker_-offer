import java.util.LinkedList;
import java.util.Queue;

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
