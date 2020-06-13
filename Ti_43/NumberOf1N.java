public class NumberOf1N {
    public int NumberOf1Between1AndN_Solution(int n) {
        int num = 0;
        for(int i = 1; i <= n; i++){
            num += numberOf1(i);
        }
        return num;
    }
    public int numberOf1(int n){
        int num = 0;
        while(n != 0){
            if(n % 10 == 1){
                num++;
            }
            n = n / 10;
        }
        return num;
    }

    public static void main(String[] args) {
        NumberOf1N numberOf1N = new NumberOf1N();
        int n = 1;
        System.out.println(numberOf1N.NumberOf1Between1AndN_Solution(n));
        System.out.println(numberOf1N.numberOf1(n));
    }
}
