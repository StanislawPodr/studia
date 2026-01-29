class Philosopher extends Thread {

    private int seatNumber;
    private PhilosophersProblem problem;

    public Philosopher(int seatNumber, PhilosophersProblem problem) {
        this.seatNumber = seatNumber;
        this.problem = problem;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public void run() {
        while (true) {
            int meditationTime = PhilosophersProblem.rand.nextInt(PhilosophersProblem.maxTimeToSleep);

            try {
                Thread.sleep(meditationTime);
                problem.sit(this);
                System.out.println("Philosopher " + seatNumber + " ate");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
