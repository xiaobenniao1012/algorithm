package org.example.p24;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 如何用队列结构实现栈结构?
 */
public class Example_03 {

    public static void main(String[] args) {
        //使用2个队列定义栈结构
        TwoQueueStack stack = new TwoQueueStack();
        //放入数据并测试
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    /**
     * 定义2个队列的栈结构
     */
    public static class TwoQueueStack {
        //数据队列
        private Queue<Integer> dataQueue;
        //辅助队列
        private Queue<Integer> helpQueue;

        public TwoQueueStack() {
            dataQueue = new ArrayDeque<Integer>();
            helpQueue = new ArrayDeque<Integer>();
        }

        /**
         * 入栈时，如果逆序队列为空，则直接将数据压入数据队列，否则将逆序队列中的数据依次弹出并压入数据队列，然后将数据压入数据队列即可。
         *
         * @param value
         */
        public void push(int value) {
            if (helpQueue.isEmpty()) {
                dataQueue.add(value);
                return;
            }
            //将逆序队列中的数据依次弹出并压入数据队列
            while (!helpQueue.isEmpty()) {
                dataQueue.add(helpQueue.poll());
            }
        }

        /**
         * 出栈时，将数据队列中的元素依次压入辅助队列，直到数据队列中只剩下一个元素，然后将该元素出队
         *
         * @return
         */
        public int pop() {
            if (dataQueue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            //将数据队列中的元素依次压入辅助队列，直到数据队列中只剩下一个元素
            while (dataQueue.size() > 1) {
                helpQueue.add(dataQueue.poll());
            }
            //将数据队列中的最后一个元素出队
            int value = dataQueue.poll();

            //交换数据队列和辅助队列
            Queue<Integer> temp = dataQueue;
            dataQueue = helpQueue;
            helpQueue = temp;
            return value;
        }

        /**
         * 获取栈顶元素时，将数据队列中的元素依次压入辅助队列，直到数据队列中只剩下一个元素，然后将该元素出队
         *
         * @return
         */
        public int peek() {
            if (dataQueue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            //将数据队列中的元素依次压入辅助队列，直到数据队列中只剩下一个元素
            while (dataQueue.size() > 1) {
                helpQueue.add(dataQueue.poll());
            }
            //获取数据队列中的最后一个元素
            int value = dataQueue.poll();
            //交换数据队列和辅助队列
            Queue<Integer> temp = dataQueue;
            dataQueue = helpQueue;
            helpQueue = temp;
            return value;
        }
    }
}
