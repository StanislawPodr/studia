import java.util.concurrent.Semaphore;

class Count extends Thread {
	private static IntCellNoMonitor n = new IntCellNoMonitor();
	Semaphore mutex;

	public Count(Semaphore mutex) {
		this.mutex = mutex;
	}

	@Override
	public void run() {
		for (int i = 0; i < 200000; i++) {
			try {
				mutex.acquire();
				try {
					n.setN(n.getN() + 1);
				} finally {
					mutex.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getN() {
		return n.getN();
	}
}