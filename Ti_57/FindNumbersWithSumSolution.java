import java.util.ArrayList;
import java.util.Arrays;

/**
 * 题目条件：1、递增排序数组  2、输出任意一对即可
 * */
public class FindNumbersWithSumSolution {


    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (array == null){
            return arrayList;
        }

        int lo = 0;
        int hi = array.length - 1;
        while (lo <= hi){
            if ((array[lo] + array[hi]) == sum){
                arrayList.add(array[lo]);
                arrayList.add(array[hi]);
                break;
            }else if ((array[lo] + array[hi]) > sum){
                hi--;
            }else {
                lo++;
            }
        }
        return arrayList;
    }


    public static void main(String[] args) {
        int[] arr = {1,2,4,7,11,15};
        FindNumbersWithSumSolution findNumbersWithSumSolution = new FindNumbersWithSumSolution();
        System.out.println(findNumbersWithSumSolution.FindNumbersWithSum(arr, 29));
    }
}
