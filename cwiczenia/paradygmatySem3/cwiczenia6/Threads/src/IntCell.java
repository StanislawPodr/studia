class IntCell {
	private int n = 0;

	public synchronized int getN() {
		return n;
	}

	public synchronized void setN(int n) {
		this.n = n;
	}

    public synchronized int getAndIncrement() {
        return n++;
    }
}