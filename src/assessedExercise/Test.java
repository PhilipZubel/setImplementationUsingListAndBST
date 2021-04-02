package assessedExercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class Test {

	// returns an array of integers from the file <fileName>
	private static int[] fileToArray(String fileName) throws FileNotFoundException {
		String URL = System.getProperty("user.dir") + "/numbers/" + fileName;
		File file = new File(URL);

		// from:
		// https://mkyong.com/java/how-to-get-the-total-number-of-lines-of-a-file-in-java/
		int lines = 0;
		try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {
			while (lnr.readLine() != null)
				lines = lnr.getLineNumber();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(file);
		int[] array = new int[lines];
		for (int idx = 0; idx < array.length; idx++) {
			array[idx] = scanner.nextInt();
		}
		scanner.close();

		return array;
	}

	// populates a set with the values in the file <fileName>
	public static void populateSet(Set set, String fileName) throws FileNotFoundException {
		int[] aa = fileToArray(fileName);
		for (int item : aa) {
			set.add(item);
		}
	}

	// returns an array of size length containing random integers from the interval
	// [0, 49999]
	public static int[] generateRandomNumbers(int length) {
		int[] aa = new int[length];
		for (int idx = 0; idx < length; idx++) {
			aa[idx] = (int) (Math.random() * 50000);
		}
		return aa;
	}

	// returns the average time it takes to check if an element is in the set
	public static double getAverageLookUpTime(Set set, int[] items) {
		if (items.length == 0)
			return 0;
		
		double time1 = System.nanoTime();
		for (int item : items) {
			set.isElement(item);
		}
		double time2 = System.nanoTime();
		return (time2-time1) / items.length;
	}
	
//	public static double getInsertionTime(Set set, int[] items) {
//		if (items.length == 0)
//			return 0;
//		double time1 = System.nanoTime();
//		for (int item : items) {
//			set.isElement(item);
//		}
//		double time2 = System.nanoTime();
//		return time2-time1 ;
//	}

}
