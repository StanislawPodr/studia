import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Simulator {
    private List<Process> processes;
    private int numberOfProcesses = Process.numberOfProcesses;
    private int time = 0;
    private List<Simulated> simulated;

    public Simulator(List<Simulated> simulated) {
        processes = new ArrayList<>(Process.numberOfProcesses);
        this.simulated = simulated;
        for (int i = 0; i < numberOfProcesses; i++) {
            processes.add(new Process());
        }
    }

    public Simulator(List<Simulated> simulated, List<Process> processes) {
        this.simulated = simulated;
        this.processes = processes;
    }

    public void run() {
        boolean end = false;
        while (!end) {
            List<Process> readyQueue = new ArrayList<>();
            Iterator<Process> iterator = processes.iterator();
            while (iterator.hasNext()) {
                Process process = iterator.next();
                if (process.getArrivalTime() == time) {
                    readyQueue.add(process);
                    iterator.remove();
                }
            }

            simulated.forEach(simulated -> {
                simulated.run(readyQueue, time);
            });

            time += Process.iterationTime;
            if (processes.isEmpty()) {
                boolean allEnded = true;
                for (Simulated sim : simulated) {
                    if (!sim.isSimulationEnded()) {
                        allEnded = false;
                        break;
                    }
                }
                if (allEnded) {
                    end = true;
                }
            }
        }

    }

    public void printAllProcesses() {
       for(Simulated sim : simulated) {
           System.out.println(sim + ":");
           int i = 0;
           for(Simulated.EndedProcess endedProcess : sim.getEndedProcess()) {
               System.out.println(i + ": " + endedProcess.time());
               i++;
           }
       }
    }

    public void printResults() {
        for (Simulated sim : simulated) {
            System.out.println(sim + ":");
            int avgTimeForProcess = 0;
            int maxTimeForProcess = 0;
            int maxTimeForProcessWaiting = 0;
            for (Simulated.EndedProcess endedProcess : sim.getEndedProcess()) {
                int completionTime = endedProcess.time() - endedProcess.process().getArrivalTime();
                if (completionTime > maxTimeForProcess) {
                    maxTimeForProcess = completionTime;
                }

                if (completionTime - endedProcess.process().getBurstTime() > maxTimeForProcessWaiting) {
                    maxTimeForProcessWaiting = completionTime - endedProcess.process().getBurstTime();
                }

                avgTimeForProcess += endedProcess.time() - endedProcess.process().getArrivalTime();
            }
            avgTimeForProcess /= sim.getEndedProcess().size();
            System.out.println("Number of processes: " + sim.getEndedProcess().size());
            System.out.println("Average time for process: " + avgTimeForProcess);
            System.out.println("Max time for process: " + maxTimeForProcess);
            System.out.println("Max time for process waiting: " + maxTimeForProcessWaiting);
            System.out.println(
                    "Last process ended at: " + sim.getEndedProcess().get(sim.getEndedProcess().size() - 1).time());
            System.out.println("Median finish time: " + sim.getEndedProcess().get(sim.getEndedProcess().size() / 2).time());
            System.out.println("---------------------------------");
        }
    }
}
