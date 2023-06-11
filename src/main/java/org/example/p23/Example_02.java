package org.example.p23;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Example_02 {
    public static void main(String[] args) {
        int[][] matrix=new int[][]{
                {1,2,3,4},
                {12,13,14,5},
                {11,16,15,6},
                {10,9,8,7}
        };

        List<Integer> result=solution(matrix);

        System.out.println("螺旋打印结果为："+result.stream().map(Objects::toString).collect(Collectors.joining(",")));
    }

    public static List solution(int[][] matrix){
        List result = new ArrayList();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        //m表示行，n表示列
        int m = matrix.length, n = matrix[0].length;
        //分别定位左上角和右下角
        int startX = 0, startY = 0, endX = m - 1, endY = n - 1;
        while (startX <= endX && startY <= endY) {
            // 从左到右打印起始行上的元素
            for (int j = startY; j <= endY; j++) {
                result.add(matrix[startX][j]);
            }
            // 从上到下打印结束列上的元素
            for (int i = startX + 1; i <= endX; i++) {
                result.add(matrix[i][endY]);
            }
            // 从右到左打印结束行上的元素，startX和endX不在同一列上则打印
            if (startX != endX) {
                for (int j = endY - 1; j >= startY; j--) {
                    result.add(matrix[endX][j]);
                }
            }
            // 从下到上打印起始列上的元素，startY和endY不在同一行上则打印
            if (startY != endY) {
                for (int i = endX - 1; i > startX; i--) {
                    result.add(matrix[i][startY]);
                }
            }
            // 更新起始位置和结束位置
            startX++;
            startY++;
            endX--;
            endY--;
        }
        return result;
    }
}
