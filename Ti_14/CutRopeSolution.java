import java.util.Arrays;

public class CutRopeSolution {

//    public int[] dp = new int[9];
//    public int cutRope(int target) {
//        if (target == 1) {
//            return 1;
//        }
//        if (target == 2) {
//            return 2;
//        }
//        if (target == 3) {
//            return 3;
//        }
//        int res = -1;
//
//        for (int i = 1; i < target; i++) {
//            res = Math.max(res, i * cutRope(target - i));
//        }
//        dp[target] = res;
//        return res;
//    }

    public int cutRope(int target) {
        // 以下target和最优解不相等，在构造大问题时不能使用
        if (target < 2) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }

        // 构造大问题时使用以下该值
        int[] products = new int[target + 1];
        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;

        int max = 0;
        for (int i = 4; i <= target; i++) {
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[i - j];

                if (max < product) {
                    max = product;
                }
            }
            products[i] = max;
        }
        max = products[target];
        return max;
    }

    public int cutRope2(int target) {
        // 以下target和最优解不相等，在构造大问题时不能使用
        if (target < 2) {
            return 0;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }

        // target能最多分解成timesOf3 个3
        int timesOf3 = target / 3;
        if (target - timesOf3 * 3 == 1) {
            timesOf3 -= 1;
        }

        int timesOf2 = (target - timesOf3 * 3) / 2;
        return (int)(Math.pow(3, timesOf3)) * (int)(Math.pow(2, timesOf2));
    }

    public static void main(String[] args) {
        CutRopeSolution cutRopeSolution = new CutRopeSolution();
        System.out.println(cutRopeSolution.cutRope(20));
        System.out.println(cutRopeSolution.cutRope2(20));
//        System.out.println(Arrays.toString(cutRopeSolution.dp));
    }
}
