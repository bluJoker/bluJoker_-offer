/**
 *
 *                    1
 *                 /     \
 *               2        3
 *              /       /  \
 *            4        5    6
 *             \           /
 *              7         8
 *
 *    preOrder: 1, 2, 4, 7, 3, 5, 6, 8
 *    inOrder:  4, 7, 2, 1, 5, 3, 8, 6
 *
 *  reConstructBinaryTree1()实现没有考虑特殊输入测试：输入的前序遍历序列和中序遍历序列不匹配
 *  正确实现为：reConstructBinaryTree2()
 *
 * */

public class ConstructBinaryTreeDemo {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode root;

    public TreeNode reConstructBinaryTree1(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        return reConstructBinaryTree1(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode reConstructBinaryTree1(int[] pre, int startPre, int endPre,
                                            int[] in, int startIn, int endIn) {

//        if (startPre >= endPre || startIn >= endIn) {
//            return null;
//        }
        if (startPre > endPre || startIn > endIn) {
            return null;
        }

        TreeNode node = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= endIn; i++) {
            if (pre[startPre] == in[i]) {

                node.left = reConstructBinaryTree1(pre, startPre + 1, i - startIn + startPre,
                        in, startIn, i - 1);

                node.right = reConstructBinaryTree1(pre, startPre + i - startIn + 1, endPre,
                        in, i + 1, endIn);
            }
        }
        return node;
    }


    public TreeNode reConstructBinaryTree2(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null) {
            return null;
        }
        return reConstructBinaryTree2(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
    }

    private TreeNode reConstructBinaryTree2(int[] preOrder, int startPre, int endPre,
                                            int[] inOrder, int startIn, int endIn) {

        TreeNode node = new TreeNode(preOrder[startPre]);

//        if (startPre == endPre && startIn == endIn){
//            return node;
//        }else {
//            throw new IllegalArgumentException("invalid params");
//        }

        // 如果遍历到前序只剩一个且后序也只剩一个，并且对应的数值相等，则该节点为叶子结点，返回该节点即可
        // 例如结点7：startPre == endPre == 3; startIn == endIn == 1; preOrder[3] == inOrder[1] == 7
        if (startPre == endPre){
            if (startIn == endIn && preOrder[startPre] == inOrder[startIn]){
                return node;
            }else {
                throw new IllegalArgumentException("invalid params1");
            }
        }

        // 中序遍历序列中找根结点
        int rootInorder = startIn;
        while (rootInorder <= endIn && inOrder[rootInorder] != preOrder[startPre]){
            rootInorder++;
        }

        // 遍历到了endIn，且该值也不等于preOrder[startPre]，则说明中序遍历序列中找不到前序序列对应的根结点。说明输入序列不对
        if (rootInorder == endIn && inOrder[rootInorder] != preOrder[startPre]){
            throw new IllegalArgumentException("invalid params2");
        }

        int leftLength = rootInorder - startIn;
        int leftPreEnd = startPre + leftLength; // 数结点总数

        if (leftLength > 0){
               node.left = reConstructBinaryTree2(preOrder, startPre+1, leftPreEnd,
                    inOrder, startIn, rootInorder-1);
        }

        // leftLength：左子树结点数
        // endPre - startPre：树结点总数
        // leftLength < endPre - startPre 等价于右子树结点数目 > 0
        if (leftLength < endPre - startPre){
            node.right = reConstructBinaryTree2(preOrder, leftPreEnd+1, endPre,
                    inOrder, rootInorder+1, endIn);
        }
        return node;
    }


    public static void main(String[] args) {
//        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
//        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        // 前序和中序不匹配。返回结果root{1, null, null}，是不是应该返回null?
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 1, 2, 5, 3, 8, 6};

        ConstructBinaryTreeDemo demo = new ConstructBinaryTreeDemo();
        demo.root = demo.reConstructBinaryTree2(pre, in);

        System.out.println("demo.root.val = " + demo.root.val);
    }
}

