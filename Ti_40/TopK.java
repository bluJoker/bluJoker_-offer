import java.util.*;

public class TopK {
    // 1、使用快排的partition方法，时间O(logn)，但修改了原数组
    // 7, 5, 1, 6, 2, 4, 3, 8
    // k = 4: 1, 2, 3, 4
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (input == null || k > input.length || k <= 0) {
            return list;
        }

        int start = 0;
        int end = input.length - 1;
        int index = partition(input, start, end);

        // index = k-1时，0到index即为所求的最小的k个数，注意这k个数字不一定是排序的
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
                index = partition(input, start, end);
            } else {
                start = index + 1;
                index = partition(input, start, end);
            }
        }

        for (int i = 0; i <= k - 1; i++) {
            list.add(input[i]);
        }

        return list;
    }

    private int partition(int[] input, int lo, int hi) {
        // 此处防止遍历最后一个元素后while中越界
        if (lo >= hi) {
            return lo;
        }
        int i = lo;
        int j = hi + 1;
        int val = input[lo];
        while (true) {
            while (input[++i] < val) {
                if (i == hi) {
                    break;
                }
            }
            while (input[--j] > val) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(input, i, j);
        }
        exch(input, lo, j);
        return j;
    }

    private void exch(int[] input, int i, int j) {
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }


    // 2、n很大时即是TopK问题，使用TreeSet保存最小的k个数。
    // 做法：TreeSet的size小于k，直接插入；待插入的数比TreeSet的目前k个数中最大值要小，则删除最大值，添加待删除元素；否则什么都不做。
    // 堆需要自己实现，故使用TreeSet红黑树，操作只需要O(logk)，n个数，故总时间O(nlogk)
    // TreeSet.first/last：返回有序集合中的最小或最大元素
    // TreeSet.pollFirst/pollLast：删除并返回有序集合中的最小或最大元素
    public ArrayList<Integer> GetLeastNumbers_SolutionTopK(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (input == null || k > input.length || k <= 0) {
            return list;
        }
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < input.length; i++) {
            if (treeSet.size() < k) {
                treeSet.add(input[i]);
            } else {
                if (input[i] < treeSet.last()) {
                    treeSet.pollLast();
                    treeSet.add(input[i]);
                }
            }
        }
        list.addAll(treeSet);
        return list;
    }

    // 3、PriorityQueue即为最小堆。通过在构造器中提供Comparator实现比较，构造最大堆
    public ArrayList<Integer> GetLeastNumbers_TopK_PriorityQueue(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (input == null || k > input.length || k <= 0) {
            return list;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (int i = 0; i < input.length; i++) {
            if (maxHeap.size() < k) {
                maxHeap.offer(input[i]);
            } else {
                if (input[i] < maxHeap.peek()) {
                    maxHeap.poll();
                    maxHeap.offer(input[i]);
                }
            }
        }
        list.addAll(maxHeap);
        return list;
    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 1, 6, 2, 4, 3, 8};
        System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));

        TopK topK = new TopK();
//        System.out.println(topK.GetLeastNumbers_TopK_PriorityQueue(arr, 7));
        System.out.println(topK.GetLeastNumbers_Solution(arr, 4));

    }
}
