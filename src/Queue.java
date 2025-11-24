
public class Queue<T> {
    private int length;
    private int head;
    private int tail;
    private T[] arr;


    public Queue(int size) {
        this.length = size;
        this.arr = (T[]) new Object[length];
        this.head = 0;
        this.tail = 0;
    }

    public boolean isEmpty() {
        return tail == head;
    }

    public boolean isFull() {
        return (head == tail + 1) || (head == 0 && tail == length - 1);
    }

    public void Enqueue(T v) {
        if (isFull()) {
            System.out.println("Очередь переполнена");
            return;
        }
        arr[tail] = v;
        if (tail == length - 1) {
            tail = 0;
        } else {
            tail = tail + 1;
        }
    }

    public T Dequeue() {
        if (isEmpty()) {
            System.out.println("Очередь переполнена");
            return null;
        }
        T v = arr[head];
        if (head == length - 1) {
            head = 0;
        } else {
            head = head + 1;
        }
        return v;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("Очередь переполнена");
            return null;
        }
        return arr[head];
    }
}