package org.example.p22;

/**
 * 将给定的数转换为字符串，原则如下：1对应 a，2对应b，……26对应z，例如12258可以转换为"abbeh", “aveh”, “abyh”, “lbeh” and “lyh”，个数为5，
 * 编写一个函数，给出可以转换的不同字符串的个数。
 */
public class Example_03 {
    public static void main(String[] args) {
        String s="12258";//"249713295212325";
        System.out.println("解法一：可转换的字符串个数为："+solution01(s));
        System.out.println("解法二：可转换的字符串个数为："+solution02(s));
    }

    /**
     * 递归方式
     * 假设求解函数为f(x)，x表示当前位置；g(x)为当前字符转换成那个数值；
     * 若当前位置为字符串的i位置，则i和i之后的字符串转换方式有2种
     * 1）g(i)+ f(i+1)
     * 2）g(i+1)+f(i+2)
     * 最终的结果为2者的和
     * @param s
     * @return
     */
    public static int solution01(String s){
        //如果当前位置是0则无法转换
        if(s.charAt(0)=='0'){
            return 0;
        }

        if(s.length()==1){
            return 1;
        }

        int count=0;
        //当前位置取一个，整体数量由后面的组合决定
        count=solution01(s.substring(1));

        //判断2个字符组合是否大于26
        int num=(s.charAt(0)-'0')*10+(s.charAt(1)-'0');
        if(num>26){
            return count;
        }

        //如果当前字符串只剩2个字符串
        if(s.length()>2){
            return count+solution01(s.substring(2));
        }

        return count+1;
    }

    /**
     * 动态规划（往前找）
     *  dp[i] 表示前i个数字所能转换的不同字符串的个数
     * @param s
     * @return
     */
    public static int solution02(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        //初始化dp数组
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= n; i++) {
            //获取当前位置的数值
            int num = Integer.parseInt(s.substring(i-1, i));
            //获取上一个位置的数值
            int prenum = Integer.parseInt(s.substring(i-2, i-1));
            if (num == 0) {
                if (prenum == 1 || prenum == 2) {
                    dp[i] = dp[i-2];
                } else {
                    return 0;
                }
            } else {
                dp[i] = dp[i-1];
                if (prenum == 1 || (prenum == 2 && num <= 6)) {
                    dp[i] += dp[i-2];
                }
            }
        }
        return dp[n];
    }

}
