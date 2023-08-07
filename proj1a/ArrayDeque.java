public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int front;
    private int rear;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 0;
    }
    public ArrayDeque(ArrayDeque other) {
        size = other.size();
        front = 0;
        rear = size;
        items = (T[]) new Object[size + 1];
        for (int i = 0; i < size; i++) {
            items[i] = (T) other.get(i);
        }
    }
    private void resize() {
        if ((size * 1.0) / items.length < 0.25) {
            /*Resize*/
            T[] newArray = (T[]) new Object[size * 2 + 1];
            for (int i = 0; i < size; i++) {
                newArray[i] = get(i);
            }
            front = 0;
            rear = size;
            items = newArray;
        }
    }

    public void addFirst(T item) {
        if (isFull()) {
            T[] newArray = (T[]) new Object[size * 2 + 1];
            newArray[0] = item;
            for (int i = 1; i <= size; i++) {
                newArray[i] = get(i - 1);
            }
            size += 1;
            front = 0;
            rear = size;
            items = newArray;
        } else {
            front = (front - 1 + items.length) % items.length;
            items[front] = item;
            size += 1;
            resize();
        }
    }
    public void addLast(T item) {
        if (isFull()) {
            T[] newArray = (T[]) new Object[2 * size + 1];
            for (int i = 0; i < size; i++) {
                newArray[i] = get(i);
            }
            newArray[size] = item;
            size += 1;
            front = 0;
            rear = size;
            items = newArray;
        } else {
            items[rear] = item;
            size += 1;
            rear = (rear + 1) % items.length;
            resize();
        }
    }
    public boolean isEmpty() {
        return front == rear;
    }
    private boolean isFull() {
        return (rear + 1) % items.length == front;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        System.out.print(get(0));
        for (int i = 1; i < size; i++) {
            System.out.print(" ");
            System.out.print(get(i));
        }
        System.out.println();
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T ret = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        size -= 1;
        resize();
        return ret;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        rear = (rear - 1 + items.length) % items.length;
        T ret = items[rear];
        items[rear] = null;
        size -= 1;
        resize();
        return ret;
    }
    public T get(int index) {
        int idx = (front + index) % items.length;
        return items[idx];
    }
}

