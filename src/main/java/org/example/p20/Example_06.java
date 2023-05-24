package org.example.p20;

/**
 * 给定一个函数f，可以1-5的数字等概率返回一个。请加工出一个函数g可以等概率返回1-7的数字。
 * 给定一个函数f，可以a-b的数字等概率返回一个。请加工出一个函数g可以等概率返回c-d的数字。
 * 给定一个函数f，以p概率返回0，以1-p概率返回1。请加工出一个函数g可以等概率返回0和1。
 */
public class Example_06 {
    public static void main(String[] args) {
        for(int i=1;i<=5;i++){
            System.out.println("g函数第"+i+"次返回数字："+g());
        }


        for(int i=1;i<=5;i++){
            System.out.println("g2函数第"+i+"次返回数字："+g2());
        }
    }

    /**
     * f函数，返回1-5的等概率函数
     * @return
     */
    public static int f(){
        return (int)(Math.random()*5) +1;
    }

    /**
     * 返回0或1等概览函数
     * @return
     */
    public static int f1(){
        int result=0;
        do{
            result=f();
        }while (result == 3);

        return result<3 ? 0 : 1;
    }

    /**
     * 等概率返回1-7的数字
     * @return
     */
    public static int g(){
        int result=0;
        do{
            result= (f1() << 2) + (f1() << 1) +f1();
        }while (result==0);

        return result;
    }

    /**
     * 随机返回0和1（以p概率返回0，以1-p概率返回1）
     * @return
     */
    public static int p() {
        return (int) (Math.random() * 2);
    }

    /**
     * 等概率返回0和1
     *
     * {0,1}则定义为0
     * {1,0}则定义为1
     * @return
     */
    public static int g2(){
        int first=0;
        int secord=0;

        do{
           first=p();
           secord=p();
        }while (first+secord!=1);

        return first==0 ? 0 : 1;
    }

}
