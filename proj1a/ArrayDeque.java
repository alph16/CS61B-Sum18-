public class ArrayDeque<T> {
    // 使用泛型数组存储元素
    private T[] items;
    // 记录队列中元素的数量
    private int size;
    // 下一个要添加到队首的位置的索引
    private int nextFirst;
    // 下一个要添加到队尾的位置的索引
    private int nextLast;

    // 构造函数初始化ArrayDeque对象
    public ArrayDeque() {
        items = (T[]) new Object[8]; // 初始容量为8
        size = 0; // 初始大小为0
        nextFirst = 7; // 初始队首位置设置为数组的最后一个位置
        nextLast = 0; // 初始队尾位置设置为数组的第一个位置
    }

    // 辅助方法：计算给定索引的前一个位置的索引，考虑循环
    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        } else {
            return index - 1;
        }
    }

    // 辅助方法：计算给定索引的后一个位置的索引，考虑循环
    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    // 辅助方法：调整数组大小以适应更多元素或减少未使用的空间
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst);
        for (int newIndex = 0; newIndex < size; newIndex++) {
            a[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    // 在队首添加元素
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2); // 如果数组已满，扩大两倍
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    // 在队尾添加元素
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2); // 如果数组已满，扩大两倍
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    // 检查队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取队列中的元素数量
    public int size() {
        return size;
    }

    // 打印队列中的所有元素
    public void printDeque() {
        int index = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[index] + " ");
            index = plusOne(index);
        }
        System.out.println();
    }

    // 从队首移除元素
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null; // 帮助垃圾收集
        size--;
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2); // 缩小数组大小
        }
        return item;
    }

    // 从队尾移除元素
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null; // 帮助垃圾收集
        size--;
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 2); // 缩小数组大小
        }
        return item;
    }

    // 获取指定位置的元素
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int start = plusOne(nextFirst);
        int actualIndex = (start + index) % items.length;
        return items[actualIndex];
    }
}
