import java.util.Random;

public class Process {
    private int arrivalTime;
    private int burstTime;
    private int burstTimeLeft;
    public final static int iterationTime = 1;
    public final static int numberOfProcesses = 100;

    public Process() {
        Random random = new Random();
        arrivalTime = random.nextInt(200) * iterationTime;
        burstTime = (int) ((Math.pow(random.nextInt(5) + 1, 7)) * iterationTime);
        burstTimeLeft = burstTime;
    }

    public Process(int arrivalTime, int burstTime) {
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        burstTimeLeft = burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    @Override
    public Process clone() {
        Process process = new Process(arrivalTime, burstTime);
        return process;
    }

    public int getBurstTimeLeft() {
        return burstTimeLeft;
    }

    public void setBurstTimeLeft(int burstTimeLeft) {
        this.burstTimeLeft = burstTimeLeft;
    }

    public static int getIterationtime() {
        return iterationTime;
    }

    public static int getNumberofprocesses() {
        return numberOfProcesses;
    }
}
