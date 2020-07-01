import java.util.Arrays;

public class MultiplySolution {

    public int[] multiply(int[] A) {
        if (A == null || A.length <= 0){
            return null;
        }
        int[] res = new int[A.length];

        res[0] = 1;
        for (int i = 1; i < A.length; i++) {
            // C[i] = C[i-1] * A[i-1]
            // 不能写成res[i] *= A[i - 1]; 因为此种乘与i相关：res[i] = res[i] * A[i-1]。可以使用temp *= A[i-1]
            res[i] = res[i - 1] * A[i - 1];
        }

        int temp = 1;
        for (int j = A.length - 2; j >= 0; j--) {
            // D[i] = D[i+1] * A[i+1]
            temp *= A[j + 1];

            // B[i] = C[i] * D[i]
            res[j] *= temp;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        MultiplySolution multiplySolution = new MultiplySolution();
        System.out.println(Arrays.toString(multiplySolution.multiply(a)));
    }
}
