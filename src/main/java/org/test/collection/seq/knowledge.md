有序表： p6 01:01:00

1、有序表理解为一种集合结构
2、java中TreeMap就是一个有序表，要求key必须是可排序的
3、常见的有序表：红黑树、AVL树、SBT（size-balance-tree，平衡二叉查找树）和跳表都属于有序表结构，只是底层实现不同。
4、有序表的相关操作一般是O(logN)级别。
5、相关操作：
增put、查get、删remove、判断是否存在containsKey、获取最小firstKey、获取最大lastKey、大于某个值的最小值floorKey(k)、小于某个值的最大值ceilingKey(k)