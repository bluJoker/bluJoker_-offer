public class InversePairsSolution {

    // 归并排序思想
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