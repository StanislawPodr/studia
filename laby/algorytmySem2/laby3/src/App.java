public class App {
    public static void main(String[] args) throws Exception {
        TestIteratorForGeometricSequence test = new TestIteratorForGeometricSequence(1, 2, 512);
        ByteIterator byteIterator = new ByteIterator(test);
        while(byteIterator.hasNext()) {
            System.out.println(byteIterator.next());
        }
    }
}
