
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

import core.AbstractSwappingSortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;

public class Main {

	public static void main(String[] args) {

		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());
		AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> shakerAlgorithm = new ShakerSort<MarkedValue<Integer>>(
				markedComparator);
		AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> insertionAlgorithm = new InsertionSortWithBinarySearch<MarkedValue<Integer>>(
				markedComparator);
		AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> selectionAlgorithm = new SelectionSort<MarkedValue<Integer>>(
				markedComparator);
		try (
				BufferedWriter writerRandomShaker = new BufferedWriter(new FileWriter("shakerSortRandom.csv"));
				BufferedWriter writerOrderedShaker = new BufferedWriter(new FileWriter("shakerSortOrdered.csv"));
				BufferedWriter writerReversedShaker = new BufferedWriter(new FileWriter("shakerSortReversed.csv"));
				BufferedWriter writerShuffledShaker = new BufferedWriter(new FileWriter("shakerSortShuffled.csv"));
				BufferedWriter writerRandomInsertion = new BufferedWriter(new FileWriter("insertionSortRandom.csv"));
				BufferedWriter writerOrderedInsertion = new BufferedWriter(new FileWriter("insertionSortOrdered.csv"));
				BufferedWriter writerReversedInsertion = new BufferedWriter(
						new FileWriter("insertionSortReversed.csv"));
				BufferedWriter writerShuffledInsertion = new BufferedWriter(
						new FileWriter("insertionSortShuffled.csv"));
				BufferedWriter writerRandomSelection = new BufferedWriter(new FileWriter("selectionSortRandom.csv"));
				BufferedWriter writerOrderedSelection = new BufferedWriter(new FileWriter("selectionSortOrdered.csv"));
				BufferedWriter writerReversedSelection = new BufferedWriter(
						new FileWriter("selectionSortReversed.csv"));
				BufferedWriter writerShuffledSelection = new BufferedWriter(
						new FileWriter("selectionSortShuffled.csv"))) {
			String header = "size;time;timeDeviation;comparisons;comparisonsDeviation;swaps;swapsDeviation\n";
			writerRandomShaker.write(header);
			writerOrderedShaker.write(header);
			writerReversedShaker.write(header);
			writerShuffledShaker.write(header);
			writerRandomInsertion.write(header);
			writerOrderedInsertion.write(header);
			writerReversedInsertion.write(header);
			writerShuffledInsertion.write(header);
			writerRandomSelection.write(header);
			writerOrderedSelection.write(header);
			writerReversedSelection.write(header);
			writerShuffledSelection.write(header);
			for (int i = 0; i <= 10000; i += 500) {
				Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(
						new RandomIntegerArrayGenerator(10));
				testing.results.swapping.Result result = Tester.runNTimes(shakerAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerRandomShaker);
				result = Tester.runNTimes(insertionAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerRandomInsertion);
				result = Tester.runNTimes(selectionAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerRandomSelection);

				generator = new MarkingGenerator<Integer>(
						new OrderedIntegerArrayGenerator());
				result = Tester.runNTimes(shakerAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerOrderedShaker);
				result = Tester.runNTimes(insertionAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerOrderedInsertion);
				result = Tester.runNTimes(selectionAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerOrderedSelection);

				generator = new MarkingGenerator<Integer>(
						new ReversedIntegerArrayGenerator());
				result = Tester.runNTimes(shakerAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerReversedShaker);
				result = Tester.runNTimes(insertionAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerReversedInsertion);
				result = Tester.runNTimes(selectionAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerReversedSelection);

				generator = new MarkingGenerator<Integer>(
						new ShuffledIntegerArrayGenerator());
				result = Tester.runNTimes(shakerAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerShuffledShaker);
				result = Tester.runNTimes(insertionAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerShuffledInsertion);
				result = Tester.runNTimes(selectionAlgorithm, generator, i, 20);
				writeResultsToCsv(i, result, writerShuffledSelection);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeResultsToCsv(int size, testing.results.swapping.Result result, BufferedWriter writer)
			throws IOException {
		writer.write(size + ";"
				+ double2String(result.averageTimeInMilliseconds()) + ";"
				+ double2String(result.timeStandardDeviation()) + ";" + double2String(result.averageComparisons()) + ";"
				+ double2String(result.comparisonsStandardDeviation()) + ";" + double2String(result.averageSwaps())
				+ ";"
				+ double2String(result.swapsStandardDeviation()) + "\n");
	}


	private static String double2String(double value) {
		return String.format("%.12f", value);
	}
}
