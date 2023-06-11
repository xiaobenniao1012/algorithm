package org.example.p24;

import java.util.Stack;

/**
 * 如何仅用栈结构实现队列结构?
 */
public class Example_02 {
    public static void main(String[] args) {
        //使用2个栈实现队列
        TwoStackQueue queue = new TwoStackQueue();
        //向队列中放入数据，并测试
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        queue.add(4);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    public static class TwoStackQueue {
        //入队栈
        private Stack<Integer> stackPush;
        //出队栈
        private Stack<Integer> stackPop;

        public TwoStackQueue() {
            //数据栈
            stackPush = new Stack<>();
            //逆序栈
            stackPop = new Stack<>();
        }

        /**
         * 设置栈元素时，直接压入入队栈
         * @param value
         */
        public void add(int value) {
            stackPush.push(value);
        }

        /**
         * 出队时，如果出队栈为空，则将入队栈的元素全部压入出队栈，然后出队
         * @return
         */
        public int poll() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (stackPop.isEmpty()) {
                //将入队栈的元素全部压入出队栈
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.pop();
        }

        public int peek() {
            if (stackPush.isEmpty() && stackPop.isEmpty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
            return stackPop.peek();
        }
    }
}
