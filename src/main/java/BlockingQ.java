import java.util.LinkedList;
import java.util.Queue;

public class BlockingQ<T> {
    private Queue<T> queue = new LinkedList<>();

    public synchronized boolean offer(T elemento) {
        queue.add(elemento);
        //System.out.println("Adicionado uma elemento, size = " + queue.size());
        notifyAll();
        return true;
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.peek() == null) {
            wait();
        }
        T a = queue.poll();
        //System.out.println("Retirado um elemento, size = " + size());
        return a;
    }

    public synchronized void clear() {
        queue = new LinkedList<>();
    }

    public synchronized int size() {
        return queue.size();
    }
}