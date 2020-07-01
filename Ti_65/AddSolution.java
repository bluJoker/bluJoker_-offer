/**
 * 1、不考虑进位相加：异或
 * 2、考虑进位：与并左移
 * 3、1和2相加，故重复1和2，直到不产生进位为止
 *
 * */
public class AddSolution {
    public int Add(int num1,int num2) {
        int sum;
        int carry;

        // 重复直到不产生进位为止
        do {
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;

            num1 = sum;
            num2 = carry;

        }while (num2 != 0);

        System.out.println("num1 = " + num1);
        System.out.println("sum = " + sum);
        return num1;
    }

    public static void main(String[] args) {
        AddSolution addSolution = new AddSolution();
        System.out.println(addSolution.Add(5, 21));
    }
}
