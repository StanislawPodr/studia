import java.util.concurrent.Semaphore;

class BufferForOne {
    private Semaphore empty = new Semaphore(1);
    private Semaphore full = new Semaphore(0);
    private int value = 0;

    public void set(int value) throws InterruptedException {
        empty.acquire();
        try {
            this.value = value;
        } finally {
            full.release();
        }
    }

    public int get() throws InterruptedException {
        full.acquire();
        try {
            return value;

        } finally {
            empty.release();
        }
    }
}