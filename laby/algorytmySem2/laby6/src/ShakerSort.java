import java.util.Comparator;
import java.util.List;

import core.AbstractSwappingSortingAlgorithm;

public class ShakerSort<T> extends AbstractSwappingSortingAlgorithm<T> {

	public ShakerSort(Comparator<? super T> comparator) {
		super(comparator);
	}

	@Override
	public List<T> sort(List<T> list) {
		int size = list.size();

		int halfSize = size / 2;
		
		for(int pass = 1; pass <= halfSize; ++pass) {
			for(int left = pass - 1; left < (size - pass); ++left) {
				int right = left + 1;
				
				if(compare(list.get(left), list.get(right)) > 0) {
					swap(list, left, right);
				}
			}

			for(int right = size - pass - 1; right > pass - 1; --right) {
				int left = right - 1;
				
				if(compare(list.get(left), list.get(right)) > 0) {
					swap(list, left, right);
				}
			}
		}
		
		return list;
	}
}
