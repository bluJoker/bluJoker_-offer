
/**
 * 递归终止条件为：
 * 1、在数组序列的右子树对应序列中查找是否有比root小点的值，有则返回false。左子树肯定都比root大，因为我们在第一个for循环使得i为第一个大于root的结点
 * 2、当递归到只有一个元素时，只包含一个元素的子数组为二叉搜索树，故返回true
 * if (lo >= hi){
 *       return true;
 * }
 * 3、返回左右子树递归的&&结果，表示所有都返回true最终结果才为true，如果有某一子树不符合条件（右子树有比root小的结点），则最终结果就为false
 * returnVerifySquenceOfBST(sequence, lo, i-1) &&
 *          VerifySquenceOfBST(sequence, i, hi-1);
 *
 * */
public class VerifySquenceOfBSTSolution {

    public boolean verifySquenceOfBST(int[] sequence) {
        if (sequence == null) {
            return false;
        }
        return verifySquenceOfBST(sequence, 0, sequence.length - 1);
    }

    private boolean verifySquenceOfBST(int[] sequence, int lo, int hi) {
        if (sequence.length == 0){
            return false;
        }

        if (lo >= hi) {
            return true;
        }

        int root = sequence[hi];

        int i = lo;
        for (; i < hi; i++) {
            if (sequence[i] > root) {
                break;
            }
        }

        for (int j = i; j < hi; j++) {
            if (sequence[j] < root) {
                return false;
            }
        }

        return verifySquenceOfBST(sequence, lo, i - 1) &&
                verifySquenceOfBST(sequence, i, hi - 1);
    }

    public static void main(String[] args) {
        int[] sequence = {5, 11, 7, 9, 6, 10, 8};
        VerifySquenceOfBSTSolution verifySquenceOfBSTSolution = new VerifySquenceOfBSTSolution();
        System.out.println(verifySquenceOfBSTSolution.verifySquenceOfBST(sequence));
    }
}
