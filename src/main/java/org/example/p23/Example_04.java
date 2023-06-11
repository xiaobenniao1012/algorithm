package org.example.p23;

/**
 * 用zigzag的方式打印矩阵
 */
public class Example_04 {

    public static void main(String[] args) {
        //定义矩阵
        int[][] matrix = new int[5][5];
        //给矩阵赋值
        int num = 1;
        for (int i = 0; i < matrix.length; i++) {
            int[] arr = matrix[i];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = num++;
            }
        }

        //打印矩阵
        for (int[] arr : matrix) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

        //zigzag打印矩阵
        printMatrixZigZag(matrix);
    }

    private static void printMatrixZigZag(int[][] matrix) {
        //定义2个点，A点和B点
        int aR = 0;
        int aC = 0;
        int bR = 0;
        int bC = 0;

        //定义一个标识，用来判断是从上往下打印还是从下往上打印
        boolean fromUp = false;

        //A点的行数不能超过矩阵的行数
        while (aR < matrix.length) {
            //打印A点和B点
            printLevel(matrix, aR, aC, bR, bC, fromUp);
            //A点的列数不能超过矩阵的列数
            aR = aC == matrix[0].length - 1 ? aR + 1 : aR;
            //A点的列数不能超过矩阵的列数
            aC = aC == matrix[0].length - 1 ? aC : aC + 1;
            //B点的行数不能超过矩阵的行数
            bC = bR == matrix.length - 1 ? bC + 1 : bC;
            //B点的行数不能超过矩阵的行数
            bR = bR == matrix.length - 1 ? bR : bR + 1;
            //改变标识
            fromUp = !fromUp;
        }
    }

    private static void printLevel(int[][] matrix, int aR, int aC, int bR, int bC, boolean fromUp) {
        //定义一个临时变量，用来判断是从上往下打印还是从下往上打印
        if (fromUp) {
            //从上往下打印
            while (aR <= bR) {
                System.out.print(matrix[aR++][aC--] + " ");
            }
        } else {
            //从下往上打印
            while (bR >= aR) {
                System.out.print(matrix[bR--][bC++] + " ");
            }
        }
    }
}
