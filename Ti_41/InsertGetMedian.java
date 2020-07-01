import java.util.Comparator;
import java.util.PriorityQueue;

public class InsertGetMedian {

////    // MaxHeap
////    public class MaxHeap {
//        public int[] data;
//        public int size = 0;
//
//        public InsertGetMedian(int maxN) {
//            this.data = new int[maxN + 1];
//        }
//
//        private void swim(int k) {
//            while (k > 1 && data[k] > data[k / 2]) {
//                exch(data, k, k / 2);
//                k = k / 2;
//            }
//        }
//
//        private void sink(int k) {
//            while (2 * k <= size) {
//                int j = 2 * k;
//                if (j < size && data[j] < data[j + 1]) {
//                    j = j + 1;
//                }
//                if (data[k] >= data[j]) {
//                    break;
//                }
//                exch(data, k, j);
//                k = j;
//            }
//        }
//
//        public void add(int v) {
//            data[++size] = v;
//            swim(size);
//        }
//
//        public int deleteMax() {
//            int max = data[1];
//            exch(data, 1, size - 1);
//            sink(1);
//            return max;
//        }
//
//        private void exch(int[] data, int i, int j) {
//            int tmp = data[i];
//            data[i] = data[j];
//            data[j] = tmp;
//        }
////    }


    // Java api中PriorityQueue默认即为最小堆。通过在构造器中提供Comparator实现比较，构造最大堆
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    public void Insert(Integer num) {
        if (((maxHeap.size() + minHeap.size()) & 0x1) == 0){
            if (maxHeap.size() > 0 && num < maxHeap.peek()){
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            }else {
                minHeap.offer(num);
            }
        }else {
            if (minHeap.size() > 0 && num > minHeap.peek()){
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            }else {
                maxHeap.offer(num);
            }
        }
    }

    public Double GetMedian() {
        int size = maxHeap.size() + minHeap.size();
        if (size <= 0){
            return 0d;
        }
        if ((size & 0x1) == 0){
            return Double.valueOf(maxHeap.peek() + minHeap.peek()) / 2;
        }else {
            return Double.valueOf(minHeap.peek());
        }
    }

    public static void main(String[] args) {
        InsertGetMedian insertGetMedian = new InsertGetMedian();
        insertGetMedian.Insert(4);
        insertGetMedian.Insert(5);
        insertGetMedian.Insert(1);
        insertGetMedian.Insert(6);
        insertGetMedian.Insert(2);
        insertGetMedian.Insert(7);
        insertGetMedian.Insert(3);
        insertGetMedian.Insert(8);
//        insertGetMedian.Insert(9);


        System.out.println(insertGetMedian.GetMedian());
    }
}
