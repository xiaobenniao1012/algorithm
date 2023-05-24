package org.example.p20;

/**
 * 假设现在有两只动物吃草，每次只能吃4的次幂的草，也就是1、4、16等等，现在，给你一个整数N，代表总共有多少草，两只动物，只能交替吃草，有先手和后手区别，
 * 假设动物都绝顶聪明，哪只动物先吃完草，哪只就赢，请你求出N份草时，是先手赢还是后手赢？
 * 例如：n=6，则先手赢；n=7则先手输
 */
public class Example_03 {

    public static void main(String[] args) {
        int n=6;

        System.out.println("1先手是否赢："+solution_01(n,true));

        System.out.println("2先手是否赢："+solution_02(n,true));

        //总结规律
        /*for(int i=0;i<100;i++){
            System.out.println("先手是否赢-数值："+i+","+solution_02(i,true));
        }*/
        System.out.println("3先手是否赢："+solution_03(n,true));
    }

    /**
     *
     *
     * isMyTurn 表示当前是否为先手
     */
    public static boolean solution_01(int n, boolean isMyTurn){

        // n=0当前动物无法再吃草，另一只动物获胜
        if(n<5){
            return (n==0 || n==2) ? false : true;
        }

        for (int i = 0; Math.pow(4, i) <= n; i++) {
            int x = (int)Math.pow(4, i);
            // 剩余量为：n-x；在n-x剩余中当前动物为后手，因此传递!isMyTurn
            if (!solution_01(n - x, !isMyTurn)) {
                // 另一只动物无法获胜，当前动物可以在这种情况下获胜（这里是递归，只要是返回false，即另一只尝试后无法取胜）
                return true;
            }
        }
        return false;
    }

    /**
     * 如果当前剩余草量 N 为 0，则表示当前动物无法再吃草，此时另一只动物获胜。
     * 如果当前剩余草量 N 不为 0，则当前动物可以选择吃 1、4、16...中的任意一个数量的草。
     * 如果当前动物选择吃 x 株草，则剩余草量为 N - x，交换角色后，另一只动物需要在剩余草量为 N - x 的情况下继续尝试赢得胜利。
     * 如果另一只动物无法获胜，则表示当前动物可以在这种情况下获胜。
     * Math.pow 容易出现溢出，将其修改为除法
     */
    public static boolean solution_02(int n, boolean isMyTurn){

        if(n<5){
            // n=0当前动物无法再吃草，另一只动物获胜
            return (n==0 || n==2) ? false : true;
        }

        int x=1;
        for (int i = 0; x <= n; i++) {

            // 剩余量为：n-x；在n-x剩余中当前动物为后手，因此传递!isMyTurn
            if (!solution_02(n - x, !isMyTurn)) {
                // 另一只动物无法获胜，当前动物可以在这种情况下获胜（这里是递归，只要是返回false，即另一只尝试后无法取胜）
                return true;
            }

            if(x > n/4){
                break;
            }
            x *=4;
        }
        return false;
    }

    /**
     * 总结规律
     * for(int i=0;i<100;i++){
     *      System.out.println("先手是否赢-数值："+i+","+solution_02(i,true));
     *  }
     */
    public static boolean solution_03(int n,boolean isMyTurn){

        if(n%5==0 || n%5==2){
            return false;
        }else{
            return true;
        }

    }
}
