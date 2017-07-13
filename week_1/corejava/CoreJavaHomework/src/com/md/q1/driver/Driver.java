package com.md.q1.driver;

import java.util.Arrays;

public class Driver {
	
	public static void main(String[] args) {

		int[] array = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 }; // array to sort

		bubbleSort(array); // Q1 call bubbleSort with array

	}

	public static void bubbleSort(int[] array) {

		int index = 0;
		int pass = 0;

		while (pass < array.length - 1) {

			while (index < array.length - 1) {

				if (array[index] > array[index + 1]) {
					System.out.println("swap " + array[index] + " by " + array[index + 1]);
					int tmp = array[index];
					array[index] = array[index + 1];
					array[index + 1] = tmp;
				}

				index++;

			}

			pass++;
			index = array.length - pass;
		}

		System.out.println("Sorted array\n" + Arrays.toString(array));

	}	
	
}
