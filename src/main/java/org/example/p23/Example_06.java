package org.example.p23;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个字符串类型的数组arr，求其中出现次数最多的前K个
 */
public class Example_06 {
    public static void main(String[] args) {
        /**
         * 1 --> 5
         * 2 --> 4
         * 3 --> 3
         * 4 --> 2
         * 5 --> 1
         */
        String[] arr = {"1", "2", "3", "4", "5", "1", "2", "3", "4", "1", "2", "3", "1", "2", "1"};
        int k = 3;
        System.out.println("获取前3个数据为："+solution01(arr, k).stream().collect(Collectors.joining(",")));
    }

    /**
     * 利用大根堆存储数据，将无法进入堆中的数据防止到list集合中返回
     * @param arr
     * @param k
     * @return
     */
    private static List<String> solution01(String[] arr, int k) {
        List<String> result=new ArrayList<>();
        //构建hash表，统计数组中每个字符串出现的次数
        Map<String,Integer> hash=new HashMap<>();
        for (String s : arr) {
            if (hash.containsKey(s)){
                hash.put(s,hash.get(s)+1);
            }else {
                hash.put(s,1);
            }
        }
        //自定义一个大根堆，将hash表中的数据存入大根堆中
        PriorityQueue<Map.Entry<String,Integer>> maxHeap=new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String,Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        //将hash表中的数据存入大根堆中
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            maxHeap.add(entry);
        }

        //将大根堆中的数据取出前k个元素，放入list集合中
        for (int i = 0; i < k; i++) {
            result.add(maxHeap.poll().getKey());
        }

        //返回list集合
        return result;
    }
}
