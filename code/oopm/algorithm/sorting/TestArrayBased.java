package algorithm.sorting;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import static algorithm.sorting.ArrayBased.*;

public class TestArrayBased {

	// Test data
	int[] input0;
	int[] output0;
	int[] input1;
	int[] output1;
	int[] input2a;
	int[] input2b;
	int[] output2;
	int[] input3a;
	int[] input3b;
	int[] input3c; 
	int[] input3d;
	int[] input3e;
	int[] input3f;
	int[] output3;
	
	@Before
	public void setUp() throws Exception {
		input0 = new int[0];
		output0 = new int[0];
		input1 = new int[] { 42 }; // array of length 1
		output1 = new int[] { 42 }; // array of length 1
		input2a = new int[] { 1, 2 }; // sorted array of length 2
		input2b = new int[] { 2, 1 }; // unsorted array of length 2
		output2 = new int[] { 1, 2 }; // sorted array of length 2
		input3a = new int[] { 1, 2, 3 }; // sorted array of length 3
		input3b = new int[] { 2, 3, 1 }; // unsorted array of length 3
		input3c = new int[] { 3, 1, 2 }; // unsorted array of length 3
		input3d = new int[] { 1, 3, 2 }; // unsorted array of length 3
		input3e = new int[] { 3, 2, 1 }; // unsorted array of length 3
		input3f = new int[] { 2, 1, 3 }; // unsorted array of length 3
		output3 = new int[] { 1, 2, 3 }; // sorted array of length 3
	}

	@Test
	public void testIsSorted() {
		// Sorted input
		assertTrue(isSorted(input0));
		assertTrue(isSorted(input1));
		assertTrue(isSorted(input2a));
		assertTrue(isSorted(input3a));
		
		// Sorted output
		assertTrue(isSorted(output0));
		assertTrue(isSorted(output1));
		assertTrue(isSorted(output2));
		assertTrue(isSorted(output3));
		
		// Unsorted input
		assertFalse(isSorted(input2b));
		assertFalse(isSorted(input3b));
		assertFalse(isSorted(input3c));
		assertFalse(isSorted(input3d));
		assertFalse(isSorted(input3e));
		assertFalse(isSorted(input3f));
	}
	
	@Test 
	public void testIsPermutation() {
		assertTrue(isPermutation3(input0,output0));
		assertTrue(isPermutation3(input1,output1));
		assertTrue(isPermutation3(input2a,output2));
		assertTrue(isPermutation3(input2b,output2));
		assertTrue(isPermutation3(input3a,output3));
		assertTrue(isPermutation3(input3b,output3));
		assertTrue(isPermutation3(input3c,output3));
		assertTrue(isPermutation3(input3d,output3));
		assertTrue(isPermutation3(input3e,output3));
		assertTrue(isPermutation3(input3f,output3));
	}
	
	@Test
	public void testBubbleSort() {
		bubbleSort2(input0);
		assertTrue(Arrays.equals(input0,output0));	
		bubbleSort2(input1);
		assertTrue(Arrays.equals(input1,output1));	
		bubbleSort2(input2a);
		assertTrue(Arrays.equals(output2,input2a));	
		bubbleSort2(input2b);
		assertTrue(Arrays.equals(output2,input2b));	
		bubbleSort2(input3a);
		assertTrue(Arrays.equals(output3,input3a));	
		bubbleSort2(input3b);
		assertTrue(Arrays.equals(output3,input3b));	
		bubbleSort2(input3c);
		assertTrue(Arrays.equals(output3,input3c));	
		bubbleSort2(input3d);
		assertTrue(Arrays.equals(output3,input3d));	
		bubbleSort2(input3e);
		assertTrue(Arrays.equals(output3,input3e));	
		bubbleSort2(input3f);
		assertTrue(Arrays.equals(output3,input3f));	
	}

	@Test
	public void testSelectionSort() {
		selectionSort(input0);
		assertTrue(Arrays.equals(input0,output0));	
		selectionSort(input1);
		assertTrue(Arrays.equals(input1,output1));	
		selectionSort(input2a);
		assertTrue(Arrays.equals(output2,input2a));	
		selectionSort(input2b);
		assertTrue(Arrays.equals(output2,input2b));	
		selectionSort(input3a);
		assertTrue(Arrays.equals(output3,input3a));	
		selectionSort(input3b);
		assertTrue(Arrays.equals(output3,input3b));	
		selectionSort(input3c);
		assertTrue(Arrays.equals(output3,input3c));	
		selectionSort(input3d);
		assertTrue(Arrays.equals(output3,input3d));	
		selectionSort(input3e);
		assertTrue(Arrays.equals(output3,input3e));	
		selectionSort(input3f);
		assertTrue(Arrays.equals(output3,input3f));	
	}
	
	@Test
	public void testInsertionSort() {
		insertionSort(input0);
		assertTrue(Arrays.equals(input0,output0));	
		insertionSort(input1);
		assertTrue(Arrays.equals(input1,output1));	
		insertionSort(input2a);
		assertTrue(Arrays.equals(output2,input2a));	
		insertionSort(input2b);
		assertTrue(Arrays.equals(output2,input2b));	
		insertionSort(input3a);
		assertTrue(Arrays.equals(output3,input3a));	
		insertionSort(input3b);
		assertTrue(Arrays.equals(output3,input3b));	
		insertionSort(input3c);
		assertTrue(Arrays.equals(output3,input3c));	
		insertionSort(input3d);
		assertTrue(Arrays.equals(output3,input3d));	
		insertionSort(input3e);
		assertTrue(Arrays.equals(output3,input3e));	
		insertionSort(input3f);
		assertTrue(Arrays.equals(output3,input3f));	
	}	
	
	@Test
	public void testMergeSort() {
		mergeSort(input0);
		assertTrue(Arrays.equals(input0,output0));	
		mergeSort(input1);
		assertTrue(Arrays.equals(input1,output1));	
		mergeSort(input2a);
		assertTrue(Arrays.equals(output2,input2a));	
		mergeSort(input2b);
		assertTrue(Arrays.equals(output2,input2b));	
		mergeSort(input3a);
		assertTrue(Arrays.equals(output3,input3a));	
		mergeSort(input3b);
		assertTrue(Arrays.equals(output3,input3b));	
		mergeSort(input3c);
		assertTrue(Arrays.equals(output3,input3c));	
		mergeSort(input3d);
		assertTrue(Arrays.equals(output3,input3d));	
		mergeSort(input3e);
		assertTrue(Arrays.equals(output3,input3e));	
		mergeSort(input3f);
		assertTrue(Arrays.equals(output3,input3f));	
	}	
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testBubbleSortWithNull() {
		bubbleSort2(null);
	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testSelectionSortWithNull() {
		selectionSort(null);
	}	
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testInsertionSortWithNull() {
		insertionSort(null);
	}
	
	@Test(expected=java.lang.NullPointerException.class)
	public void testMergeSortWithNull() {
		mergeSort(null);
	}		
}
