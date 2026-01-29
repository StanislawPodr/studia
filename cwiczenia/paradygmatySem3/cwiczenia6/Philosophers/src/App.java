import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        int philosopherCount = 5;
        List<Philosopher> philosophers = new ArrayList<>(philosopherCount);

        PhilosophersProblem problem = new PhilosophersProblem(philosopherCount);
        for (int init = 0; init < philosopherCount; init++) {
            philosophers.add(new Philosopher(init, problem));
        }

        for (var philosopher : philosophers) {
            philosopher.start();
        }
    }
}
