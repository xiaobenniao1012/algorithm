package org.example.p21;

/**
 * 给定一个非负整数n，代表二叉树的节点个数。返回能形成多少种不同的二叉树结构。
 *
 */
public class Example_01 {

    public static void main(String[] args) {
        System.out.println("5个节点能组成："+solution_01(5)+"棵树");
        System.out.println("5个节点能组成："+solution_02(5)+"棵树");
    }

    /**
     * 利用递归思路
     */
    public static int solution_01(int n){
        if(n<0){
            return -1;
        }
        if(n<2){
            //空节点和1个节点都只有一种树
            return 1;
        }
        if(n==2){
            return 2;
        }

        int res=0;
        //l表示左侧树的节点个数
        for(int l=0;l<=n-1;l++){
            int left=solution_01(l);
            int right=solution_01(n-l-1);
            res+=left*right;
        }

        return res;
    }

    /**
     * 动态规划
     */
    public static int solution_02(int n){
        if(n<2){
            return 1;
        }

        int[] dp=new int[n+1];
        //初始化dp[0]
        dp[0]=1;

        // i表示节点个数
        for(int i=1;i<=n;i++){
            //l表示树的左节点个数
            for(int l=0;l<=i-1;l++){
                //i个节点的个数=左侧节点个数*右侧节点个数
                dp[i] += dp[l]*dp[i-l-1];
            }
        }

        return dp[n];
    }
}
