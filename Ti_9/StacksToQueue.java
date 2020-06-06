import java.util.Stack;

public class StacksToQueue {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
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
        solution1.push(1);
        solution1.push(2);
        solution1.push(3);
        System.out.println(solution1.pop());
        System.out.println(solution1.pop());
        solution1.push(4);
        System.out.println(solution1.pop());
        System.out.println(solution1.pop());

        StacksToQueue solution2 = new StacksToQueue();

        // 1, 2, 3, 4
        System.out.println(solution2.pop());


    }


}
