import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MirrorSolution {

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
        pHead = new TreeNode(8);
        pHead.left = new TreeNode(6);
        pHead.right = new TreeNode(10);
        pHead.left.left = new TreeNode(5);
        pHead.left.right = new TreeNode(7);
        pHead.right.left = new TreeNode(9);
        pHead.right.right = new TreeNode(11);
        return pHead;
    }

    public void printTree(TreeNode head){
        System.out.println("Binary Tree:");
        pringInOrder(head, 0, "H", 17);
        System.out.println();
    }

    private void pringInOrder(TreeNode head, int height, String to, int len) {
        if (head == null){
            return;
        }
        pringInOrder(head.right, height+1, "v", len);
        String val = to + head.val + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height*len) + val);
        pringInOrder(head.left, height+1, "^", len);

    }

    private String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }


    // 方法1：递归
    // 求一棵树的镜像的过程：
    // 先前序遍历这棵树的每个结点，如果遍历到的结点有子节点，就交换它的两个子节点。当交换完所有非叶子节点的左、右子节点之后，就得到了树的镜像。
    public void Mirror1(TreeNode root) {
        if (root == null){
            return;
        }

        // 叶子结点不需要交换，自身就是对称的
        if (root.left == null && root.right == null){
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.left != null){
            Mirror1(root.left);
        }
        if (root.right != null){
            Mirror1(root.right);
        }
    }


    // 方法2：非递归。前序遍历使用队列，左右子树遍历顺序无关，故队列和栈都可以
    public void Mirror2(TreeNode root) {
        if (root == null){
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();

            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
    }


    public static void main(String[] args) {
        MirrorSolution mirrorSolution = new MirrorSolution();
        mirrorSolution.pHead = mirrorSolution.constructTree();
//        mirrorSolution.Mirror1(mirrorSolution.pHead);
         mirrorSolution.Mirror2(mirrorSolution.pHead);


        mirrorSolution.printTree(mirrorSolution.pHead);
    }

}
