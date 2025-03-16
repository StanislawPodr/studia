import java.util.ArrayList;
import java.util.List;

public class RR implements Simulated {

    List<EndedProcess> endedProcesses;
    private boolean simulationEnded = false;
    List<Process> processes;
    private boolean waitingForChange = false;
    private int defaultQuantum = 5;
    private int quantum = defaultQuantum;
    private int index = 0;

    public RR() {
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

        if (waitingForChange) {
            waitingForChange = false;
            simulationEnded = false;
            return;
        }

        if (!processes.isEmpty()) {
            processes.get(index).setBurstTimeLeft(processes.get(index).getBurstTimeLeft() - Process.iterationTime);
            if (processes.get(index).getBurstTimeLeft() == 0) {
                waitingForChange = true;
                endedProcesses.add(new EndedProcess(time + Process.iterationTime, processes.get(index)));
                processes.remove(index);
                if(processes.size() > 0) {
                    index = index % processes.size();
                } else {
                    index = 0;
                }
                quantum = defaultQuantum;
            } else if (quantum == 0) {
                waitingForChange = true;
                quantum = defaultQuantum;
                index = (index + 1) % processes.size();
            } else {
                quantum--;
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
        return "RR";
    }

}
