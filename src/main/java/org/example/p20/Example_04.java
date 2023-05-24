package org.example.p20;

import java.util.Arrays;

/**
 * 牛牛有一些排成一行的正方形。每个正方形已经被染成红色或者绿色。牛牛现在可 以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将会被覆盖。
 * 牛牛的目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近（R在前，G在后）。 牛牛想知道他最少需要涂染几个正方形。
 * 一行字符串s，由R和G构成，表示正方形的颜色（R为红色，G为绿色）|s|，求一个数最少需要涂染几个正方形。
 * 输入：RGGGR，结果：1 （将最后的R染成G）
 */
public class Example_04 {
    public static void main(String[] args) {
        System.out.println("字符串GGRRR需要染色个数为："+solution_01("GGRRR"));

        System.out.println("字符串GGRRR需要染色个数为："+solution_02("GGRRR"));

    }

    public static int solution_01(String s){
        char[] arr=s.toCharArray();
        int n=s.length();

        int min=Integer.MAX_VALUE;
        for(int i=0;i<=n;i++){
            int cnt=0;
            if(i==0){
                //右侧全部变成G，查找数组中有多少个R
                cnt=countCharacters(arr,'R');
            } else if(i==n){
                //左侧全部变成R，查找数组中左侧有多少个G
                cnt=countCharacters(arr,'G');
            }else{
                //当前位置之前为R，当前位置和之后的位置全部变成G需要多少次染色
                char[] left= Arrays.copyOfRange(arr,0,i);
                char[] right=Arrays.copyOfRange(arr,i,n);
                //左侧找G变成R，右侧找R变成G
                cnt=countCharacters(left,'G')+countCharacters(right,'R');
            }

            min= Math.min(min,cnt);
        }

        return min;
    }

    public static int solution_02(String s){
        int n = s.length();
        // 记录当前位置左边R的个数(包括当前位置）
        int[] leftR = new int[n];
        // 记录当前位置右边G的个数（包括当前位置）
        int[] rightG = new int[n];
        // 左侧R的个数
        int rCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'R') {
                rCount++;
            }
            leftR[i] = rCount;
        }
        // 右侧G的个数
        for (int i = n-1; i >= 0; i--) {
            //数组上一个位置的下标，防止边界溢出
            int index=Math.min(i+1, n-1);
            if (s.charAt(i) == 'G') {
                rightG[i] = rightG[index] + 1;
            } else {
                rightG[i] = rightG[index];
            }
        }

        int ans = Integer.MAX_VALUE;
        //需要考虑i=n边界问题
        for (int i = 0; i <= n; i++) {
            int cnt=0;
            if(i==n){
                //把左侧的G全变成R
                cnt=n-leftR[n-1];
            }else{
                // 将当前位置的R染成G，前面的位置染成R，后面的位置染成G，所需的涂染次数
                //i+1指当前位置和之前的元素个数
                int left=(i+1)-leftR[i];
                int right=n-(i+1)-rightG[i];
                int now=(s.charAt(i) == 'R' ? 1 : 0);
                cnt = left+right+now;
            }

            ans = Math.min(ans, cnt);
        }
        return ans;
    }

    /**
     * 统计字符的个数
     * @param arr
     * @param c
     * @return
     */
    private static int countCharacters(char[] arr,char c){
        if(null==arr || arr.length==0){
            return 0;
        }

        int count=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==c){
                count++;
            }
        }
        return count;
    }
}
