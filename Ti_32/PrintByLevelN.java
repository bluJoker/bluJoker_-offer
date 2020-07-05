import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PrintByLevelN {

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
        pHead.left.right = new TreeNode(5);
        pHead.right.left = new TreeNode(6);
        pHead.right.right = new TreeNode(7);

        pHead.left.left.left = new TreeNode(8);
        pHead.left.left.right = new TreeNode(9);
        pHead.left.right.left = new TreeNode(10);
        pHead.left.right.right = new TreeNode(11);

        pHead.right.left.left = new TreeNode(12);
        pHead.right.left.right = new TreeNode(13);
        pHead.right.right.left = new TreeNode(14);
        pHead.right.right.right = new TreeNode(15);

        return pHead;
    }

    // 1. 按层打印成一行
    public void Print1(TreeNode pRoot) {
        if (pRoot == null){
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);

        TreeNode last = pRoot;
        TreeNode nLast = null;

        while (!queue.isEmpty()){
            pRoot = queue.poll();

            System.out.print(pRoot.val + " ");
            if (pRoot.left != null){
                queue.offer(pRoot.left);
                nLast = pRoot.left;
            }
            if (pRoot.right != null){
                queue.offer(pRoot.right);
                nLast = pRoot.right;
            }

            if (pRoot == last){
                System.out.println();
                last = nLast;
            }
        }
    }


    // 2. 按层打印成多行
    // last变量表示正在打印的当前行的最右节点；nLast表示下一行的最右节点
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();

        if (pRoot == null){
            return arrayLists;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);

        TreeNode last = pRoot;
        TreeNode nLast = null;
        ArrayList<Integer> arrayList = new ArrayList<>();

        while (!queue.isEmpty()){
            pRoot = queue.poll();

//            System.out.print(pRoot.val + " ");
            arrayList.add(pRoot.val);

            // 只要让nLast一直跟踪记录宽度优先队列中的最新加入的节点即可。这是因为最新加入队列的节点一定是目前已经发现的下一行的最右节点
            if (pRoot.left != null){
                queue.offer(pRoot.left);
                nLast = pRoot.left;
            }
            if (pRoot.right != null){
                queue.offer(pRoot.right);
                nLast = pRoot.right;
            }

            // 如果发现遍历到的节点等于last，即pRoot到当前行的最右节点，此时应该换行。
            if (pRoot == last){
                arrayLists.add(arrayList);
                arrayList = new ArrayList<>();

                // 换行之后，只要令last = nLast 就可以继续下一行的打印过程
                last = nLast;
            }
        }
        return arrayLists;
    }


    // 3. 之字形打印
    public void PrintZ(TreeNode pRoot) {
        if (pRoot == null){
            return;
        }

        Stack<TreeNode>[] stacks = new Stack[2];
        stacks[0] = new Stack<>();
        stacks[1] = new Stack<>();

        int current = 0;
        int next = 1;

        stacks[current].push(pRoot);

        while (!stacks[0].isEmpty() || !stacks[1].isEmpty()){

            TreeNode node = stacks[current].pop();
            System.out.print(node.val + "  ");

            if (current == 0){
                if (node.left != null){
                    stacks[next].push(node.left);
                }
                if (node.right != null){
                    stacks[next].push(node.right);
                }
            }else {
                if (node.right != null){
                    stacks[next].push(node.right);
                }
                if (node.left != null){
                    stacks[next].push(node.left);
                }
            }

            // 当一层所有节点都打印完毕时，交换这两个栈并继续打印下一层
            if (stacks[current].isEmpty()){
                System.out.println();
                current = 1- current;
                next = 1-next;
            }
        }
    }


    public static void main(String[] args) {
        PrintByLevelN printByLevelN = new PrintByLevelN();
        printByLevelN.pHead = printByLevelN.constructTree();
//        printByLevelN.Print1(printByLevelN.pHead);

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        arrayLists = printByLevelN.Print(printByLevelN.pHead);
        System.out.println("arrayLists = " + arrayLists);

        printByLevelN.PrintZ(printByLevelN.pHead);
    }
}
