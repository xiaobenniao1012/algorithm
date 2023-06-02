package org.example.p21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个数组arr，求差值为k的去重数字对。
 */
public class Example_03 {

    public static void main(String[] args) {
        int[] arr=new int[]{3,2,5,7,0,0};

        solution(arr,2);
    }

    /**
     * 找出差值为2的数值对
     * @param arr
     * @param k
     */
    public static void solution(int[] arr ,int k){
        //排序
        Arrays.sort(arr);

        //放入到Map中
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }

        for(int i=0;i<arr.length;i++){
            if(i>0 && arr[i]==arr[i-1]){
                continue;
            }
            if(set.contains(arr[i]+k)){
                System.out.println("("+arr[i]+","+(arr[i]+k)+")");
            }
        }
    }

}
