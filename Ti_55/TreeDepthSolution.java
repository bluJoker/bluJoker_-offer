import java.util.LinkedList;
import java.util.Queue;

public class TreeDepthSolution {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public TreeNode pHead;

    public TreeNode constructTree() {
        pHead = new TreeNode(1);
        pHead.left = new TreeNode(2);
        pHead.right = new TreeNode(3);
        pHead.left.left = new TreeNode(4);
        pHead.left.right = new TreeNode(5);
        pHead.left.right.left = new TreeNode(7);
        pHead.right.right = new TreeNode(6);
        return pHead;
    }

    // 方法一：递归
    public int TreeDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = TreeDepth1(root.left);
        int rightDepth = TreeDepth1(root.right);
        return (leftDepth > rightDepth) ? (leftDepth + 1) : (rightDepth + 1);
    }

    // 后序遍历
//    public void AfterPrint(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//
//        AfterPrint(root.left);
//        AfterPrint(root.right);
//        System.out.print(root.val + "-");
//    }

    // 方法二：非递归，分层遍历
    public int TreeDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        TreeNode last = root;
        TreeNode nLast = null;
        int height = 0;

        while (!queue.isEmpty()){
            root = queue.poll();

            if (root.left != null){
                queue.offer(root.left);
                nLast = root.left;
            }
            if (root.right != null){
                queue.offer(root.right);
                nLast = root.right;
            }

            if (root == last){
                height++;
                last = nLast;
            }
        }
        return height;
    }

    public static void main(String[] args) {
        TreeDepthSolution treeDepthSolution = new TreeDepthSolution();
        treeDepthSolution.constructTree();
        System.out.println(treeDepthSolution.TreeDepth1(treeDepthSolution.pHead));
        System.out.println(treeDepthSolution.TreeDepth2(treeDepthSolution.pHead));

//        treeDepthSolution.AfterPrint(treeDepthSolution.pHead);
    }
}
