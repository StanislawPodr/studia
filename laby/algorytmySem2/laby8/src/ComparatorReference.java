import java.util.Comparator;

class ComparatorReference<T> implements Comparator<T> {
     private Comparator<T> comparator;

        public ComparatorReference(Comparator<T> comparator) {
            this.comparator = comparator;
        }

        public void setComparator(Comparator<T> comparator) {
            this.comparator = comparator;
        }

        @Override
        public int compare(T o1, T o2) {
            return comparator.compare(o1, o2);
        }
}
