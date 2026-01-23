import java.util.concurrent.Semaphore;

class LargerBuffer {
    private Semaphore full = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private static int bufferSize = 10;
    private Semaphore empty = new Semaphore(bufferSize);
    private int[] values = new int[bufferSize];
    private int idxTake = 0;
    private int idxPut = 0;

    public void set(int value) throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        try {
            values[idxPut++ % bufferSize] = value;
            idxPut = (idxPut + 1) % bufferSize;
        } finally {
            full.release();
            mutex.release();
        }
    }

    public int get() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        try {
            int value = values[idxTake];
            idxTake = (idxTake + 1) % bufferSize;
            return value;
        } finally {
            mutex.release();
            empty.release();
        }
    }
}
