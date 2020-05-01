import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数。
 * PS：min()函数的时间复杂度应为O（1）
 * */
// 每加入一个元素，就将比较辅助栈栈顶元素与该值，并将较小者（即目前最小元素）入辅助栈
// 辅助栈的大小==数值栈的大小
// 这样当前最小元素出栈后，辅助栈也出栈，出栈后的栈顶即为次最小元素
public class MinStack {

    public Stack<Integer> num_stack = new Stack<>();
    public Stack<Integer> min_stack = new Stack<>();

    public void push(int node) {
        num_stack.push(node);

        if (!min_stack.isEmpty()){
            min_stack.push(Math.min(node, min_stack.peek()));
        }else {
            min_stack.push(node);
        }
    }

    public void pop() {
        num_stack.pop();
        min_stack.pop();
    }

    public int top() {
        return num_stack.peek();
    }

    public int min() {
        return min_stack.peek();
    }


    public static void main(String[] args){

        MinStack minStack = new MinStack();
        minStack.push(3);
        System.out.println("push(3) = " + minStack.min());
        minStack.push(4);
        System.out.println("push(4) = " + minStack.min());
        minStack.push(2);
        System.out.println("push(2) = " + minStack.min());
        minStack.push(1);
        System.out.println("push(1) = " + minStack.min());

        minStack.pop();
        System.out.println("pop() = " + minStack.min());
        minStack.pop();
        System.out.println("pop() = " + minStack.min());
//        minStack.pop();
        minStack.push(0);
        System.out.println("push(0) = " + minStack.min());

        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
    }
}
