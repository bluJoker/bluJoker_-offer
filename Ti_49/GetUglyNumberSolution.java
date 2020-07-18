
/**
 * 100以内丑数：
 * 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 16, 18, 20, 24, 25, 27, 30,
 * 32, 36, 40, 45, 48, 50, 54, 60, 64, 72, 75, 80, 81, 90, 96, 100.
 *
 *
 * pUglyNumbers：1, 0, 0, 0, 0, 0, 0
 * nextUglyIndex=1：Math.min(2,3,5)=2; pMultiply2 = 1(pUglyNumbers[1]*2=4>2); pMultiply3 = 0(1*3=3>2); pMultiply5 = 0(1*5=5>2);
 * nextUglyIndex=2：Math.min(4,3,5)=3; pMultiply2 = 1(pUglyNumbers[1]*2=4>3); pMultiply3 = 1(2*3=6>3); pMultiply5 = 0(1*5=5>3);
 * nextUglyIndex=3：Math.min(4,6,5)=4;
 * ......
 *
 * */
public class GetUglyNumberSolution {

    // 解法1：牛客网运行错误提示
    // 运行超时：您的程序未能在规定时间内运行结束，请检查是否循环有错或算法复杂度过大。点击对比用例标准输出与你的输出
    // Tips2：该算法非常直观，代码也非常简洁。
    //        但最大问题是每个整数都要计算。即使一个数字不是丑数，还是需要对它执行求余数和除法操作。
    public int getUglyNumber1(int index) {
        if (index <= 0){
            return 0;
        }

        int num = 0;
        int uglyFound = 0;

        // Tips1: <而非<=，因为习惯上我们把1当做第1个丑数
        while (uglyFound < index){
            num++;
            if (isUgly(num)){
                uglyFound++;
            }
        }

        return num;
    }

    boolean isUgly(int number){
        while (number % 2 == 0){
            number /= 2;
        }
        while (number % 3 == 0){
            number /= 3;
        }
        while (number % 5 == 0){
            number /= 5;
        }
        return (number == 1) ? true : false;
    }

    // 解法2：空间换时间，丑数应该是另一个丑数乘以2、3或者5的结果
    public int getUglyNumber2(int index) {
        if (index <= 0){
            return 0;
        }

        int[] pUglyNumbers = new int[index];
        pUglyNumbers[0] = 1;
        int nextUglyIndex = 1;

        // Tips3：pMultiply2、pMultiply3、pMultiply5为*2、*3、*5比最大丑数大的最小下标。下一个丑数即为此3者取值。
        int pMultiply2 = 0;
        int pMultiply3 = 0;
        int pMultiply5 = 0;

        while (nextUglyIndex < index){
            int min = Math.min(Math.min(pUglyNumbers[pMultiply2] * 2, pUglyNumbers[pMultiply3] * 3),
                    pUglyNumbers[pMultiply5] * 5);
            pUglyNumbers[nextUglyIndex] = min;

            // Tips4：每次生成新的丑数的时候去更新pMultiply2、pMultiply3、pMultiply5
            while (pUglyNumbers[pMultiply2] * 2 <= pUglyNumbers[nextUglyIndex]){
                pMultiply2++;
            }
            while (pUglyNumbers[pMultiply3] * 3 <= pUglyNumbers[nextUglyIndex]){
                pMultiply3++;
            }
            while (pUglyNumbers[pMultiply5] * 5 <= pUglyNumbers[nextUglyIndex]){
                pMultiply5++;
            }
            nextUglyIndex++;
        }

        return pUglyNumbers[nextUglyIndex - 1];
    }


    public static void main(String[] args) {
        GetUglyNumberSolution getUglyNumberSolution = new GetUglyNumberSolution();
        System.out.println(getUglyNumberSolution.getUglyNumber1(1500));
//
//        for (int i = 1; i <= 100; i++) {
//            if (getUglyNumberSolution.isUgly(i)){
//                System.out.print(i + ", ");
//            }
//        }

        System.out.println(getUglyNumberSolution.getUglyNumber2(7));
    }
}
