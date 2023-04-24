package org.test.bit;

public class Sample {

    public static void main(String[] args) {
        int a=1239;
        //00000000000000000000010011010111
        System.out.println("整型数值"+a+"的二进制为："+printBit(a));

        //5在数组中只出现了1次，其他数都出现了偶数次
        int[] arr=new int[]{1,1,2,4,2,5,4,6,6,7,2,7,2};
        System.out.println("数组中出现奇数次的数值为："+oddNumber(arr));

        //5和8在数组中只出现了1次，其他数都出现了偶数次
        int[] arr2=new int[]{1,1,2,4,2,5,4,6,6,7,2,7,8,2};
        int[] result=oddNumber2(arr2);
        System.out.println("数组中出现奇数次的2个数，分别为："+result[0]+"和"+result[1]);
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
    private static String printBit(int a) {
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<32;i++){
            int t=(a & 0x80000000>>>i ) >>> (31-i);
            builder.append(t);
        }
        return builder.toString();
    }

    /**
     * 数组中只有一个数出现奇数次，其他数出现偶数次。从数组中找到只出现奇数次的数值
     * 利用异或的性质，一个值异或自己结果等于0；0异或任何一个值都等于该值
     * 思路：使用数值k=0，k异或数组的所有元素，最终结果就是这个出现奇数次的数值。
     * @param arr
     * @return
     */
    private static int oddNumber(int[] arr){
        int k=0;
        for(int nmuber:arr){
            k^=nmuber;
        }

        return k;
    }

    /**
     * 数组中只有2个数出现奇数次，其他数都出现偶数次，从数组中找到只出现奇数次的数值
     * 思路：假设数组中出现奇数次的数据为a和b。
     * 第一步：k=0,k异或数组的所有元素，结果为k=a^b;
     * 第二步：提取出k(即a^b的结果)值最右侧为1的数值：right_1=k & (~k +1)。 -- 固定算法
     * 第三步：再一次执行异或操作，只是这一次执行时与上right_1等于1的记录。执行完成后 a= right_1异或数组的所有元素，则能获取到a或者b。 -- 这里暂时设置为 a
     * 第四步：使用k和a 异或，结果必然是a或者b。另外一个值则再与k异或即可。
     */
    private static int[] oddNumber2(int[] arr){
        int[] result=new int[2];

        //第一步：获取a^b
        int k=0;
        for(int number:arr){
            k ^= number;
        }
        //第二步：right_1=k & (~k +1)
        int right_1= k & (~k +1);

        //第三步：获取a或者b，0和任何数异或都等于该数
        int a=0;
        for(int number:arr){
            //筛选数据，同时与a进行与操作
            if( (right_1 & number) ==1){
                a ^= number;
            }
        }

        //第四步：获取另外一个值
        int b = a ^ k;

        result[0]=a;
        result[1]=b;

        return result;
    }
}