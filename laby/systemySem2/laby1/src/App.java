import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Simulator simulator = new Simulator(List.of(new FCFS(), new SJF(true), new SJF(false), new RR()));
        simulator.run();
        simulator.printResults();
        System.out.println("\n ----------------- \n");
        List<Process> processes = new ArrayList<>(1000);
        for (int i = 0; i < 100; i++) {
            processes.add(new Process(0, 11));
        }
        for (int i = 0; i < 900; i++) {
            processes.add(new Process(i*5, 10));
        }
        simulator = new Simulator(List.of(new FCFS(), new SJF(true), new SJF(false), new RR()), processes);
        simulator.run();
        simulator.printResults();
    }
}
