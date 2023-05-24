package org.example.p20;

/**
 * 给定一个有序数组arr，代表数轴上从左到右有n个点arr[0]、arr[1]...arr[n-1]，给定一个正数L，代表一根长度为L的绳子，求绳子最多能覆盖其中的几个点。
 * 假设：L=5，数组arr=[2,4,8,9,10,12,17]，最大能覆盖4个点
 */
public class Example_01 {

    public static void main(String[] args) {
        int len=5;
        int[] arr=new int[]{2,4,8,9,10,12,17};

        System.out.println("遍历方式-最大覆盖点："+solution_01(arr,len));

        System.out.println("滑动窗口方式-最大覆盖点："+solution_02(arr,len));
    }

    /**
     * 遍历元素方式
     * 初始化最多覆盖点数maxCount为0。
     * 对于数组中的每一个点arr[i]，从该点开始向右找到最远能够覆盖的点，即满足arr[j]-arr[i]<=L的最大的j。
     * 计算覆盖的点数count=j-i+1。
     * 如果count大于maxCount，则更新maxCount为count。
     * 返回maxCount。
     */
    public static int solution_01(int[] arr, int L){
        int n = arr.length;
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && arr[j] - arr[i] <= L) {
                j++;
            }
            int count = j - i;
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    /**
     * 滑动窗口方式
     * 初始化左右指针left和right为0。
     * 初始化最多覆盖点数maxCount为0。
     * 当right指针小于数组长度n时，循环执行以下步骤：
     *  a. 如果right指针指向的点与左端点的距离小于等于L，则右移right指针。
     *  b. 否则，左移left指针。
     *  c. 计算当前窗口能够覆盖的点数count=right-left+1。
     *  d. 如果count大于maxCount，则更新maxCount为count。
     * 返回maxCount。
     */
    public static int solution_02(int[] arr,int L){
        int n = arr.length;
        int left = 0, right = 0;
        int maxCount = 0;
        while (right < n) {
            if (arr[right] - arr[left] <= L) {
                right++;
            } else {
                left++;
            }

            int count = right - left + 1;
            //赋值时仍然需要再判断一下，因为后续有+的操作
            if (count > maxCount && arr[right] - arr[left] <= L) {
                maxCount = count;
            }
        }
        return maxCount;
    }
}
