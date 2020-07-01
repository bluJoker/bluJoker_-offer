import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 因为index需要在队首和队尾删除元素，故需要双端队列。Java中双端队列Deque的实现类有ArrayDeque和LinkedList。
 * */
public class MaxInWindowsSolution {

    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> resList = new ArrayList<>();

        if (num == null || num.length < size){
            return resList;
        }

        Deque<Integer> index = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            while (!index.isEmpty() && num[i] >= num[index.peekLast()]){
                index.pollLast();
            }
            index.addLast(i);
        }

        for (int i = size; i < num.length; i++) {
            // 将index首元素(当前窗口最大值)加入结果集
            resList.add(num[index.peekFirst()]);

            // 如果已有的数字小于待存入的数字，那么这些数字已经不可能是滑动窗口的最大值，因此它们将会被依次从队列的尾部删除
            while (!index.isEmpty() && num[i] >= num[index.peekLast()]){
                index.pollLast();
            }

            // 如果index队列的头部元素已经从窗口里滑出，那么滑出的数字也需要从队列的头部删除
            if (!index.isEmpty() && ((i - index.peekFirst()) >= size)){
                index.pollFirst();
            }

            index.addLast(i);
        }

        // 加入结果集的代码在for循环中，故最后一次进不去
        resList.add(num[index.peekFirst()]);
        return resList;
    }


    public static void main(String[] args) {
        MaxInWindowsSolution maxInWindowsSolution = new MaxInWindowsSolution();
        int[] num = {2,3,4,2,6,2,5,1};
        System.out.println(maxInWindowsSolution.maxInWindows(num, 3));
    }
}
