public class App {

    public static <T> void extractingIterartorTest(T [] table) {
            ExtractingIterator<T> extractingIterator = new ExtractingIterator<>(table);
            while(extractingIterator.hasNext()) {
                CountValuesIterator<T> next = extractingIterator.next();
                while(next.hasNext()) {
                    System.out.print(next.next());
                }
                System.out.println();
            }
        }
        public static void main(String[] args) {
            TestIteratorForGeometricSequence test = new TestIteratorForGeometricSequence(1, 2, 512);
            ByteIterator byteIterator = new ByteIterator(test);
            while(byteIterator.hasNext()) {
                System.out.println(byteIterator.next());
            }
    
            System.out.println("-----------------------------------------");
    
            DivisorIterable iterable = new DivisorIterable(24);
            for(int i : iterable) {
                System.out.println(i);
            }
    
            System.out.println("-----------------------------------------");
    
            Integer [] table = {1, 5, 6, 2, 8, 5, 1, 1, 2, 7, 1, 6};
            extractingIterartorTest(table);

            Integer [] table3 = new Integer[0];
            extractingIterartorTest(table3);

            Integer [] table2 = {null, 2, 4, null};
            //extractingIterartorTest(table2);

    }
}
