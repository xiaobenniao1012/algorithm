package org.example.p23;

/**
 * 假设s和m初始化，s = “a”; m = s;
 * 再定义两种操作，第一种操作：
 * m = s;
 * s = s + s;
 * 第二种操作：
 * s = s + m;
 * 求最小的操作步骤数，可以将s拼接到长度等于n
 *
 * 如：
 * n=3，若执行第二种操作需要执行2次，同时也可以先执行第一种操作，再执行第二种操作，也是2次
 * n=4，若执行第二种操作需要执行3次，同时执行第一种操作2次。
 * n=5，只能执行第二种操作需要执行4次
 * n=6，若执行第二种操作需要执行5次；可先执行第一种操作，在执行第二种操作，最后再执行第一种操作，需要3次。
 */
public class Example_05 {
    public static void main(String[] args) {
        int n = 6;
        //System.out.println(getMinStep(n));

    }
}
