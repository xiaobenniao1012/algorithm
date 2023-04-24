package org.test.bit;

/**
 * 常见的位操作
 * 1：交换2个数值
 * 2：获取一个数最右侧为1的数值
 * 3：二分法取中
 * 4：待补充
 */
public class BitOperate {

    public static void main(String[] args) {
        //一、数值交换
        swap(5,3);
        //二、获取一个数最右侧(低位)为1的值，其余位全变成0
        getRightSideIs1Value(6);
        //三、数组二分法取中
        getMidPosition(new int []{1,2,3,4,5,6,7,8});
    }

    /**
     * 交换a、b的值
     * @param a
     * @param b
     */
    public static void swap(int a,int b){
        System.out.println("交换数值之前，2数的值分别为，a="+a+",b="+b);

        a=a^b;
        b=a^b;
        a=a^b;

        System.out.println("交换数值之后，2数的值分别为，a="+a+",b="+b);
    }

    /**
     * 获取一个数值最右侧bit位=1的整数（其余位置更新成0）
     * 5的二进制
     * @param a
     * @return
     */
    public static int getRightSideIs1Value(int a){
        System.out.println("原数据值为，a="+a+",其二进制为："+outputBit(a));
        //取反加1后，保证原数值最右侧bit位变成1
        int b=a & (~a +1);
        System.out.println("获取最右侧bit位为1的数值，b="+b+",其二进制为："+outputBit(b));
        return b;
    }

    /**
     * 获取数组中的中间位置
     * @param arr
     */
    private static void getMidPosition(int[] arr) {
        if(null==arr || arr.length==0){
            return;
        }

        System.out.println("数组内容："+outputArray(arr));

        int left=0;
        int right=arr.length-1;

        int mid=left+((right-left)>>1);

        System.out.println("数组中中间位置为,mid="+mid+",对应的值为："+arr[mid]);
    }

    /**
     * 输出数值的二进制bit位信息
     * 0x80000000是数的十六进制表示，转成二进制表示为10000000000000000000000000000000
     * 运算的优先级，移位运算高于逻辑运算，如>>>高于&
     * 位逻辑与运算 1&1 = 1 ，0&1 = 0
     * >>>无符号右移，移出部分舍弃，左边位补0；
     * @param a
     * @return
     */
    private static String outputBit(int a) {
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<32;i++){
            int t=(a & 0x80000000>>>i ) >>> (31-i);
            builder.append(t);
        }
        return builder.toString();
    }

    /**
     * 输出数组
     * @param arr
     */
    private static String outputArray(int[] arr){
        if(null==arr || arr.length==0){
            return null;
        }

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("[");
        for(int i=0;i<arr.length;i++){
            if(i==arr.length-1){
                stringBuilder.append(arr[i]);
            }else {
                stringBuilder.append(arr[i]+",");
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

}