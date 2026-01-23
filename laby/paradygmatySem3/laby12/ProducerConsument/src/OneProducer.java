import java.util.Random;

class OneProducer extends Thread {

    private static final Random rand = new Random();
    private static final int low = 500;
    private static final int high = 5000;
    private BufferForOne buffer;

    public OneProducer(BufferForOne buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            produce();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void produce() throws InterruptedException {
        while (true) {
            // losowanie wartości
            int randFromRange = rand.nextInt((high - low) + 1) + low;

            buffer.set(randFromRange);
        }
    }
}
