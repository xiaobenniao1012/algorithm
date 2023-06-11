package org.example.p23;

/**
 * 实现一个自定义的堆结果，可以限制堆的大小
 */
public class CustomHeap {
    //堆的大小
    private int limit;
    //堆的元素个数
    private int heapSize;
    //堆的元素
    private int[] heap;

    public CustomHeap(int limit) {
        this.limit = limit;
        heap = new int[limit];
        heapSize = 0;
    }

    /**
     * 判断堆是否为空
     * @return
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * 判断堆是否已满
     * @return
     */
    public boolean isFull() {
        return heapSize == limit;
    }

    /**
     * 向堆中添加元素
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            //throw new RuntimeException("heap is full");
            pop();
        }
        heap[heapSize] = value;
        heapInsert(heap, heapSize++);
    }

    /**
     * 弹出堆顶元素
     * @return
     */
    public int pop() {
        if(isEmpty()) {
            return 0;
        }
        int ans = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return ans;
    }

    /**
     * 堆的插入操作
     * @param arr
     * @param index
     */
    public void heapInsert(int[] arr, int index) {
        //如果当前节点的值大于父节点的值，则交换
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            //将当前节点的索引指向父节点的索引
            index = (index - 1) / 2;
        }
    }

    /**
     * 堆的调整操作
     * @param arr
     * @param index
     * @param heapSize
     */
    public void heapify(int[] arr, int index, int heapSize) {
        //左孩子的索引
        int left = index * 2 + 1;
        //右孩子的索引
        int right = index * 2 + 2;
        //最大值
        int largest = index;
        //如果左孩子的值大于最大值，则将最大值的索引指向左孩子的索引
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        //如果右孩子的值大于最大值，则将最大值的索引指向右孩子的索引
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        //如果最大值的索引不等于当前索引，则交换
        if (largest != index) {
            swap(arr, largest, index);
            //递归调用
            heapify(arr, largest, heapSize);
        }
    }

    /**
     * 实现数组位置交换
     * @param arr
     * @param index1
     * @param index2
     */
    public void swap(int[] arr, int index1, int index2) {
        //临时变量
        int tmp = arr[index1];
        //将index2的值赋值给index1
        arr[index1] = arr[index2];
        //将临时变量的值赋值给index2
        arr[index2] = tmp;
    }

}
