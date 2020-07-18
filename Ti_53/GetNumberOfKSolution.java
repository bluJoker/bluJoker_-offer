public class GetNumberOfKSolution {


    public int getFirstK(int[] array, int k) {
        int lo = 0;
        int hi = array.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (k == array[mid]) {
                // 如果前面的数字不是k，则不是第一个k，需要继续在前面找。当然如果此时mid已经是第一个元素，此时即就是第一个k
                if (mid == 0 || (mid > 0 && k != array[mid - 1])) {
                    return mid;
                } else {
                    hi = mid - 1;
                }
            } else if (k < array[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public int getLastK(int[] array, int k) {
        int lo = 0;
        int hi = array.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (k == array[mid]) {
                if (mid == hi || (mid + 1 <= hi && k != array[mid + 1])) {
                    return mid;
                } else {
                    lo = mid + 1;
                }
            } else if (k < array[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public int GetNumberOfK(int[] array, int k) {
        int numberOfK = 0;
        if (array == null) {
            numberOfK = 0;
        }
        int firstK = getFirstK(array, k);
        int lastK = getLastK(array, k);
        if (firstK != -1 && lastK != -1) {
            numberOfK = lastK - firstK + 1;
        }
        return numberOfK;
    }

    public static void main(String[] args) {
        int[] arr = {1,2 ,3, 3, 3, 3, 4, 5};
        GetNumberOfKSolution getNumberOfKSolution = new GetNumberOfKSolution();
        System.out.println(getNumberOfKSolution.getFirstK(arr, 3));
        System.out.println(getNumberOfKSolution.getLastK(arr, 3));

    }
}
