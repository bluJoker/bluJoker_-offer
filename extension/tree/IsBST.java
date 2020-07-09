package tree;

import java.util.ArrayList;

/**
 * 成为二叉查找树的条件：所有左边的结点必须小于或等于当前结点，而当前结点必须小于所有右边的结点。
 * */
public class IsBST {

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
        pHead = new TreeNode(8);
        pHead.left = new TreeNode(6);
        pHead.right = new TreeNode(10);

        pHead.left.left = new TreeNode(5);
        pHead.left.right = new TreeNode(7);
        pHead.right.left = new TreeNode(9);
        pHead.right.right = new TreeNode(11);

        return pHead;
    }


    // 解法1：树形dp套路，后序遍历
    public class ReturnType {
        public boolean isBST;
        public int max;
        public int min;

        public ReturnType(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public boolean checkBST1(TreeNode root) {

        if (root == null) {
            return false;
        }
        return process(root).isBST;
    }

    public ReturnType process(TreeNode root) {

        if (root == null) {
            return new ReturnType(true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ReturnType leftData = process(root.left);
        ReturnType rightData = process(root.right);


        int max = Math.max(Math.max(leftData.max, rightData.max), root.val);
        int min = Math.min(Math.min(leftData.min, rightData.min), root.val);

        boolean isBST = leftData.isBST && rightData.isBST && (leftData.max < root.val && root.val < rightData.min);

        return new ReturnType(isBST, max, min);
    }

    // 解法2：解法1翻版，不用ReturnType，前序遍历。详解见程序员面试金典P153
    // 成为二叉查找树的条件：所有左边的结点必须小于或等于当前结点，而当前结点必须小于所有右边的结点。
    // 利用这一点，我们可以通过自上而下传递最小和最大值来解决这个问题。在迭代遍历整个树的过程中，我们会用逐渐变窄的范围来检查各个结点。
    // 以下面这棵树为例：

//              20
//            /   \
//           10     30
//          /  \
//         5   15
//        / \    \
//       3   7    17

    // 首先，从（min=MIN_VALUE，max=MAX_VALUE）这个范围开始，根节点20显然落入其中。然后处理左子树（值为10的结点），检查这些结点是否落在
    // （min=MIN_VALUE，max=20）范围内。然后再处理右子树（值为30的结点），检查结点是否落在（min=20，max=MAX_VALUE）范围内。
    // 然后依此遍历整棵树。进入左子树时，更新max。进入右子树时，更新min。只要有任一结点不能通过检查，则停止并返回false。


    public boolean checkBST2(TreeNode root) {
        if (root == null){
            return false;
        }
        return checkBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean checkBST2(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.val < min || root.val >= max) {
            return false;
        }

        if (!checkBST2(root.left, min, root.val) ||
                !checkBST2(root.right, root.val, max)) {
            return false;
        }
        return true;
    }

    // 解法3：中序遍历。唯一问题在于，无法正确处理树中的重复值: 详解见程序员面试金典P153
    // Valid_BST   [20.left = 20]
    // Invalid_BST [20.right = 20]
    public boolean checkBST3(TreeNode root) {

        ArrayList<Integer> resList = new ArrayList<>();
        copyBSTInOrder(root, resList);
        for (int i = 1; i < resList.size(); i++) {
            if (resList.get(i) <= resList.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void copyBSTInOrder(TreeNode root, ArrayList<Integer> resList) {
        if (root == null) {
            return;
        }
        copyBSTInOrder(root.left, resList);
        resList.add(root.val);
        copyBSTInOrder(root.right, resList);
    }

    // 解法4：解法3改进，解法3中的数组实无必要。除了用来比较某个元素和前一个元素，别无他用。那么，为什么不在进行比较时，直接记下最后的元素？
    public int last_printed = Integer.MIN_VALUE;

    public boolean checkBST4(TreeNode root){
        if (root == null){
            return true;
        }

        // 递归检查左子树
        if (!checkBST4(root.left)){
            return false;
        }

        // 检查当前结点，last_printed为遍历左子树的最后结点，此处比较和解法3的最后比较相同
        if (root.val <= last_printed){
            return false;
        }
        last_printed = root.val;

        // 递归检查右子树
        if (!checkBST4(root.right)){
            return false;
        }

        return true; // 全部检查完毕
    }



    public static void main(String[] args) {
        IsBST isBST = new IsBST();
        isBST.pHead = isBST.constructTree();
        System.out.println(isBST.checkBST1(isBST.pHead));
        System.out.println(isBST.checkBST2(isBST.pHead));
        System.out.println(isBST.checkBST3(isBST.pHead));
        System.out.println(isBST.checkBST4(isBST.pHead));

    }
}
