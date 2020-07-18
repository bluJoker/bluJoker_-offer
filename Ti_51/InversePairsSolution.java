public class InversePairsSolution {

    // 解法1：顺序扫描整个数组。每扫描到一个数字，逐个比较该数字和它后面的数字的大小。如果后面的数字比它小，
    // 则这两个数字就组成一个逆序对。假设数组中含有n个数字。由于每个数字都要和O(n)个数字进行比较，因此时间复杂度为O(n^2)


    // 解法2：每扫描到一个数字的时候，我们不能拿它和后面的每一个数字进行比较，否则时间复杂度就是O(n^2)。
    // 因此可以考虑先比较两个相邻的数字。
    // 过程：先把数组分隔成子数组，统计出子数组内部的逆序对的数目，然后再统计出两个相邻子数组之间的逆序对的数目。
    // 在统计逆序对的过程中，还需要对数组进行排序。
    // *** 以上其实是采用了归并排序思想。
    //     空间换时间：时间复杂度O(nlogn)，空间复杂度O(n)
    private static int[] aux;
    private static int sum;

    public int inversePairs(int[] array) {
        aux = new int[array.length];
        int sum = 0;
        return inversePairs(array, 0, array.length - 1);
    }

    private int inversePairs(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return 0;
        }

        int mid = lo + (hi - lo) / 2;
        inversePairs(array, lo, mid);
        inversePairs(array, mid + 1, hi);
        sum += pairsNum(array, lo, mid, hi);
        return sum;
    }

    private int pairsNum(int[] array, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = array[k];
        }

        int i = mid;
        int j = hi;
        int num = 0;

        // Tips：从后往前遍历填原数组，即从大到小，因为计算逆序对的比较也是从后往前
        for (int k = hi; k >= lo; k--) {
            if (i < lo) {
                array[k] = aux[j--];
            } else if (j < mid + 1) {
                array[k] = aux[i--];
            } else if (aux[i] > aux[j]) {
                // num需要累加
                num += j - mid;
                array[k] = aux[i--];
            } else {
                array[k] = aux[j--];
            }
        }
        return num;
    }

    public static void main(String[] args) {
        InversePairsSolution inversePairsSolution = new InversePairsSolution();
        int[] arr = {7,5,6,4};
        int[] arr1 = {1,2,3,4,5,6,7,0};
        System.out.println(inversePairsSolution.inversePairs(arr1));
    }
}