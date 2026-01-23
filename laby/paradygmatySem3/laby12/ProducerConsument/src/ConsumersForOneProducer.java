class ConsumersForOneProducer extends Thread {
     private BufferForOne buffer;
     private int consumerNumber = 0;
     private final static int sleepTime = 1000;

     public ConsumersForOneProducer(BufferForOne buffer, int consumerNumber) {
        this.consumerNumber = consumerNumber;
        this.buffer = buffer;
     }

     @Override
     public void run() {
        try {
            consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     }

     public int consume() throws InterruptedException {
        int result = buffer.get();

        System.out.println("Konsument " + consumerNumber + " rozpoczal " + result);
        sleep(sleepTime);
        System.out.println("Konsument " + consumerNumber + " zakonczyl " + result);

        return result;
     }
}
