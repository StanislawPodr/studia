import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SJF implements Simulated{

    List<EndedProcess> endedProcesses;
    private boolean simulationEnded = false;
    List<Process> processes;
    private boolean waitingForChange = false;

    public SJF() {
        processes = new ArrayList<>();
        endedProcesses = new ArrayList<>();
    }

    @Override
    public List<Simulated.EndedProcess> getEndedProcess() {
        return endedProcesses;
    }

    @Override
    public boolean isSimulationEnded() {
       return simulationEnded;
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
                Comparator<Process> comparator = Comparator.comparingInt(Process::getBurstTimeLeft);
                processes.sort(comparator);
            }
        }

        if (processes.isEmpty()) {
            simulationEnded = true;
        } else {
            simulationEnded = false;
        }
    }

    @Override
    public String toString() {
        return "SJF";
    }

}
