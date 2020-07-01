public class MoreThenHalfNum {
    // 方法1：使用快排的partition（递归）找到对数组排序后的n/2处元素值。checkMoreThanHalf判断数组中等于该值的数目，大于n/2则返回该值，否则返回0。
    // 该方法修改了原数组（交换），询问面试官。
    public int MoreThanHalfNum_Solution_Partition(int[] array) {
        if (array == null) {
            return 0;
        }
        int middle = array.length >> 1;
        int start = 0;
        int end = array.length - 1;
        int index = partition(array, start, end);

        while (index != middle) {
            if (index > middle) {
                end = index - 1;
                index = partition(array, start, end);
            } else {
                start = index + 1;
                index = partition(array, start, end);
            }
        }
        int result = array[middle];
        if (!checkMoreThanHalf(array, result)) {
            return 0;
        }
        return result;
    }

    private int partition(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return lo;
        }
        int i = lo;
        int j = hi + 1;
        int val = a[lo];
        while (true) {
            while (a[++i] < val) {
                if (i == hi) {
                    break;
                }
            }
            while (a[--j] > val) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    //==================================================================================================================
    // 方法2：如果存在该数，则其在数组中出现的次数-其他数出现的次数>=1。故使用计数器实现，数字相同+1，不同-1。
    // 那么要找的数字肯定是最后一次把次数设为1时对应的数字。
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null) {
            return 0;
        }
        int result = array[0];
        int times = 1;
        for (int i = 1; i < array.length; i++) {
            if (times == 0) {
                result = array[i];
                times = 1;
            } else if (array[i] == result) {
                times++;
            } else {
                times--;
            }
        }
        if (!checkMoreThanHalf(array, result)) {
            return 0;
        }
        return result;
    }

    // 检查找到的数是否出现次数大于数组长度的一半
    private boolean checkMoreThanHalf(int[] array, int result) {
        int times = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == result) {
                times++;
            }
        }
        if (times * 2 > array.length) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MoreThenHalfNum moreThenHalfNum = new MoreThenHalfNum();
        int[] str1 = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(moreThenHalfNum.MoreThanHalfNum_Solution(str1));

        int[] str2 = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(moreThenHalfNum.MoreThanHalfNum_Solution_Partition(str2));
    }
}
