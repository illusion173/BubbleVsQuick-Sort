package org.sample;

/*
 *
 * Student Name: Jeremiah Webb
 * Date: 11/2/22
 * Class: CS315
 * Basic Sorting Algorithn: Bubble Sort
 * Advanced Sorting Algorithn: Merge Sort
 * See README.md for instructions on how to run this file.

 * Link for Reference of how to structure JMH Properly
 * https://github.com/ksean/jmh-data-structure-and-sorting-benchmarks/blob/master/src/main/java/com/ks/sort/SortingAlgorithmBenchmark.java
 */
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.util.Arrays;

// Class to implement bubble sort Algorithm
class Bubble_Sort_Class {
	public int[] bubble_sort(int[] array) throws InterruptedException {
		int n = array.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (array[j] > array[j + 1]) {
					// swap arr[j+1] and arr[j]
					int temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}

		return array;
	}
}

// Class to implement quick sort Algorithm
class Quick_Sort_Class {

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/*
	 * This function takes last element as pivot, places
	 * the pivot element at its correct position in sorted
	 * array, and places all smaller (smaller than pivot)
	 * to left of pivot and all greater elements to right
	 * of pivot
	 */
	static int partition(int[] arr, int low, int high) {

		// pivot
		int pivot = arr[high];

		// Index of smaller element and
		// indicates the right position
		// of pivot found so far
		int i = (low - 1);

		for (int j = low; j <= high - 1; j++) {

			// If current element is smaller
			// than the pivot
			if (arr[j] < pivot) {

				// Increment index of
				// smaller element
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}

	/*
	 * The main function that implements QuickSort
	 * arr[] --> Array to be sorted,
	 * low --> Starting index,
	 * high --> Ending index
	 */
	static void quickSort(int[] arr, int low, int high) {
		if (low < high) {

			// pi is partitioning index, arr[p]
			// is now at right place
			int pi = partition(arr, low, high);

			// Separately sort elements before
			// partition and after partition
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}

}

@State(Scope.Thread)
public class MyBenchmark {
	private final int LIST_SIZE = 1000;
	private int[] custom_arr;
	private int[] sorted_array;
	private int[] reverse_of_sorted_array;

	private int[] custom_arr_2;
	private int[] sorted_array_2;
	private int[] reverse_of_sorted_array_2;

	@Setup(Level.Iteration)
	public void setup() {
		custom_arr = create_random_custom_array(LIST_SIZE);
		sorted_array = create_ordered_array(LIST_SIZE);
		reverse_of_sorted_array = create_ordered_array_reverse(LIST_SIZE);
		custom_arr_2 = custom_arr.clone();
		sorted_array_2 = sorted_array.clone();
		reverse_of_sorted_array_2 = reverse_of_sorted_array.clone();
	}

	public static int[] create_ordered_array(int size) {
		int[] num_array = new int[size];
		for (int i = 0; i < size - 1; i++) {
			num_array[i] = i;
		}
		return num_array;
	}

	public static int[] create_ordered_array_reverse(int size) {
		int[] num_array = new int[size];
		for (int i = size; i > 0; i--) {
			num_array[Math.abs(i - size)] = i;
		}
		return num_array;
	}

	public static int[] create_random_custom_array(int size) {
		int[] new_array = new int[size];

		// Provided by java.util.Random, allows nextInt random num generator
		Random rand = new Random();
		for (int i = 0; i < size; i++) {

			new_array[i] = rand.nextInt(1500);
		}
		return new_array;
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@Fork(value = 1)
	@Warmup(iterations = 3)
	@Measurement(iterations = 5)
	public int[] bubble_sort_random_sorted_array() throws InterruptedException {
		return new Bubble_Sort_Class().bubble_sort(sorted_array);
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@Fork(value = 1)
	@Warmup(iterations = 3)
	@Measurement(iterations = 5)
	public int[] bubble_sort_random_sorted_reversed_array() throws InterruptedException {
		return new Bubble_Sort_Class().bubble_sort(reverse_of_sorted_array);
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@Fork(value = 1)
	@Warmup(iterations = 3)
	@Measurement(iterations = 5)
	public int[] bubble_sort_random_custom_array() throws InterruptedException {
		return new Bubble_Sort_Class().bubble_sort(custom_arr);
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@Fork(value = 1)
	@Warmup(iterations = 3)
	@Measurement(iterations = 5)
	public void quick_sort_random_sorted_array() throws InterruptedException {
		Quick_Sort_Class.quickSort(sorted_array_2, 0, sorted_array_2.length - 1);
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@Fork(value = 1)
	@Warmup(iterations = 3)
	@Measurement(iterations = 5)
	public void quick_sort_random_sorted_reversed_array() throws InterruptedException {

		Quick_Sort_Class.quickSort(reverse_of_sorted_array_2, 0, reverse_of_sorted_array_2.length - 1);
	}

	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@Fork(value = 1)
	@Warmup(iterations = 3)
	@Measurement(iterations = 5)
	public void quick_sort_random_custom_array() throws InterruptedException {

		Quick_Sort_Class.quickSort(custom_arr_2, 0, custom_arr_2.length - 1);
	}

	public static void main(String Args[]) throws RunnerException {
	}
}
