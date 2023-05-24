package org.example.p20;

/**
 * 小虎去附近的商店买苹果，奸诈的商贩使用了捆绑交易，只提供6个每袋和8个每袋的包装，包装不可拆分。可是小虎现在只想购买恰好n个苹果，小虎想购买尽量少的袋数方便携带。
 * 如果不能购买恰好n个苹果，小虎将不会购买。输入一个整数n，表示小虎想购买的苹果，返回最小使用多少袋子。如果无论如何都不能正好装下，返回-1。
 */
public class Example_02 {

    public static void main(String[] args) {
        int n=30;

        System.out.println("解法一：需要袋子："+solution_01(n));

        //归纳结论
        /*for(int i=1;i<100;i++){
            System.out.println("数值"+i+",结果"+solution_01(i));
        }*/

        System.out.println("解法二：需要袋子："+solution_01(n));
    }

    /**
     * 首先判断输入的 n 是否小于 6 或等于 7，因为这些数无法用 6 和 8 的包装恰好装满。如果是，直接返回 -1。
     * 假设用尽量多的 8 个苹果的袋子装 n 个苹果，可以使用 n / 8 个袋子，剩余的苹果数量为 n % 8。
     * 如果剩余的苹果数量为 0，说明已经找到了满足条件的解，直接返回使用的袋子数量。
     * 否则，如果剩余的苹果数量可以被 6 整除，说明可以使用更少的 6 个苹果的袋子来装剩余的苹果，返回使用的袋子数量即可。
     * 如果以上两种情况都不满足，说明用尽量多的 8 个苹果的袋子还不能恰好装下 n 个苹果，需要逐渐减少使用 8 个苹果的袋子的数量，同时增加剩余的苹果数量，直到找到满足条件的解或者无解。具体做法是将使用 8 个苹果的袋子的数量减 1，剩余的苹果数量加 8，然后再进行判断。
     * 如果经过多次尝试，还是找不到满足条件的解，说明无解，返回 -1
     */
    public static int solution_01(int n){
        if (n < 6 || n == 7) {
            return -1;
        }
        // 先尽量用8个装袋子(先用大袋子）
        int num8 = n / 8;
        // 剩余的苹果数量
        int remainder = n % 8;
        //// 逐渐减少8个装袋子的数量，直到找到满足条件的解或者无解
        while (num8 >= 0) {
            if (remainder == 0) {
                return num8;
            } else if (remainder % 6 == 0) {
                return num8 + remainder / 6;
            } else {
                num8--;
                remainder += 8;
            }
        }
        // 找不到解，返回-1
        return -1;
    }

    /**
     * 查表法：
     * 通过代码实际运行规律总结出来
     * n=1-100 运行solution_01，查看数据结果，然后归纳总结
     * //归纳结论
     * for(int i=1;i<100;i++){
     *      System.out.println("数值"+i+",结果"+solution_01(i));
     * }
     * @param n
     * @return
     */
    public static int solution_02(int n){
        //如果是奇数，则返回-1
        if((n & 1) !=0){
            return -1;
        }

        if(n<18){
            return n==0 ? 0:( n==6 || n==8 )? 1
                    :(n==12 || n==14 || n==16) ? 2 :-1;
        }

        //n=18后变的有规律可循
        return (n-18) /8 +3;
    }

}
