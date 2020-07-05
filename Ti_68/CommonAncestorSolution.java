import java.util.ArrayList;
import java.util.Stack;

public class CommonAncestorSolution {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode parent = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public TreeNode pHead1;

    public TreeNode constructTree1() {
        pHead1 = new TreeNode(8);
        pHead1.left = new TreeNode(6);
        pHead1.right = new TreeNode(10);
        pHead1.left.left = new TreeNode(5);
        pHead1.left.right = new TreeNode(7);
        pHead1.right.left = new TreeNode(9);
        pHead1.right.right = new TreeNode(11);
        return pHead1;
    }


    public TreeNode pHead2;

    public TreeNode constructTree2() {
        pHead2 = new TreeNode(3);
        pHead2.left = new TreeNode(5);
        pHead2.left.parent = pHead2;
        pHead2.right = new TreeNode(1);
        pHead2.right.parent = pHead2;

        pHead2.left.left = new TreeNode(6);
        pHead2.left.left.parent = pHead2.left;
        pHead2.left.right = new TreeNode(2);
        pHead2.left.right.parent = pHead2.left;

        pHead2.left.right.left = new TreeNode(7);
        pHead2.left.right.left.parent = pHead2.left.right.left;
        pHead2.left.right.right = new TreeNode(4);
        pHead2.left.right.right.parent = pHead2.left.right.right;

        pHead2.right.left = new TreeNode(0);
        pHead2.right.right = new TreeNode(8);
        pHead2.right.left.parent = pHead2.right;
        pHead2.right.right.parent = pHead2.right;

        return pHead2;
    }

    // 1. 当该树为排序的二叉搜索树时：
    //
    // 从树的根节点出发遍历树，
    // (1) 如果当前节点都大于输入的两个节点，则下一步遍历当前节点的左子树
    // (2) 如果当前节点都小于输入的两个节点，则下一步遍历当前节点的右子树
    // (3) 一直遍历到当前节点比一个输入节点大而比另一个小，此时当前节点就是符合要求的最低公共祖先
    public TreeNode commonAncestor1(TreeNode head, TreeNode node1, TreeNode node2) {
        if (head == null) {
            return null;
        }

        if (head.val > node1.val && head.val > node2.val) {
            commonAncestor1(head.left, node1, node2);
        } else if (head.val < node1.val && head.val < node2.val) {
            commonAncestor1(head.right, node1, node2);
        } else {
            return head;
        }

        return null;
    }

    // 普通二叉树

    // 2. 树的节点中有指向父节点的指针，可以转换为寻找两个单向链表的第一个公共节点
    public TreeNode commonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || p == null || q == null) {
            return null;
        }

        int node1Depth = 1;
        TreeNode node1Tmp = p;
        while (p != root) {
            p = p.parent;
            node1Depth++;
        }

        int node2Depth = 1;
        TreeNode node2Tmp = q;
        while (q != root) {
            q = q.parent;
            node2Depth++;
        }

        int depthDiff = node1Depth - node2Depth;
        if (depthDiff > 0) {
            for (int i = 0; i < depthDiff; i++) {
                p = p.parent;
            }
        } else {
            for (int i = 0; i < depthDiff; i++) {
                q = q.parent;
            }
        }

        while (p != null && q != null) {
            if (p == q) {
                return p;
            }

            p = p.parent;
            q = q.parent;
        }

        return null;
    }

    // 不算长度，使用栈，从后往前找到第一个不同的节点的前一节点即为所求：
    public TreeNode commonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        // 注意写法，根节点也要入栈
        stack1.push(p);
        while (p != root) {
            p = p.parent;
            stack1.push(p);
        }

        stack2.push(q);
        while (q != root) {
            q = q.parent;
            stack2.push(q);
        }
        TreeNode prev = null;

        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            TreeNode node1 = stack1.pop();
            TreeNode node2 = stack2.pop();

            if (node1 != node2) {
                return prev;
            }

            prev = node1;
        }

        return null;
    }

    // 3. 树的节点中没有指向父节点的指针
    // 方法1：保存root到结点路径，然后求最后一个公共节点
    public TreeNode commonAncestor4(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        ArrayList<TreeNode> list1 = path(root, p);
        ArrayList<TreeNode> list2 = path(root, q);

        int i = 0;
        int iLast = 0;
        while (i < Math.min(list1.size(), list2.size())){

            if (list1.get(i) == list2.get(i)){
                iLast = i;
            }
            i++;
        }

        return list1.get(iLast);
    }


    private ArrayList<TreeNode> path(TreeNode root, TreeNode node) {

        ArrayList<TreeNode> list = new ArrayList<>();
        ArrayList<TreeNode> reslist = new ArrayList<>();
        if (root == null || node == null){
            return list;
        }

        path(root, node, list, reslist);
        return reslist;
    }

    private void path(TreeNode root, TreeNode node, ArrayList<TreeNode> list, ArrayList<TreeNode> reslist) {
        list.add(root);

        if (root == node) {
            reslist.addAll(list);
        }

        if (root.left != null) {
            path(root.left, node, list, reslist);
        }

        if (root.right != null) {
            path(root.right, node, list, reslist);
        }

        list.remove(list.size() - 1);
    }

    // 方法2：递归
    // TODO:
    public TreeNode commonAncestor5(TreeNode root, TreeNode p, TreeNode q){
        if (!covers(root, p) || !covers(root, q)){
            return null;
        }

        return commonAncestorHelper(root, p, q);
    }

    public boolean covers(TreeNode root, TreeNode p){
        if (root == null){
            return false;
        }
        if (root == p){
            return true;
        }
        return covers(root.left, p) ||covers(root.right, p);
    }

    public TreeNode commonAncestorHelper(TreeNode root, TreeNode p, TreeNode q){

        if (root == null){
            return null;
        }

        // 如果p、q有等于root，则root即为所求
        if (p == root || q == root){
            return root;
        }

        boolean is_p_on_left = covers(root.left, p);
        boolean is_q_on_left = covers(root.left, q);

        // 若p和q不在同一边，返回root
        if (is_p_on_left != is_q_on_left){
            return root;
        }

        // 否则就是在同一边，遍历那一边
        TreeNode child_size = is_p_on_left ? root.left : root.right;

        return commonAncestorHelper(child_size, p, q);
    }



    public static void main(String[] args) {
        CommonAncestorSolution commonAncestorSolution = new CommonAncestorSolution();
        // 二叉搜索树
        commonAncestorSolution.pHead1 = commonAncestorSolution.constructTree1();
        TreeNode ancestor1 = commonAncestorSolution.commonAncestor1(commonAncestorSolution.pHead1,
                commonAncestorSolution.pHead1.left.left, commonAncestorSolution.pHead1.right.left);
        System.out.println(ancestor1.val);


        commonAncestorSolution.pHead2 = commonAncestorSolution.constructTree2();

        // 普通二叉树
//        TreeNode ancestor2 = commonAncestorSolution.commonAncestor1(commonAncestorSolution.pHead2,
//                commonAncestorSolution.pHead2.left.left, commonAncestorSolution.pHead2.right.left);
//        System.out.println(ancestor2.val);

//        TreeNode ancestor22 = commonAncestorSolution.commonAncestor2(commonAncestorSolution.pHead2,
//                commonAncestorSolution.pHead2.left.left, commonAncestorSolution.pHead2.right.left);
//        System.out.println(ancestor22.val);

//        TreeNode ancestor3 = commonAncestorSolution.commonAncestor3(commonAncestorSolution.pHead2,
//                commonAncestorSolution.pHead2.left, commonAncestorSolution.pHead2.right);
//        System.out.println(ancestor3.val);


        // 测试某节点路径
        ArrayList<TreeNode> reslist = commonAncestorSolution.path(commonAncestorSolution.pHead2,
                commonAncestorSolution.pHead2.left.right.left);
        System.out.println();
        for (TreeNode node : reslist) {
            System.out.print(node.val + " ");
        }

        System.out.println();

        // 3. 树的节点中没有指向父节点的指针
        // 方法1
        TreeNode ancestor4 = commonAncestorSolution.commonAncestor4(commonAncestorSolution.pHead2,
                commonAncestorSolution.pHead2.left.left,
                commonAncestorSolution.pHead2.right.left);
        System.out.println(ancestor4.val);

        // 方法2

    }
}
