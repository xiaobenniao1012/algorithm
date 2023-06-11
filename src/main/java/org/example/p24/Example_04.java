package org.example.p24;

/**
 * 给你一个二维数组matrix，其中每个数都是正数，要求从左上角走到右下角。每一步只能向右或者向下，沿途经过的数字要累加起来。最后请返回最小的路径和。
 */
public class Example_04 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 5, 9},
                {8, 1, 3, 4},
                {5, 0, 6, 1},
                {8, 8, 4, 0}
        };
        System.out.println(minPathSum(matrix));
    }

    /**
     * 利用动态规划求解出最后一个位置处的最小路径和
     * 其中dp[i][j]表示从左上角走到(i, j)位置处的最小路径和
     * @param matrix
     * @return
     */
    public static int minPathSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];

        // 初始化第一行
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < col; i++) {
            dp[0][i] = matrix[0][i] + dp[0][i - 1];
        }

        // 初始化第一列
        for (int i = 1; i < row; i++) {
            dp[i][0] = matrix[i][0] + dp[i - 1][0];
        }

        // 递推
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++){
                dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[row - 1][col - 1];
    }

    /**
     * 使用滚动数组优化空间复杂度
     */
    public static int minPathSum2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        // 为了节省空间，我们只用一个一维数组来保存结果
        int[] dp = new int[col];

        // 初始化第一行
        dp[0] = matrix[0][0];
        for (int i = 1; i < col; i++) {
            dp[i] = matrix[0][i] + dp[i - 1];
        }

        // 递推
        for (int i = 1; i < row; i++) {
            // 第一列的值需要单独计算
            dp[0] = matrix[i][0] + dp[0];
            for (int j = 1; j < col; j++) {
                dp[j] = matrix[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }

        return dp[col - 1];
    }
}
