import java.util.List;

public interface Simulated {
    public record EndedProcess(int time, Process process) {}
    public List<EndedProcess> getEndedProcess();
    public boolean isSimulationEnded();
    public void run(List<Process> newProcesses, int time);
}
