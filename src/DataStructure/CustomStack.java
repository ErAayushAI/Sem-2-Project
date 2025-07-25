package DataStructure;

public class CustomStack<T> {
    private static final int SIZE = 100;
    private Object[] data = new Object[SIZE];
    private int top = -1;

    public void push(T item) {
        if (top >= SIZE - 1) {
            System.out.println("Stack Overflow");
        } else {
            data[++top] = item;
        }
    }

    public T pop() {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return null;
        } else {
            return (T) data[top--];
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
