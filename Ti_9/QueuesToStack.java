import java.util.LinkedList;
import java.util.Queue;

public class QueuesToStack {

    Queue<Integer> queue1 = new LinkedList<>();
    Queue<Integer> queue2 = new LinkedList<>();

    public void enqueue(int node) {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            queue1.add(node);
        }

        if (!queue1.isEmpty()) {
            queue1.add(node);
        }
        if (!queue2.isEmpty()) {
            queue2.add(node);
        }
    }

    public int dequeue() {
//        while (!queue1.isEmpty()){
//            if (queue1.size() == 1){
//                return queue1.remove();
//            }else {
//                queue2.add(queue1.remove());
//            }
//        }
//        while (!queue2.isEmpty()){
//            if (queue2.size() == 1){
//                return queue2.remove();
//            }else {
//                queue1.add(queue2.remove());
//            }
//        }
//        return 0;
        if (queue1.isEmpty() && queue2.isEmpty()) {
            throw new IllegalArgumentException("queues empty!");
        }

        int element;

        if (!queue1.isEmpty()) {
            while (queue1.size() != 1) {
                queue2.add(queue1.remove());
            }
            element = queue1.remove();
        } else {
            while (queue2.size() != 1) {
                queue1.add(queue2.remove());
            }
            element = queue2.remove();
        }
        return element;
    }


    public static void main(String[] args) {
        QueuesToStack queuesToStack = new QueuesToStack();

        queuesToStack.enqueue(1);
        queuesToStack.enqueue(2);
        queuesToStack.enqueue(3);

//        System.out.println(queuesToStack.queue1.remove());
//        System.out.println(queuesToStack.queue1.remove());
//        System.out.println(queuesToStack.queue1.remove());


        System.out.println(queuesToStack.dequeue());
        System.out.println(queuesToStack.dequeue());

        System.out.println(queuesToStack.dequeue());

    }
}
