// 定义一个泛型类LinkedListDeque，使用泛型T，可以存储任意类型的数据。
public class LinkedListDeque<T> {
    // 内部类Node，用于表示链表中的节点。
    private class Node {
        T item; // 节点存储的数据
        Node prev; // 指向前一个节点的引用
        Node next; // 指向下一个节点的引用

        // Node的构造函数，初始化节点的数据和前后节点的引用
        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel; // 哨兵节点，用于简化在链表头尾操作的复杂度
    private int size; // 链表的大小，即存储的元素数量

    // LinkedListDeque的构造函数，初始化一个空的双向循环链表
    public LinkedListDeque() {
        sentinel = new Node(null, null, null); // 创建哨兵节点，其前后节点都指向自己
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0; // 初始化链表大小为0
    }

    // 在链表头部添加一个新元素
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next); // 创建新节点，插入到哨兵节点和原首节点之间
        sentinel.next.next.prev = sentinel.next; // 更新原首节点的前驱引用
        size++; // 链表大小增加
    }

    // 在链表尾部添加一个新元素
    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel); // 创建新节点，插入到哨兵节点和原尾节点之间
        sentinel.prev.prev.next = sentinel.prev; // 更新原尾节点的后继引用
        size++; // 链表大小增加
    }

    // 移除并返回链表头部的元素
    public T removeFirst() {
        if (size == 0) return null; // 如果链表为空，则返回null
        T item = sentinel.next.item; // 获取首节点存储的数据
        sentinel.next = sentinel.next.next; // 移除首节点
        sentinel.next.prev = sentinel; // 更新新的首节点的前驱引用
        size--; // 链表大小减少
        return item; // 返回被移除的元素
    }

    // 移除并返回链表尾部的元素
    public T removeLast() {
        if (size == 0) return null; // 如果链表为空，则返回null
        T item = sentinel.prev.item; // 获取尾节点存储的数据
        sentinel.prev = sentinel.prev.prev; // 移除尾节点
        sentinel.prev.next = sentinel; // 更新新的尾节点的后继引用
        size--; // 链表大小减少
        return item; // 返回被移除的元素
    }

    // 通过迭代获取指定位置的元素
    public T get(int index) {
        if (index >= size) return null; // 如果索引超出范围，则返回null
        Node current = sentinel.next; // 从首节点开始
        for (int i = 0; i < index; i++) { // 迭代至指定位置
            current = current.next;
        }
        return current.item; // 返回找到的元素
    }

    // 返回链表的大小
    public int size() {
        return size;
    }

    // 递归辅助方法，用于实现getRecursive
    private T getRecursiveHelper(int index, Node current) {
        if (index == 0) return current.item; // 基准情况：找到目标元素
        return getRecursiveHelper(index - 1, current.next); // 递归调用，向后移动
    }

    // 使用递归获取指定位置的元素
    public T getRecursive(int index) {
        if (index >= size) return null; // 如果索引超出范围，则返回null
        return getRecursiveHelper(index, sentinel.next); // 从首节点开始递归查找
    }

    // 检查双端队列是否为空
public boolean isEmpty() {
    return size == 0;
}

// 打印双端队列的内容
public void printDeque() {
    Node current = sentinel.next;
    while (current != sentinel) { // 循环直到回到哨兵节点
        System.out.print(current.item + " ");
        current = current.next;
    }
    System.out.println();
}
}
