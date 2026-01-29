import java.util.concurrent.Semaphore;

public class App {
	public static void main(String[] args) throws Exception {
		Semaphore mutex = new Semaphore(1);
		Count p = new Count(mutex);
		Count q = new Count(mutex);
		p.start();
		q.start();
		try {
			p.join();
			q.join();
		} catch (InterruptedException e) {
		}
		System.out.println("The value of n is " + p.getN());
	}
}
