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

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre,
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

                node.left = reConstructBinaryTree(pre, startPre + 1, i - startIn + startPre,
                        in, startIn, i - 1);

                node.right = reConstructBinaryTree(pre, startPre + i - startIn + 1, endPre,
                        in, i + 1, endIn);
            }
        }
        return node;
    }


    public static void main(String[] args) {
//        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
//        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        // 前序和中序不匹配。返回结果root{1, null, null}，是不是应该返回null?
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 9, 5, 3, 8, 6};

        ConstructBinaryTreeDemo demo = new ConstructBinaryTreeDemo();
        demo.root = demo.reConstructBinaryTree(pre, in);

        System.out.println(" ");
    }
}

