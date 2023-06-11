package org.example.p24;

/**
 * 给定一个数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器， 请返回容器能装多少水
 * 比如，arr = {3，1，2，5，2，4}，根据值画出的直方图就是容器形状，该容器可以装下5格水
 * 再比如，arr = {4，5，1，3，2}，该容器可以装下2格水
 */
public class Example_05 {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 2, 5, 2, 4};
        System.out.println(getWater(arr));
    }

    /**
     * 双指针移动法
     * @param arr
     * @return
     */
    public static int getWater(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }

        //定义左右指针
        int left = 1;
        int right = arr.length - 2;
        //定义左右最大值
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];
        //定义水量
        int water = 0;

        //左右2侧指针相遇时停止
        while (left <= right) {
            //如果左侧最大值小于右侧最大值，说明左侧可以装水
            if (leftMax <= rightMax) {
                water += Math.max(0, leftMax - arr[left]);
                //更新左侧最大值
                leftMax = Math.max(leftMax, arr[left++]);
            } else {
                water += Math.max(0, rightMax - arr[right]);
                //更新右侧最大值
                rightMax = Math.max(rightMax, arr[right--]);
            }
        }

        return water;
    }
}
