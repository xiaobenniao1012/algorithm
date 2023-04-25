package org.test.collection.list;

import java.util.Stack;

/**
 * 单链表
 * 实现单链表的逆序操作
 * 思路1：使用3个指针：last，mid，next（分别定义为l,m,n）
 * 思路2：递归反转。从链表的尾节点开始，依次向前遍历，遍历过程依次改变各节点的指向，即另其指向前一个节点
 * 思路3：头插入反转链表，依次将位于链表头部的节点摘下，然后采用从头部插入的方式生成一个新链表，则此链表即为原链表的反转版。
 * 思路4：利用栈实现反转
 *
 * 参考：http://c.biancheng.net/view/8105.html
 */
public class SingleList {

    public static void main(String[] args) {
        //初始化单链表
        Node head=initSingleList();
        output(head);
        System.out.println();

        //3指针逆序
        System.out.println("3指针方式逆序链表：");
        Node head1=threePointReverseList(head);
        output(head1);
        System.out.println();

        //递归逆序
        System.out.println("递归方式逆序链表：");
        head=initSingleList();
        Node head2=recusiveReverse(head);
        output(head2);
        System.out.println();

        //头部插入
        System.out.println("头部插入方式逆序链表：");
        head=initSingleList();
        Node head3=headInsert(head);
        output(head3);
        System.out.println();

        //利用栈结构
        System.out.println("栈结构方式逆序链表：");
        head=initSingleList();
        Node head4=headStack(head);
        output(head4);
    }

    /**
     * 利用栈，将节点逆序
     * @param head
     * @return
     */
    private static Node headStack(Node head) {

        if(null==head || head.next==null){
            return head;
        }

        Stack<Node> stack=new Stack<>();
        Node n=head;
        //将链表节点全部压入栈中
        while (null!=n){
            stack.push(n);
            n=n.next;
        }

        head=stack.pop();
        n=head;
        while (!stack.isEmpty()){
            n.next=stack.pop();
            n=n.next;
        }
        n.next=null;

        return head;
    }

    /**
     * 头部插入法
     * @param head
     * @return
     */
    public static Node headInsert(Node head){
        //定义新的头节点
        Node newHead=null;

        //用于遍历的指针，指向从原链表中摘除的节点
        Node temp=null;
        if(head==null || null==head.next){
            return head;
        }

        while (null!=head){
            //向将节点从原链表中移除
            temp=head;
            head=head.next;

            //挂接temp节点关节到新的链表中
            temp.next=newHead;
            newHead=temp;
        }

        return newHead;
    }

    /**
     * 递归处理
     * @param head
     * @return
     */
    public static Node recusiveReverse(Node head){
        //遍历到最后一个节点后返回
        if(null==head || null==head.next){
            return head;
        }

        //newHead为最后一个节点，而head为上一个节点
        Node newHead=recusiveReverse(head.next);

        //这里不要改变newHead的指针，每次递归出口都在这段代码处
        /*newHead.next=head;
        head.next=null;*/
        head.next.next=head;
        head.next=null;

        return newHead;
    }

    /**
     * 三指针逆序链表
     * @param head
     * @return
     */
    public static Node threePointReverseList(Node head){
        if(head==null || null==head.next){
            return head;
        }

        Node last=null;
        Node mid=head;
        // next主要用于控制循环
        Node next=head.next;

        while(true){
            //逆序
            mid.setNext(last);

            if(null==next){
                break;
            }

            //从前向后依次调整位置
            last=mid;
            mid=next;
            next=next.getNext();
        }

        return mid;
    }

    /**
     * 打印
     * @param head
     */
    public static void output(Node head){
        Node n=head;

        while (null!=n){
            System.out.print(n.getValue()+"-->");
            n=n.next;
        }

        System.out.print("Null");
    }

    /**
     * 初始化链表
     * @return
     */
    public static Node initSingleList(){
        Node node1=new Node(1,null);
        Node node2=new Node(2,null);
        Node node3=new Node(3,null);
        Node node4=new Node(4,null);

        //建立关联
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;

        Node head=node1;

        return head;
    }

    /**
     * 定义单链表节点
     */
    static class Node{

        public Node(Integer value, Node next) {
            this.value = value;
            this.next = next;
        }

        private Integer value;
        private Node next;

        public Integer getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }
}