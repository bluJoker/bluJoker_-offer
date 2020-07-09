public class Print1ToMaxSolution {

    // 解法1：字符串解决大数问题
    public void print1ToMaxN(int n){
        if (n <= 0){
            return;
        }

        char[] number = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }

        while (!Increment(number)){
            printNumber(number);
        }

    }

    private boolean Increment(char[] number) {
        // 判断是否溢出
        boolean isOverflow = false;
        // 判断是否进位
        int nTakeOver = 0;
        int nLength = number.length;

        // 从后往前加，判断是否进位
        for (int i = nLength-1; i >=0; i--) {
            // 加上进位
            int nSum = number[i] - '0' +nTakeOver;
            // 当且仅当为最后一位时才需要+1
            if (i == nLength-1){
                nSum++;
            }

            if (nSum >= 10){
                if (i==0){
                    isOverflow = true;
                }else {
                    nSum -= 10;
                    nTakeOver=1;
                    number[i] = (char)('0'+nSum);
                }
            }else {
                number[i] = (char)('0'+nSum);
                break;
            }
        }

        return isOverflow;
    }


    // 解法2：全排列
    //打印1到最大的n位数的主方法
    public void printToMaxOfDigits(int n){
        if(n <= 0){
            System.out.println("输入的n没有意义");
            return;
        }
        char number[] = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        for (int i = 0; i < 10; ++i) {
            number[0] = (char) (i + '0');
            printToMaxOfNDigitsRecursively(number, n, 0);
        }
    }
    //利用递归实现1到最大的n位数的全排列
    public void printToMaxOfNDigitsRecursively(char[] number, int n, int index) {
        if(index == n - 1){
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; ++i) {
            number[index + 1] = (char) (i + '0');
            printToMaxOfNDigitsRecursively(number, n, index + 1);
        }
    }



    private void printNumber(char[] number) {
        boolean isBeginning0 = true;
        int nLength = number.length;
        for (int i = 0; i < nLength; ++i) {
            // 找到第一个不为0的位置，打印其及其后所有字符
            if(isBeginning0 && number[i]!='0'){
                isBeginning0 = false;
            }
            if(!isBeginning0){
                System.out.print(number[i]);
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int n = 3;
        Print1ToMaxSolution print1ToMaxSolution = new Print1ToMaxSolution();
        print1ToMaxSolution.print1ToMaxN(2);
        print1ToMaxSolution.printToMaxOfDigits(3);
    }
}
