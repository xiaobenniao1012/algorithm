package org.example.p21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给一个包含n个整数元素的集合a，一个包含m个整数元素的集合b。
 * 定义magic操作，从一个集合中取出一个元素，放到另一个集合里，且操作过后每个集合的平均值都大于操作前。
 * 注意以下两点：
 * 1）不可以把一个集合的元素取空，这样就没有平均值了
 * 2）值为x的元素从集合b取出放入集合a，但集合a中已经有值为x的元素，则a的平均值不变（因为集合元素不会重复），b的平均值可能会改变（因为x被取出了）
 * 问：最多可以进行多少次magic操作？
 */
public class Example_04 {
    public static void main(String[] args) {
        //集合a：{1, 2, 5, 7, 8}，平均值：23/5=4.6
        int[] a=new int[]{1, 2, 5, 7, 8};
        //集合b：{3, 4, 6, 9, 10}，平均值：32/5=6.4
        int[] b=new int[]{3, 4, 6, 9, 10};

        System.out.println("magic操作次数："+solution(a,b));
    }

    public static int solution(int[] a, int[] b) {
        int sumA = 0, sumB = 0;
        for (int i : a) {
            sumA += i;
        }
        for (int i : b) {
            sumB += i;
        }

        int aSize=a.length;
        int bSize=b.length;

        //用double类型来记录平均值，防止小数精度缺失
        double avgA = (double) sumA / aSize;
        double avgB = (double) sumB / bSize;

        //如果2个平均值相同，则直接返回
        if(avgA==avgB || aSize==1 || bSize==1){
            return 0;
        }

        int count = 0;

        //b集合的平均值要大，则从b集合中向a集合中放入数据
        int[] large=avgA>avgB?a:b;
        int[] less=avgA>avgB?b:a;
        double largeSum=avgA>avgB?sumA:sumB;
        double lessSum=avgA>avgB?sumB:sumA;
        int largeLen=avgA>avgB?aSize:bSize;
        int lessLen=avgA>avgB?bSize:aSize;

        //记录小集合的数值到hash中，判断是否重复
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<less.length;i++){
            set.add(less[i]);
        }

        //对大集合进行排序
        Arrays.sort(large);
        for(int i=0;i<large.length;i++){
            if(largeLen==1){
                break;
            }

            //获取当前值
            int cur=large[i];
            if(!set.contains(large[i])
                && cur>lessSum/lessLen
                && cur<largeSum/largeLen){

                System.out.println("移动数据："+cur);

                //更改相关值
                set.add(cur);
                lessSum+=cur;
                largeSum-=cur;
                lessLen++;
                largeLen--;
                count++;
            }
        }

        return count;
    }

}
