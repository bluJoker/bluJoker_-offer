
/**
 * int maxSubSum = 0x80000000; 必须取很小的负数而不是0。如果取0，则连续子数组的最大和如果为负，
 * 根据如下代码逻辑
 * if(sum > maxSubSum){
 *       maxSubSum = sum;
 * }
 * 得到的结果还是0。
 * */
public class FindGreatestSumOfSubArraySolution {
    public int findGreatestSumOfSubArray(int[] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        int sum = 0;
        int maxSubSum = 0x80000000;
        for(int i = 0; i < array.length; i++){
            if(sum <= 0){
                sum = array[i];
            }else{
                sum += array[i];
            }

            if(sum > maxSubSum){
                maxSubSum = sum;
            }
        }
        return maxSubSum;
    }


    public static void main(String[] args) {
        int[] arr = {1,-2,3,10,-4,7,2,-5};
        FindGreatestSumOfSubArraySolution findGreatestSumOfSubArraySolution = new FindGreatestSumOfSubArraySolution();
        System.out.println(findGreatestSumOfSubArraySolution.findGreatestSumOfSubArray(arr));
    }
}
