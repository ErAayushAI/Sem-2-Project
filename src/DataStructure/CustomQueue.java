package DataStructure;

public class CustomQueue<T> {
    private static final int SIZE = 100;
    private Object[] data = new Object[SIZE];
    private int front = 0, rear = 0;

    public void enqueue(T item) {
        if (rear >= SIZE) {
            System.out.println("Queue Overflow");
        } else {
            data[rear++] = item;
        }
    }

    public T dequeue() {
        if (front == rear) {
            System.out.println("Queue Underflow");
            return null;
        } else {
            return (T) data[front++];
        }
    }

    public boolean isEmpty() {
        return front == rear;
    }
}
