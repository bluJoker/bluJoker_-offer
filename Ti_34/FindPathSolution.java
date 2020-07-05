import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;

/**
 *                    10
 *                  /   \
 *                 5    12
 *                / \
 *               4   7
 *
 *  target = 22
 *
 *  输出：[
 *         [10, 5, 7],
 *         [10, 12]
 *       ]
 *
 * 1. 由于路径是从根节点出发到叶节点，也就是说路径总是以根节点为起始点，因此首先需要遍历根节点，故使用前序遍历
 *
 * 2. 当前节点访问结束后，递归函数将自动回到它的父节点。因此，在函数退出之前要在路径上删除当前节点并减去当前节点的值，
 *    以确保返回父节点时路径刚好是从根节点到父节点。
 *    我们不难看出保存路径的数据结构实际上是一个栈，因为路径要与递归调用状态一致，而递归调用的本质就是一个压栈和出栈的过程。
 *
 * 3. 但不能直接使用Stack，因为打印路径的时候需要得到路径上的所有节点，故用ArrayList作为栈比较合适
 *
 * */
public class FindPathSolution {

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
        pHead = new TreeNode(10);
        pHead.left = new TreeNode(5);
        pHead.right = new TreeNode(12);

        pHead.left.left = new TreeNode(4);
        pHead.left.right = new TreeNode(7);

        return pHead;
    }

    //ArrayList<ArrayList<Integer>>
    public void findPath(TreeNode root, int target) {

        ArrayList<Integer> pathList = new ArrayList<>();

        findPath(root, target, 0, pathList);
    }


    public void findPath(TreeNode root, int target, int sum, ArrayList<Integer> pathList) {

        pathList.add(root.val);

        sum += root.val;

        if (root.left == null && root.right == null && sum == target){
            System.out.println(pathList);
        }

        if (root.left != null){
            findPath(root.left, target, sum, pathList);
        }

        if (root.right != null){
            findPath(root.right, target, sum, pathList);
        }

        // 1.当前节点访问结束后，递归函数将自动回到它的父节点。因此，在函数退出之前要在路径上删除当前节点并减去当前节点的值，
        // 以确保返回父节点时路径刚好是从根节点到父节点。
        // 2.我们不难看出保存路径的数据结构实际上是一个栈，因为路径要与递归调用状态一致，而递归调用的本质就是一个压栈和出栈的过程。
        // 3.但不能直接使用Stack，因为打印路径的时候需要得到路径上的所有节点，故用ArrayList作为栈比较合适
        pathList.remove(pathList.size()-1);
    }


    public static void main(String[] args) {
        FindPathSolution findPathSolution = new FindPathSolution();
        findPathSolution.pHead = findPathSolution.constructTree();
        findPathSolution.findPath(findPathSolution.pHead, 22);
    }
}
