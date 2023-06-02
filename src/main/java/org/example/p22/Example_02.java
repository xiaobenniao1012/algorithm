package org.example.p22;

import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 请编写一个程序，对一个栈里的整型数据，按升序进行排序（即排序前，栈里的数据是无序的，排序后最大元素位于栈顶），
 * 要求最多只能使用一个额外的栈存放临时数据，但不得将元素复制到别的数据结构中。
 */
public class Example_02 {

    public static void main(String[] args) {
        int[] arr=new int[]{3,5,7,6,2,4,1};
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<arr.length;i++){
            stack.push(arr[i]);
        }

        solution(stack);

        String result = stack.stream().map(Objects::toString).collect(Collectors.joining(","));
        System.out.println("输出栈中的元素："+result);
    }

    /**
     * 利用辅助栈的进出实现将辅助栈从小到大排序（栈顶最小）；
     * 当放入一个元素时，先判断是否能够放入，若不能放入，则将辅助栈的元素弹出到原始栈。
     * @param s
     */
    public static void solution(Stack<Integer> s){
        //临时栈，保证栈从上到下有序（从小到大）
        Stack<Integer> tmp=new Stack<>();

        while (!s.isEmpty()){
            //原栈中弹出数据
            Integer num=s.pop();

            //判断是否能够放入临时栈
            if(canPush(tmp,num)){
                tmp.push(num);
                continue;
            }

            //挪移回原栈中，使其可以放置
            while (!tmp.isEmpty()){
                Integer mov=tmp.pop();
                s.push(mov);

                if(canPush(tmp,num)){
                    tmp.push(num);
                    break;
                }
            }
        }

        //最后将tmp栈中的数据放入到原栈中
        while (!tmp.isEmpty()){
            s.push(tmp.pop());
        }
    }

    /**
     * 判断是否可以放入栈中
     * @param tmp
     * @param num
     * @return
     */
    private static boolean canPush(Stack<Integer> tmp,Integer num){
        return tmp.isEmpty() || tmp.peek()>=num;
    }



}
