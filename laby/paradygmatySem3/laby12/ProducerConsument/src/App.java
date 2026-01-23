public class App {
    public static void main(String[] args) throws Exception {
        BufferForOne bufferForOne = new BufferForOne();
        OneProducer producer = new OneProducer(bufferForOne);
        ConsumersForOneProducer [] consumers = new ConsumersForOneProducer[20];
        for (int init = 0; init < 20; init++) {
            consumers[init] = new ConsumersForOneProducer(bufferForOne, init);
        }

        producer.start();
        
        for (var consumer : consumers) {
            consumer.start();
        }
    }
}
