import java.util.Arrays;

/**
 * 用长度oldestAge + 1的整数数组作为辅助空间换来了O(n)的时间效率
 *
 * */
public class SortAgesSolution {

    public static void sortAges(int[] ages) {
        if (ages == null) {
            return;
        }

        int oldestAge = 99;
        // 1. 需要包含下标99，0~99长度为100
        int[] timesOfAge = new int[oldestAge + 1];

        for (int i = 0; i < timesOfAge.length; i++) {
            timesOfAge[i] = 0;
        }

        for (int i = 0; i < ages.length; i++) {
            int age = ages[i];

            if (age < 0 || age > 99) {
                throw new IllegalArgumentException("invalid age");
            }

            timesOfAge[age]++;
        }

        int index = 0;
        // 2. 双重循环
        for (int i = 0; i <= oldestAge; i++) {
            for (int j = 0; j < timesOfAge[i]; j++) {
                ages[index] = i;
                index++;
            }
        }
    }

    public static void main(String[] args) {
        int[] ages = {35, 18, 29, 0, 71, 99, 99, 1, 45, 29};
        SortAgesSolution.sortAges(ages);
        System.out.println(Arrays.toString(ages));
    }
}
