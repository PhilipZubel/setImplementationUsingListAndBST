package assessedExercise;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		// below you will find operations for a doubly linked list set
		
//		Set ls = new LinkedSet(); // declare a dll set
//		ls.add(5); // add the value 5 to the set
//		ls.remove(7); // remove the value 7 from the set
//		boolean isInSet = ls.isElement(5); // checks if the value 5 is in the set
//		int size = ls.setSize(); // get size of the set
//		boolean isEmpty = ls.setEmpty(); // checks if there are no elements in the set
//		
//		LinkedSet ls1 = new LinkedSet();
//		LinkedSet ls2 = new LinkedSet();
//
//		// ls1 and ls2 are doubly linked list sets
//		LinkedSet result; // a set which stores the result of the operations
//		result = ls1.UNION(ls2); // union of the two sets
//		result = ls1.INTERSECTION(ls2); // intersection of the two sets
//		result = ls1.DIFFERENCE(ls2); // difference of the two sets
//		boolean isSubset = ls1.SUBSET(ls2); // checks if ls1 is a subset of ls2


		// below you will find operations for a binary search tree set

//		Set bst = new BSTSet(); // declare a binary search tree set
//		bst.add(5); // add the value 5 to the set
//		bst.remove(7); // remove the value 7 from the set
//		boolean isInSet = bst.isElement(5); // checks if the value 5 is in the set
//		int size = bst.setSize(); // get size of the set
//		boolean isEmpty = bst.setEmpty(); // checks if there are no elements in the set
//
//		BSTSet bst1 = new BSTSet();
//		BSTSet bst2 = new BSTSet();
//
//		// bst1 and bst2 are binary search tree sets
//		BSTSet result; // a set which stores the result of the operations
//		result = bst1.UNION(bst2); // union of the two sets
//		result = bst1.INTERSECTION(bst2); // intersection of the two sets
//		result = bst1.DIFFERENCE(bst2); // difference of the two sets
//		boolean isSubset = bst1.SUBSET(bst2); // checks if bst1 is a subset of bst2
//
//		// get height of the tree called bst
//		int height = bst1.getHeight();
//		// print the inorder traversal of the tree
//		bst1.print();
		
		
		// get union linear time
		
//		BSTSet s1 = new BSTSet();
//		BSTSet s2 = new BSTSet();
//		BSTSet result = s1.UNION2(s2);
		
		// measure isElement performance of the binary search tree and the doubly linked list

//		Set dll = new LinkedSet(); // declare doubly linked list set
//		Set bst = new BSTSet(); // declare binary search tree set
//		// populate the sets with a file located in the numbers directory 
//		Test.populateSet(dll, "int20k.txt");
//		Test.populateSet(bst, "int20k.txt");
//		// generate an array with a 100 numbers from range 0 - 49999
//		int[] numbers = Test.generateRandomNumbers(1000);
//		// get average insertion times for both sets
//		double dll_time = Test.getAverageLookUpTime(dll, numbers);
//		double bst_time = Test.getAverageLookUpTime(bst, numbers);
//				
//		System.out.println("DLL look up time: "+dll_time);
//		System.out.println("BST look up time: "+bst_time);

		
		// get size of the set
		
//		Set s = new LinkedSet(); // or new BSTSet()
//		Test.populateSet(s, "int20k.txt");
//		int size = s.setSize();


        // get height of the tree
		
//		BSTSet bst = new BSTSet();
//		Test.populateSet(bst, "int20k.txt");
//		int height = bst.getHeight();
		
	
	}

}
