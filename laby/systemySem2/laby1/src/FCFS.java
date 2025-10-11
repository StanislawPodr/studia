import java.util.ArrayList;
import java.util.List;

public class FCFS implements Simulated {
    List<EndedProcess> endedProcesses;
    private boolean simulationEnded = false;
    List<Process> processes;
    private boolean waitingForChange = false;

    public FCFS() {
        processes = new ArrayList<>();
        endedProcesses = new ArrayList<>();
    }

    @Override
    public void run(List<Process> newProcesses, int time) {
        newProcesses.forEach(process -> {
            processes.add(process.clone());
        });

        if(waitingForChange) {
            waitingForChange = false;
            simulationEnded = false;
            return;
        }

        if (!processes.isEmpty()) {
            processes.get(0).setBurstTimeLeft(processes.get(0).getBurstTimeLeft() - Process.iterationTime);
            if (processes.get(0).getBurstTimeLeft() == 0) {
                waitingForChange = true;
                endedProcesses.add(new EndedProcess(time + Process.iterationTime, processes.get(0)));
                processes.remove(0);
            }
        }

        if (processes.isEmpty()) {
            simulationEnded = true;
        } else {
            simulationEnded = false;
        }
    }

    @Override
    public List<EndedProcess> getEndedProcess() {
        return endedProcesses;
    }

    @Override
    public boolean isSimulationEnded() {
        return simulationEnded;
    }

    @Override
    public String toString() {
        return "FCFS";
    }
}
