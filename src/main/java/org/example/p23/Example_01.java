package org.example.p23;

public class Example_01 {

    public static void main(String[] args) {
        //数组长度为机器数量，数组中每一个位置的数值代表放置的物品数量
        int[] machines=new int[]{3,2,3,4,15,6,7,8};

        System.out.println("移动轮数为："+solution(machines));
    }

    /**
     * 计算轮数
     * @param machines
     * @return
     */
    public static int solution(int[] machines){
        int n = machines.length;

        int total = 0;
        for (int i = 0; i < n; i++) {
            total += machines[i];
        }
        //判断是否可以平均分配
        if (total % n != 0) {
            return -1;
        }

        //每个机器上的物品数量
        int target = total / n;

        int moves = 0, leftSum = 0;
        for (int i = 0; i < n; i++) {
            //左侧需要的物品数量
            int leftMovs=leftSum - target+i;
            //右侧需要的物品数量
            int rightMovs= (total-leftSum-machines[i]) -(n-i-1)*target;

            if(leftMovs<0 && rightMovs<0){
                //比较左右2侧移轮数和
                moves=Math.max(moves,Math.abs(leftMovs)+Math.abs(rightMovs));
            }else {
                //获取左右2侧最大值
                moves=Math.max(moves,Math.max(Math.abs(leftMovs),Math.abs(rightMovs)));
            }
            leftSum += machines[i];
        }
        return moves;
    }
}
