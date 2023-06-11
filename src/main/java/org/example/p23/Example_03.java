package org.example.p23;

/**
 * 给定一个正方形矩阵，只用有限几个变量，实现矩阵中每个位置的数顺时针转动
 */
public class Example_03 {
    public static void main(String[] args) {
        //定义一个二维数组
        int[][] matrix = new int[5][5];
        //给二维数组赋值
        int num = 1;
        for (int i = 0; i < matrix.length; i++) {
            int[] arr = matrix[i];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = num++;
            }
        }

        //旋转二维数组
        rotate(matrix);
    }

    public static void rotate(int[][] matrix) {
        //左上角的点
        int tR = 0;
        int tC = 0;
        //右下角的点
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;

        //左上角的点和右下角的点不断向中间靠拢
        while (tR < dR) {
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }
    /**
     * 旋转一圈
     * @param m 二维数组
     * @param tR 左上角的点
     * @param tC
     * @param dR
     * @param dC
     */
    public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
        //一圈的长度也称为分组次数
        int times = dC - tC;
        int tmp = 0;
        //处理每组的元素转换，理解为一个正方形，每次转换四个点
        for (int i = 0; i < times; i++) {
            //左上角点赋值给临时变量
            tmp = m[tR][tC + i];
            //左下角点赋值给左上角点
            m[tR][tC + i] = m[dR - i][tC];
            //右下角点赋值给左下角点
            m[dR - i][tC] = m[dR][dC - i];
            //右上角点赋值给右下角点
            m[dR][dC - i] = m[tR + i][dC];
            //临时变量赋值给右上角点
            m[tR + i][dC] = tmp;
        }
    }
}
