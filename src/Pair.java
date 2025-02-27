/**
 * Generic class used to store a pair of objects.
 */
public class Pair<T, E> {
    private T first;
    private E second;

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public E getSecond() {
        return second;
    }

    public void setSecond(E second) {
        this.second = second;
    }

    public Pair(T first, E second) {
        this.first = first;
        this.second = second;
    }
}
