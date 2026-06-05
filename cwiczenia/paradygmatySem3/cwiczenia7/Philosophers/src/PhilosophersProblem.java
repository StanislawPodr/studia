import java.util.Random;
import java.util.concurrent.Semaphore;

class PhilosophersProblem {

    public static final Random rand = new Random();
    public static final int maxTimeToSleep = 5000;
    private int philosophers;
    private Semaphore doorKeeper;
    private Semaphore[] chopSticks;

    public PhilosophersProblem(int numberOfPhilosophers) {
        if (numberOfPhilosophers < 2) {
            throw new IllegalArgumentException("Number of philosophers can't be negative.");
        }

        philosophers = numberOfPhilosophers;
        doorKeeper = new Semaphore(philosophers - 1, true);

        chopSticks = new Semaphore[philosophers];
        for (int init = 0; init < chopSticks.length; init++) {
            chopSticks[init] = new Semaphore(1, true);
        }
    }

    public void sit(Philosopher philosopher) throws InterruptedException {
        if (philosopher == null) {
            throw new IllegalArgumentException("Phillosopher shouldn't be null");
        }

        if (philosopher.getSeatNumber() >= philosophers) {
            throw new IllegalArgumentException("Philosopher seat index cannot larger than number of seats");
        }

        doorKeeper.acquire();

        try {
            var leftChopstick = chopSticks[philosopher.getSeatNumber()];
            var rightChopstick = chopSticks[(philosopher.getSeatNumber() + 1) % philosophers];

            leftChopstick.acquire();
            rightChopstick.acquire();

            try {
                int eatingTime = rand.nextInt(maxTimeToSleep);
                Thread.sleep(eatingTime);
            } finally {
                leftChopstick.release();
                rightChopstick.release();
            }
        } finally {
            doorKeeper.release();
        }
    }
}
