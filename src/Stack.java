public class Stack<T>{
    private int size;
    private int top;
    private T[] arr;


    public Stack(int size) {
        this.size = size;
        this.arr = (T[]) new Object[size];
        this.top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }
    public boolean isFull(){
        return top == size - 1;
    }
    public void push(T v){
        if (isFull()) {
            System.out.println("Стек переполнен");
        } else {
            arr[++top] = v;
        }
    }
    public T pop() {
        if (isEmpty()){
            return null;
        }
        return arr[top--];
    }
    public T peek(){
        if (isEmpty()){
            return null;
        }
        return arr[top];
    }
}





















