import java.util.HashSet;
import java.util.Set;

public class Duplicate {

    public static Set<Integer> duplicateNum(int[] nums){

        if (nums == null || nums.length <= 0){
            return null;
        }

        int n = nums.length;

        int i = 0;
        Set<Integer> set = new HashSet<>();

        while (i < n){

            int m = nums[i];
            if (i == m){
                i++;
            }else {
                if (m == nums[m]){
                    set.add(m);
                    i++;
                }else {
                    int tmp = nums[i];
                    nums[i] = nums[m];
                    nums[m] = tmp;
                }
            }
        }
        return set;
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(Duplicate.duplicateNum(a));

        int[] b = {2, 3, 1, 6, 0, 5, 4};
        System.out.println(Duplicate.duplicateNum(b));
    }

}
