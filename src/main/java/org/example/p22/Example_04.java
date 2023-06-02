package org.example.p22;

/**
 * 二叉树每个结点都有一个int型权值，给定一棵二叉树，要求计算出从根结点到叶结点的所有路径中，权值和最大的值为多少。
 */
public class Example_04 {

    private static int maxSum = 0;

    public static void main(String[] args) {
        //初始化树节点
        int[] vals=new int[]{1,3,2,5,6,7,34,23,5,34,85,6,73,4,10,21,50,100};
        TreeNode root=initTree(vals,0);

        System.out.println("解法一：路径最大节点权值和为："+solution01(root));

        System.out.println("解法二：路径最大节点权值和为："+solution02(root));

    }

    /**
     * 从根节点开始遍历，遍历所有节点，遍历过程中将其父节点的权值和作为参数传入
     * @param root
     * @return
     */
    public static int solution01(TreeNode root){
        if (root == null) {
            return 0;
        }
        dfs(root, 0);
        return maxSum;
    }

    /**
     * 方法获取以当前节点为根节点的最大权值和
     * 若当前节点不是叶节点，则获取其左孩子为根节点的最大权值和，同时获取其右孩子为根节点的最大权值和，取其中最大者。
     * 若当前节点为叶节点，则返回其value。
     * @param node
     * @return
     */
    public static int solution02(TreeNode node){
        if(null==node){
            return 0;
        }

        //若当前节点为叶节点，则返回其value。
        if (node.left == null && node.right == null) {
            return node.val;
        }

        int leftVal=0;
        int rightVal=0;
        if(null!=node.left){
            leftVal=node.val+solution02(node.left);
        }
        if(null!=node.right){
            rightVal=node.val+solution02(node.right);
        }
        return leftVal>rightVal?leftVal:rightVal;
    }

    /**
     * 深度遍历
     * @param node
     * @param sum
     */
    private static void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        sum += node.val;
        if (node.left == null && node.right == null) {
            maxSum = Math.max(maxSum, sum);
        }

        dfs(node.left, sum);
        dfs(node.right, sum);
    }

    /**
     * 返回数节点初始化
     * @return
     */
    private static TreeNode initTree(int[] vals,int index) {

        if(null==vals || index>=vals.length){
            return null;
        }

        TreeNode root=new TreeNode(vals[index]);
        //构建左子树
        if(2*index+1<vals.length){
            TreeNode left=initTree(vals,2*index+1);
            root.left=left;
        }
        //构建右子树
        if(2*index+2<vals.length){
            TreeNode right=initTree(vals,2*index+2);
            root.right=right;
        }

        return root;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
