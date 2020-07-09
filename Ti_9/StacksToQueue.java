import java.util.Stack;

/**
 *
 * 删除一个元素的步骤：
 * 当stack2不为空时，在stack2中的栈顶元素是最先进入队列的元素，可以弹出。当stack2为空时，我们把stack1中的元素逐个弹出压入stack2。
 * */
public class StacksToQueue {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void enqueue(int node) {
        stack1.push(node);
    }

    public int dequeue() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        StacksToQueue solution1 = new StacksToQueue();

        // 1, 2, 3, 4
        solution1.enqueue(1);
        solution1.enqueue(2);
        solution1.enqueue(3);
        System.out.println(solution1.dequeue());
        System.out.println(solution1.dequeue());
        solution1.enqueue(4);
        System.out.println(solution1.dequeue());
        System.out.println(solution1.dequeue());

        StacksToQueue solution2 = new StacksToQueue();

        // 1, 2, 3, 4
        System.out.println(solution2.dequeue());

    }
}
