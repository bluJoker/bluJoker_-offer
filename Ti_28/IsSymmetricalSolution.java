
/**
 * pHead3:
 *                  7
 *               /     \
 *              7      7
 *            /  \    /
 *           7   7   7
 *
 *   该树的前序遍历序列和前序遍历的对称序列均为{7, 7, 7, 7, 7, 7}
 *   只要在遍历的时候把遇到的null节点也考虑进来就行了
 *
 *   加上null节点后的二叉树pHead3:
 *                       7
 *                 /          \
 *               7             7
 *           /      \       /   \
 *         7         7     7     n
 *       /   \     /  \   / \
 *      n    n    n   n  n   n
 *
 *   前序遍历序列：{7, 7, 7, n, n, 7, n, n, 7, 7, n, n, n}
 *   前序对称序列：{7, 7, n, 7, n, n, 7, 7, n, n, 7, n, n}
 *
 * */
public class IsSymmetricalSolution {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    public TreeNode pHead1;

    public TreeNode constructTree1(){
        pHead1 = new TreeNode(8);
        pHead1.left = new TreeNode(6);
        pHead1.right = new TreeNode(6);
        pHead1.left.left = new TreeNode(5);
        pHead1.left.right = new TreeNode(7);
        pHead1.right.left = new TreeNode(7);
        pHead1.right.right = new TreeNode(5);
        return pHead1;
    }

    public TreeNode pHead2;

    public TreeNode constructTree2(){
        pHead2 = new TreeNode(8);
        pHead2.left = new TreeNode(6);
        pHead2.right = new TreeNode(9);
        pHead2.left.left = new TreeNode(5);
        pHead2.left.right = new TreeNode(7);
        pHead2.right.left = new TreeNode(7);
        pHead2.right.right = new TreeNode(5);
        return pHead2;
    }

    public TreeNode pHead3;

    public TreeNode constructTree3(){
        pHead3 = new TreeNode(7);
        pHead3.left = new TreeNode(7);
        pHead3.right = new TreeNode(7);
        pHead3.left.left = new TreeNode(7);
        pHead3.left.right = new TreeNode(7);
        pHead3.right.left = new TreeNode(7);
        pHead3.right.right = null;
        return pHead3;
    }

    boolean isSymmetrical(TreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    // 同一个节点分别做前序遍历和对称的前序遍历
    private boolean isSymmetrical(TreeNode pRoot1, TreeNode pRoot2) {
        if (pRoot1 == null && pRoot2 == null){
            return true;
        }

        // 考虑null节点。pRoot1和PRoot2仅有一个为空
        if (pRoot1 == null || pRoot2 == null){
            return false;
        }
        if (pRoot1.val != pRoot2.val){
            return false;
        }

        return isSymmetrical(pRoot1.left, pRoot2.right) &&
                isSymmetrical(pRoot1.right, pRoot2.left);

    }

    public static void main(String[] args) {
        IsSymmetricalSolution isSymmetricalSolution = new IsSymmetricalSolution();
        isSymmetricalSolution.pHead1 = isSymmetricalSolution.constructTree1();
        isSymmetricalSolution.pHead2 = isSymmetricalSolution.constructTree2();
        isSymmetricalSolution.pHead3 = isSymmetricalSolution.constructTree3();

        System.out.println(isSymmetricalSolution.isSymmetrical(isSymmetricalSolution.pHead1));
        System.out.println(isSymmetricalSolution.isSymmetrical(isSymmetricalSolution.pHead2));
        System.out.println(isSymmetricalSolution.isSymmetrical(isSymmetricalSolution.pHead3));

    }

}
