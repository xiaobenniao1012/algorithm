package org.example.p24;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素 的操作。
 * 要求：1.pop、push、getMin操作的时间复杂度都是O(1)；
 * 2.设计的栈类型可以使用现成的栈结构。
 */
public class Example_01 {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(1);
        myStack.push(2);
        myStack.push(1);
        //获取栈中的最小值
        System.out.println(myStack.getMin());
        //出栈
        System.out.println(myStack.pop());
        System.out.println(myStack.getMin());
        System.out.println(myStack.pop());
        System.out.println(myStack.getMin());
    }

    static class MyStack {
        //数据栈
        private java.util.Stack<Integer> stackData;
        //最小值栈
        private java.util.Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new java.util.Stack<>();
            this.stackMin = new java.util.Stack<>();
        }

        /**
         * 压栈
         * 保证最小值栈中的元素个数和数据栈中的元素个数一致
         * @param num
         */
        public void push(int num) {
            this.stackData.push(num);

            //将元素压入最小值栈
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(num);
            } else if (num <= this.getMin()) {
                //如果num小于等于最小值栈的栈顶元素，就将num压入最小值栈
                this.stackMin.push(num);
            }else {
                //如果num大于最小值栈的栈顶元素，就将最小值栈的栈顶元素再压入一次
                this.stackMin.push(this.getMin());
            }
        }

        /**
         * 出栈
         * 出栈时，最小值栈也要出栈
         * @return
         */
        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }

            int value = this.stackData.pop();
            //最小值栈也要出栈
            this.stackMin.pop();
            return value;
        }

        public int getMin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }


}
