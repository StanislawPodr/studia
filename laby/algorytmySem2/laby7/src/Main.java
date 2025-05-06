
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

import core.AbstractSortingAlgorithm;
import core.AbstractSwappingSortingAlgorithm;
import testing.*;
import testing.comparators.*;
import testing.generation.*;
import testing.generation.conversion.*;

public class Main {

	public static void main(String[] args) {
		
		Comparator<MarkedValue<Integer>> markedComparator = new MarkedValueComparator<Integer>(new IntegerComparator());
		AbstractSortingAlgorithm<MarkedValue<Integer>> mergeSort = new MergeSort<MarkedValue<Integer>>(
				markedComparator);
		AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> quicksortRand = new QuickSort<MarkedValue<Integer>>(new RandomPivot<>(), markedComparator);
		AbstractSwappingSortingAlgorithm<MarkedValue<Integer>> quickSortFirst = new QuickSort<MarkedValue<Integer>>(
				new FirstIndexPivot<>(), markedComparator);
		try (
				BufferedWriter writerRandomMerge = new BufferedWriter(new FileWriter("mergeSortRandom.csv"));
				BufferedWriter writerOrderedMerge = new BufferedWriter(new FileWriter("mergeSortOrdered.csv"));
				BufferedWriter writerReversedMerge = new BufferedWriter(new FileWriter("mergeSortReversed.csv"));
				BufferedWriter writerShuffledMerge = new BufferedWriter(new FileWriter("mergeSortShuffled.csv"));
				BufferedWriter writerRandomMergeList = new BufferedWriter(new FileWriter("mergeSortListRandom.csv"));
				BufferedWriter writerOrderedMergeList = new BufferedWriter(new FileWriter("mergeSortListOrdered.csv"));
				BufferedWriter writerReversedMergeList = new BufferedWriter(new FileWriter("mergeSortListReversed.csv"));
				BufferedWriter writerShuffledMergeList = new BufferedWriter(new FileWriter("mergeSortListShuffled.csv"));
				BufferedWriter writerRandomQRand = new BufferedWriter(new FileWriter("QRandSortRandom.csv"));
				BufferedWriter writerOrderedQRand = new BufferedWriter(new FileWriter("QRandSortOrdered.csv"));
				BufferedWriter writerReversedQRand = new BufferedWriter(
						new FileWriter("QRandSortReversed.csv"));
				BufferedWriter writerShuffledQRand = new BufferedWriter(
						new FileWriter("QRandSortShuffled.csv"));
				BufferedWriter writerRandomQFirst = new BufferedWriter(new FileWriter("QFirstSortRandom.csv"));
				BufferedWriter writerOrderedQFirst = new BufferedWriter(new FileWriter("QFirstSortOrdered.csv"));
				BufferedWriter writerReversedQFirst = new BufferedWriter(
						new FileWriter("QFirstSortReversed.csv"));
				BufferedWriter writerShuffledQFirst = new BufferedWriter(
						new FileWriter("QFirstSortShuffled.csv"))) {
			String headerQuickSort = "size;time;timeDeviation;comparisons;comparisonsDeviation;swaps;swapsDeviation\n";
			String headerMergeSort = "size;time;timeDeviation;comparisons;comparisonsDeviation\n";
			writerRandomMerge.write(headerMergeSort);
			writerOrderedMerge.write(headerMergeSort);
			writerReversedMerge.write(headerMergeSort);
			writerShuffledMerge.write(headerMergeSort);
			writerRandomMergeList.write(headerMergeSort);
			writerOrderedMergeList.write(headerMergeSort);
			writerReversedMergeList.write(headerMergeSort);
			writerShuffledMergeList.write(headerMergeSort);
			writerRandomQRand.write(headerQuickSort);
			writerOrderedQRand.write(headerQuickSort);
			writerReversedQRand.write(headerQuickSort);
			writerShuffledQRand.write(headerQuickSort);
			writerRandomQFirst.write(headerQuickSort);
			writerOrderedQFirst.write(headerQuickSort);
			writerReversedQFirst.write(headerQuickSort);
			writerShuffledQFirst.write(headerQuickSort);
			for (int i = 0; i <= 10000; i += 500) {
				Generator<MarkedValue<Integer>> generator = new MarkingGenerator<Integer>(
						new RandomIntegerArrayGenerator(10));
				Generator<MarkedValue<Integer>> generatorList = new MarkingGenerator<Integer>(
						new RandomIntegerArrayGenerator(10));
				testing.results.Result resultMerge = Tester.runNTimes(mergeSort, generator, i, 20);
				writeResultsWithoutSwapsToCsv(i, resultMerge, writerRandomMerge);
				resultMerge = Tester.runNTimes(mergeSort, generatorList, i, 20);
				writeResultsWithoutSwapsToCsv(i, resultMerge, writerRandomMergeList);
				testing.results.swapping.Result result = Tester.runNTimes(quicksortRand, generator, i, 20);
				writeResultsToCsv(i, result, writerRandomQRand);
				result = Tester.runNTimes(quickSortFirst, generator, i, 20);
				writeResultsToCsv(i, result, writerRandomQFirst);

				generator = new MarkingGenerator<Integer>(
						new OrderedIntegerArrayGenerator());
				resultMerge = Tester.runNTimes(mergeSort, generator, i, 20);
				writeResultsWithoutSwapsToCsv(i, resultMerge, writerOrderedMerge);
				resultMerge = Tester.runNTimes(mergeSort, generatorList, i, 20);
				writeResultsWithoutSwapsToCsv(i, resultMerge, writerOrderedMergeList);
				result = Tester.runNTimes(quicksortRand, generator, i, 20);
				writeResultsToCsv(i, result, writerOrderedQRand);
				result = Tester.runNTimes(quickSortFirst, generator, i, 20);
				writeResultsToCsv(i, result, writerOrderedQFirst);

				generator = new MarkingGenerator<Integer>(
						new ReversedIntegerArrayGenerator());
				resultMerge = Tester.runNTimes(mergeSort, generator, i, 20);
				writeResultsWithoutSwapsToCsv(i, resultMerge, writerReversedMerge);
				resultMerge = Tester.runNTimes(mergeSort, generatorList, i, 20);
				writeResultsWithoutSwapsToCsv(i, resultMerge, writerReversedMergeList);
				result = Tester.runNTimes(quicksortRand, generator, i, 20);
				writeResultsToCsv(i, result, writerReversedQRand);
				result = Tester.runNTimes(quickSortFirst, generator, i, 20);
				writeResultsToCsv(i, result, writerReversedQFirst);

				generator = new MarkingGenerator<Integer>(
						new ShuffledIntegerArrayGenerator());
				resultMerge = Tester.runNTimes(mergeSort, generator, i, 20);
				writeResultsWithoutSwapsToCsv(i, resultMerge, writerShuffledMerge);
				resultMerge = Tester.runNTimes(mergeSort, generatorList, i, 20);
				writeResultsWithoutSwapsToCsv(i, resultMerge, writerShuffledMergeList);
				result = Tester.runNTimes(quicksortRand, generator, i, 20);
				writeResultsToCsv(i, result, writerShuffledQRand);
				result = Tester.runNTimes(quickSortFirst, generator, i, 20);
				writeResultsToCsv(i, result, writerShuffledQFirst);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String double2String(double value) {
		return String.format("%.12f", value);
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

	private static void writeResultsWithoutSwapsToCsv(int size, testing.results.Result result, BufferedWriter writer)
			throws IOException {
		writer.write(size + ";"
				+ double2String(result.averageTimeInMilliseconds()) + ";"
				+ double2String(result.timeStandardDeviation()) + ";" + double2String(result.averageComparisons()) + ";"
				+ double2String(result.comparisonsStandardDeviation()) + "\n");
	}
}
