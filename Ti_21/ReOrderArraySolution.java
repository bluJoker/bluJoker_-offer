import java.util.ArrayList;
import java.util.Arrays;

public class ReOrderArraySolution {

    // 解法1：类似快排，不保证稳定
    public static void reOrderArray1(int[] array) {
        if (array == null) {
            return;
        }

        int lo = 0;
        int hi = array.length - 1;

        while (lo < hi){
            while ((array[lo] & 0x01) !=0){
                lo++;
            }

            while ((array[hi] & 0x01) ==0){
                hi--;
            }

            if (lo < hi){
                int tmp = array[lo];
                array[lo] = array[hi];
                array[hi] = tmp;
            }
        }
    }


    // 不能学排序算法学傻了吧，这题不至于上各种排序算法吧还
/*新建一个数组先把原数组中的奇数push进去再把偶数push进去，然后用新数组数据覆盖原数组即可
复杂度O(n)
*/
    /*class Solution {
        public:
        void reOrderArray(vector<int> &array) {
            vector<int> res;
            for(int i = 0; i < array.size(); i++)
            {
                if(array[i] % 2 == 1)
                    res.push_back(array[i]);
            }
            for(int i = 0; i < array.size(); i++)
            {
                if(array[i] % 2 == 0)
                    res.push_back(array[i]);
            }
            //array.swap(res);
            array = res;

        }
    };

    */
    // 解法2：使用辅助空间
    public static ArrayList<Integer> reOrderArray2(int[] array) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        if (array == null) {
            return arrayList;
        }
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 0x01) == 1){
                arrayList.add(array[i]);
            }
        }

        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 0x01) == 0){
                arrayList.add(array[i]);
            }
        }
        return arrayList;
    }

    // TODO:解法3：稳定排序包括插入、归并，使用插入排序做

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        ReOrderArraySolution.reOrderArray1(arr);
        System.out.println(Arrays.toString(arr));
    }

}
