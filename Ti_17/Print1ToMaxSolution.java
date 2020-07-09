public class Print1ToMaxSolution {

    public void print(int n){
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

        for (int i = nLength-1; i >=0; i--) {
            int nSum = number[i] - '0' +nTakeOver;
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

    private void printNumber(char[] number) {
        boolean isBeginning0 = true;
        int nLength = number.length;
        for (int i = 0; i < nLength; ++i) {
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
        print1ToMaxSolution.print(128);
    }
}
