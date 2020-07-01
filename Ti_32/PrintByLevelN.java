import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
        pHead.right.left = new TreeNode(5);
        pHead.right.right = new TreeNode(6);

        pHead.right.left.left = new TreeNode(7);
        pHead.right.left.right = new TreeNode(8);

        return pHead;
    }

    // 按层打印成多行
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

    // 按层打印成一行
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


    public static void main(String[] args) {
        PrintByLevelN printByLevelN = new PrintByLevelN();
        printByLevelN.pHead = printByLevelN.constructTree();
//        printByLevelN.Print1(printByLevelN.pHead);

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        arrayLists = printByLevelN.Print(printByLevelN.pHead);
        System.out.println("arrayLists = " + arrayLists);
    }
}
