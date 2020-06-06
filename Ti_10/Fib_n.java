public class Fib_n {
    
    long fibonacci(int n){
        
        int[] result = {0, 1};
        if (n < 2){
            return result[n];
        }
        
        long fibOne = 1;
        long fibTwo = 0;
        long fibN = 0;

        for (int i = 2; i <= n; i++) {
            fibN = fibOne + fibTwo;

            // 顺序不能反
            fibTwo = fibOne;
            fibOne = fibN;
        }
        return fibN;
    }


    long fibonacci2(int n){

        if (n <= 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }

        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args) {
        Fib_n fib_n = new Fib_n();

        System.out.println("fib_n.fibonacci(10) = " + fib_n.fibonacci(100));
        System.out.println("fib_n.fibonacci(10) = " + fib_n.fibonacci2(100));
    }
}
