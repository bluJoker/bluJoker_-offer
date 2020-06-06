
import java.util.ArrayList;

public class Min {

    public static int minNumberInRotateArray(int[] array) {
        // 解法一：仅能对已经旋转的数组查找。对未旋转和有重复元素无效
//        int lo = 0;
//        int hi = array.length - 1;
//
//        while (hi - lo > 1) {
//            int mid = lo + (hi - lo) / 2;
//            if (array[mid] > array[lo]) {
//                lo = mid;
//            } else if (array[mid] < array[hi]) {
//                hi = mid;
//            }
//
//        }
//        return array[hi];

        // 解法二：对未旋转数组有效，但对有重复元素数组无效
//        int lo = 0;
//        int hi = array.length - 1;
//        int midIndex = lo;
//
//        while (array[lo] >= array[hi]) {
//
//            if (hi-lo == 1){
//                midIndex = hi;
//                break;
//            }
//
//            midIndex = lo + (hi - lo) / 2;
//            if (array[midIndex] >= array[lo]) {
//                lo = midIndex;
//            } else if (array[midIndex] <= array[hi]) {
//                hi = midIndex;
//            }
//        }
//        return array[midIndex];


        // 解法三：对旋转数组及有重复元素数组都有效
        if (array.length == 0) {
            return 0;
        }

        int lo = 0;
        int hi = array.length - 1;
        int midIndex = lo;

        while (array[lo] >= array[hi]) {
            if (hi - lo == 1) {
                midIndex = hi;
                break;
            }

            midIndex = lo + (hi - lo) / 2;

            // 当两个指针指向的数字及它们中间的数字三者相同的时候，我们无法判断中间的数字是位于前面的子数组还是后面的子数组，
            // 也就无法移动两个指针来缩小查找的范围。此时，我们不得不采用顺序查找的方法。
            if (array[lo] == array[hi] && array[lo] == array[midIndex]) {
                return MinInOrder(array, lo, hi);
            }

            if (array[midIndex] >= array[lo]) {
                lo = midIndex;
            } else if (array[midIndex] <= array[hi]) {
                hi = midIndex;
            }
        }
        return array[midIndex];
    }

    private static int MinInOrder(int[] array, int lo, int hi) {
        int result = array[lo];
        for (int i = lo + 1; i <= hi; i++) {
            if (array[i] < result) {
                result = array[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 2, 3};
        int[] arr2 = {1, 2, 3, 4, 5};

        int[] arr3 = {1, 0, 1, 1, 1};
        int[] arr4 = {1, 1, 1, 0, 1};

        int[] arr5 = {};

        System.out.println(minNumberInRotateArray(arr));
        System.out.println(minNumberInRotateArray(arr2));
        System.out.println(minNumberInRotateArray(arr3));
        System.out.println(minNumberInRotateArray(arr4));
        System.out.println(minNumberInRotateArray(arr5));
    }
}
