public class SumSolution {
    private static int num;
    private static int sum;

    public SumSolution() {
        ++num;
        sum += num;
    }

    public static void reset(){
        num = 0;
        sum = 0;
    }

    public static int getSum(){
        return sum;
    }

    public static int sumWithoutWhile(int n) {
        if (n < 1){
            return 0;
        }
        SumSolution.reset();
        SumSolution[] sumSolutions = new SumSolution[n];
        for (int i = 0; i < sumSolutions.length; i++) {
            sumSolutions[i] = new SumSolution();
        }
        return SumSolution.getSum();
    }

    public static void main(String[] args) {
        System.out.println(SumSolution.sumWithoutWhile(12));
    }
}