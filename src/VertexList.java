
public class VertexList<T> {
    private int size; //размер после помещения вершин
    private int capacity; //заданный размер
    private T[] arr;//массив для вершин
    private int index;


    public VertexList(){
        this.capacity = 10;
        this.size = 0;
        this.index = 0;
        this.arr = (T[])new Object[capacity];

    }
    public void add(T vertex){
        if (size == capacity){
            capacity *= 2;
            T[] newArr = (T[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[index++] = vertex;
        size ++;
    }
    public T get(int index){
        if (index >= size){
            return null;
        }
        return arr[index];
    }
    public void set(int index, T vertex){
        if (index >= 0 && index < size){
            arr[index] = vertex;
        }
    }
    public void remove(int index){
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = null;
        size--;
        this.index--;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void clear(){
        for (int i = 0; i < size; i++){
            arr[i] = null;
        }
        size = 0;
        index = 0;
    }

}
