import aisd.queue.ArrayQueue;
import aisd.queue.IQueue;

public class FlaviusProblem {
    int n;
    int k;

    public FlaviusProblem(int n, int k) {
        if (n <= 0 || k <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.k = k;
    }

    public IQueue<Integer> getQueue() throws Exception {
        int[] table = new int[n];
        int alive = n;
        IQueue<Integer> queue = new ArrayQueue<>(n);
        for (int i = 0; i < table.length; i++) {
            table[i] = i;
        }
        int index = -1;
        while (alive > 0) {
            int steps = k;
            while(steps > 0) {
                index++;
                index %= table.length;
                while (table[index] == -1) {
                    index++;
                    index %= table.length;
                }
                steps--;
            }
            queue.enqueue(table[index]);
            table[index] = -1;
            alive--;
        }
        return queue;
    }

}
