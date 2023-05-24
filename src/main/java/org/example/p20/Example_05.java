package org.example.p20;

/**
 * 给定一个N*N的矩阵matrix，只有0和1两种值，返回边框全是1的最大正方形的边长长度。
 * 例如
 * 01111
 * 01001
 * 01001
 * 01111
 * 01011
 * 其中边框全是1的最大正方形的大小为4*4，所以返回4.
 */
public class Example_05 {

    public static void main(String[] args) {
        int[][] matrix=new int[][]{
                {0,1,1,1,1},
                {0,1,0,0,1},
                {0,1,0,0,1},
                {0,1,1,1,1},
                {0,1,0,1,1}
        };

        System.out.println("矩阵最大正方形边长大小为："+solution_01(matrix));

        System.out.println("矩阵最大正方形边长大小为："+solution_02(matrix));
    }

    /**
     * 选取一个点N（n,m），这个点作为正方形的左上角，可以有多少个正方形，然后看里面的正方形边长都是1的情况。
     * @param matrix
     * @return
     */
    public static int solution_01(int[][] matrix){
        int len=matrix.length;

        int count=0;
        //2层循环控制点的选取（i表示行，j表示列）
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(matrix[i][j]==0){
                    continue;
                }

                //到达边界的长度
                int board=Math.min(len-i,len-j);
                //k表示变的长度
                for(int k=0;k<board;k++){
                    if(isBoardEqualOne(matrix,i,j,k)){
                        count=Math.max(count,k+1);
                    }
                }
            }
        }
        return count;
    }

    /**
     * 预处理方式
     * @param matrix
     * @return
     */
    public static int solution_02(int[][] matrix){
        int len=matrix.length;
        //right矩阵：从右到左、再从下到上，生成每个点（包含该点）右侧有几个连续的1（只看右侧）
        int[][] right=new int[len][len];
        for(int i=len-1;i>=0;i--){
            for(int j=len-1;j>=0;j--){
                if(matrix[i][j]==1){
                    if(j!=len-1){
                        right[i][j]=1+right[i][j+1];
                    }else{
                        right[i][j]=1;
                    }
                }
            }
        }
        /*System.out.println("right矩阵：");
        output(right);*/


        //down矩阵：从下到上，从右到左，生成每个点（包含该点）下方有几个连续的1（只看下方）
        int[][] down=new int[len][len];
        for(int i=len-1;i>=0;i--){
            for(int j=len-1;j>=0;j--){
                if(matrix[j][i]==1){
                    if(j!=len-1){
                        down[j][i]=1+down[j+1][i];
                    }else{
                        down[j][i]=1;
                    }
                }
            }
        }

        /*System.out.println("down矩阵：");
        output(down);*/

        //找到3个点，分别看这3个点的（左上点A、左下点B、右上点C）
        int count=0;
        //2层循环控制点的选取（i表示行，j表示列）
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(matrix[i][j]==0){
                    continue;
                }

                //到达边界的长度
                int board=Math.min(len-i,len-j);
                //k表示变的长度
                for(int k=0;k<board;k++){
                    if(k==0){
                        count=Math.max(count,1);
                    }else{
                        int aRight=right[i][j];
                        int aDown=down[i][j];

                        int bRight=right[i+k][j];

                        int cDown=down[i][j+k];

                        if(aRight>=k && aDown>=k && bRight>=k & cDown>=k){
                            count=Math.max(count,k+1);
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * 判断矩阵的边是否都为1
     * @return
     */
    private static boolean isBoardEqualOne(int[][] matrix,int x,int y,int length){

        for(int i=1;i<length;i++){
            //横向节点判断
            boolean flag= matrix[x+i][y]==1 && matrix[x][y+1]==1 && matrix[x+i][y+length]==1;
            if(!flag){
                return false;
            }
        }
        return true;
    }

    /**
     * 输出矩阵
     * @param matrix
     */
    private static void output(int[][] matrix){
        int len=matrix.length;
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                System.out.print(matrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
