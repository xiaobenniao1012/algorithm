package org.example.p22;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class Example_01 {
    public static void main(String[] args) {
        String s="()((())()))()())()";
        System.out.println("最长有效子串长度为："+solution(s));
    }

    public static int solution(String s){
        int maxLen = 0;
        int[] dp = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (s.charAt(i-1) == '(') {
                    dp[i] = (i >= 2 ? dp[i-2] : 0) + 2;
                } else if (i - dp[i-1] > 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                    //i - dp[i-1] > 0 防止越界，s.charAt(i - dp[i-1] - 1) == '('，向前推有效位置后，是否为左括号
                    dp[i] = dp[i-1] + (i - dp[i-1] >= 2 ? dp[i-dp[i-1]-2] : 0) + 2;
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }
        return maxLen;
    }
}
