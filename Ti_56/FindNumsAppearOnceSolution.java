import java.util.Arrays;

public class FindNumsAppearOnceSolution {

    // num1,num2分别为长度为1的数组。传出参数
    // 将num1[0],num2[0]设置为返回结果
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array == null || array.length < 2){
            return;
        }

        int num = array[0];
        for (int i = 1; i < array.length; i++) {
            num = num ^ array[i];
        }

        int flag = 1;
        int indexOf1 = 0;
        while (flag != 0) {
            indexOf1++;

            // 以下写法有误，(2 & 2) = 2 != 1
            // if ((num & flag) == 1){
            if ((num & flag) != 0) {
                break;
            }
            flag = flag << 1;
        }

        for (int j = 0; j < array.length; j++) {
            if (isBit1(array[j], indexOf1)) {
                num1[0] ^= array[j];
                System.out.print("'1': " + array[j] + " ");
            } else {
                num2[0] ^= array[j];
                System.out.print("'0': " + array[j] + " ");
            }
        }
    }

    public boolean isBit1(int arrNum, int indexOf1) {
        arrNum = arrNum >> (indexOf1 - 1);
        return ((arrNum & 1) != 0) ? true : false;
    }


    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        FindNumsAppearOnceSolution findNumsAppearOnceSolution = new FindNumsAppearOnceSolution();
        findNumsAppearOnceSolution.FindNumsAppearOnce(arr, num1, num2);
        System.out.println(Arrays.toString(num1));
        System.out.println(Arrays.toString(num2));

    }
}
