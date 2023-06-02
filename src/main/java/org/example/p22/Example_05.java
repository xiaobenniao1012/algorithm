package org.example.p22;

/**
 * 给定一个元素为非负整数的二维数组matrix，每行和每列都是从小到大有序的。再给定一个非负整数aim，请判断aim是否在matrix中。
 */
public class Example_05 {

    public static void main(String[] args) {
        int[][] matrix=new int[][]{
                {1,3,5,6,8,10},
                {2,4,6,7,9,11},
                {5,7,9,10,11,15}
        };

        System.out.println("查找11是否在矩阵中："+solution(matrix,11));
        System.out.println("查找12是否在矩阵中："+solution(matrix,12));
    }

    /**
     * 从左下角开始查找
     * @param matrix
     * @param aim
     * @return
     */
    public static boolean solution(int[][] matrix,int aim){
        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }

        int cols = matrix[0].length;
        int row = rows - 1, col = 0;

        //从左下角开始遍历
        while (row >= 0 && col < cols) {
            if (matrix[row][col] == aim) {
                return true;
            } else if (matrix[row][col] < aim) {
                //移动列向后，向值增大的列方向移动
                col++;
            } else {
                //移动行，向值减小的行方向移动
                row--;
            }
        }
        return false;
    }
}
