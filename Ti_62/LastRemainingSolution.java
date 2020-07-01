/**
 *
 * 1、使用LinkedList没办法使用next指针将链表成环
 * 2、使用ListIterator无法也不能将链表成环
 * 3、左程云书中输入为单链表结点，故可以容易使用next使链表成环
 * 4、剑指offer输入为数组，只能自己实现单链表，然后再做题
 *
 * */
public class LastRemainingSolution {
    public int lastRemaining(int n, int m) {
        if (n < 1 || m < 1){
            return -1;
        }

        int last = 0;
        // i表示第几个数，因为有n个数，故i<=n
        for (int i = 2; i <= n; i++){
            last = (last + m) % i;
        }
        return last;
    }

    public static void main(String[] args) {
        LastRemainingSolution lastRemainingSolution = new LastRemainingSolution();
        System.out.println(lastRemainingSolution.lastRemaining(5, 3));
    }
}
