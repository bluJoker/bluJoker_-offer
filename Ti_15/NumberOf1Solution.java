
/**
 *
 * 左移运算符(<<)：m<<n表示把m左移n位。在左移n位的时候，最左边的n位将被丢弃，同时在最右边补上n个0。
 * 右移运算符(>>)：m>>n表示把m右移n位。在右移n位的时候，最右边的n位将被丢弃。
 * 但右移时处理最左边位的情形要稍微复杂一点：
 *              如果数字是一个无符号数值，则用0填补最左边的n位；
 *              如果数字是一个有符号数值，则用数字的符号位填补最左边的n位
 *
 * */
public class NumberOf1Solution {

    // 解法1：循环的次数等于整数二进制的位数，32位->32次循环
    public int NumberOf1_1(int n) {
        int count = 0;
        int flag = 1;

        while (flag != 0){
            if ((n & flag) != 0){
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    // 解法2：整数中有几个1循环几次
    // 思想：把一个整数减去1，再和原整数做&运算，会把该整数最右边的1变成0
    public int NumberOf1_2(int n) {
        int count = 0;

        while (n != 0){
            // Tips：以下两句顺序不能反
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOf1Solution numberOf1Solution = new NumberOf1Solution();
        System.out.println(numberOf1Solution.NumberOf1_1(1009));
        System.out.println(numberOf1Solution.NumberOf1_2(1009));
    }

}
