package autocomplete;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.introcs.StdOut;


public class BinarySearchDeluxe {


    // Return the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator){
    	if(a.length <= 0) return -1;
    	int min = 0;
    	int max = a.length - 1;
    	int middle;
    	while(min <= max){
    		
    		middle = (max - min)/2 + min;
    		if(comparator.compare(key, a[middle]) > 0) min = middle + 1;
    		else if(comparator.compare(key, a[middle]) < 0) max = middle - 1;
    		else if(comparator.compare(key, a[middle]) == 0){
    			if(middle == 0) return middle;
    			else if(comparator.compare(key, a[middle-1]) > 0) return middle;
    			else max = middle - 1;
    		}
    	}
    	
    	return -1;
    }

    // Return the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator){
    	if(a.length <= 0) return -1;
    	int length = a.length - 1;
    	int min = 0;
    	int max = length;
    	int middle= 0;
    	while(min <= max){
    		middle = (max - min)/2 + min;
    		if(comparator.compare(key, a[middle]) > 0) min = middle + 1;
    		else if(comparator.compare(key, a[middle]) < 0) max = middle - 1;
    		else if(comparator.compare(key, a[middle]) == 0){
    			if(middle == length) return middle;
    			else if(comparator.compare(key, a[middle+1]) < 0) return middle;
    			else min = middle + 1;
    		}
    	}
    	
    	return -1;
    }
	
	public static void main(String[] args) {

		// int array with several int equal to 42, 1, and 100. There are 10 1's, therefore the 42's should start at 
		// index 10. There are 7 42's, so they should end at index 16.
		Integer[] numbers = {1,1,42,100,42,100,1,100,1,100,100,100,1,1,100,1,100,42,42,100,42,42,1,1,1,42};
		Comparator<Integer> myNumberSorter = new numberSorter();
		
		Arrays.sort(numbers, myNumberSorter);
		StdOut.println("Sorted Array: " + Arrays.toString(numbers));
		
		int first42 = BinarySearchDeluxe.firstIndexOf(numbers, 42, myNumberSorter);
		int last42 = BinarySearchDeluxe.lastIndexOf(numbers, 42, myNumberSorter);
		
		assert(first42 == 10);
		assert(last42 == 16);
		
		StdOut.println();
		StdOut.println("Searching for value : 42");
		StdOut.println("first index with value 42: " + first42);
		StdOut.println("last index with value 42: " + last42);
		
		// for the 1's, the first index should be 0 and 9 according to my own calculations.
		
		int first1 = BinarySearchDeluxe.firstIndexOf(numbers, 1, myNumberSorter);
		int last1 = BinarySearchDeluxe.lastIndexOf(numbers, 1, myNumberSorter);
		
		assert(first1 == 0);
		assert(last1 == 9);
		
		StdOut.println();
		StdOut.println("Searching for value : 1");
		StdOut.println("first index with value 1: " + first1);
		StdOut.println("last index with value 1: " + last1);
		
		// for the 1's, the first index should be 17 and 25 according to my own calculations.
		
		int first100 = BinarySearchDeluxe.firstIndexOf(numbers, 100, myNumberSorter);
		int last100 = BinarySearchDeluxe.lastIndexOf(numbers, 100, myNumberSorter);
		
		assert(first100 == 17);
		assert(last100 == 25);
		
		StdOut.println();
		StdOut.println("Searching for value : 100");
		StdOut.println("first index with value 100: " + first100);
		StdOut.println("last index with value 100: " + last100);
		
		// Now we will test with a value that is not int the array.
		
		//Because this value is present, we should expect -1 for both index values.
		
		int first500 = BinarySearchDeluxe.firstIndexOf(numbers, 500, myNumberSorter);
		int last500 = BinarySearchDeluxe.lastIndexOf(numbers, 500, myNumberSorter);
		
		assert(first500 == -1);
		assert(last500 == -1);
		
		StdOut.println();
		StdOut.println("Now searching for non-present value: 500");
		StdOut.println();
		StdOut.println("Searching for value : 500");
		StdOut.println("first index with value 500: " + first500);
		StdOut.println("last index with value 500: " + last500);
		
		// Now let's test with an array that is more diverse
		
		StdOut.println();
		StdOut.println("Now using more diverse array: ");
		StdOut.println();
		
		Integer[] numbers2 = {5,2,3,8,4,1,3564,554,545,4,4,564,87,54,21,31,54,21,31,4,
								31,54,64,21,543,131,54,87,21,3,21,3,45,10,54,87,2,4,73,
								82,97,27,25,23,11,14,13,46,73,45,6,78};
		
		Arrays.sort(numbers2, myNumberSorter);
		StdOut.println("Sorted Array: " + Arrays.toString(numbers2));
		
		//value 54 should be at index 32 through 36 (I counted)
		
		int first54 = BinarySearchDeluxe.firstIndexOf(numbers2, 54, myNumberSorter);
		int last54 = BinarySearchDeluxe.lastIndexOf(numbers2, 54, myNumberSorter);
		
		assert(first54 == 32);
		assert(last54 == 36);
		
		StdOut.println();
		StdOut.println("Searching for value : 54");
		StdOut.println("first index with value 54: " + first54);
		StdOut.println("last index with value 54: " + last54);
		
		//value 3 should be at index 3 through 5
		
		int first3 = BinarySearchDeluxe.firstIndexOf(numbers2, 3, myNumberSorter);
		int last3 = BinarySearchDeluxe.lastIndexOf(numbers2, 3, myNumberSorter);
		
		assert(first3 == 3);
		assert(last3 == 5);
		
		StdOut.println();
		StdOut.println("Searching for value : 3");
		StdOut.println("first index with value 3: " + first3);
		StdOut.println("last index with value 3: " + last3);
		
		StdOut.println();
		StdOut.println("Now testing with Term objects: ");
		StdOut.println();
		
		Term[] terms = {new Term("Hello World", 500), new Term("Programming is Fun", 300), new Term("Hello Universe", 200),
						new Term("Pro Sports are cool", 700), new Term("Bad Cheese", 800), new Term("Hellagood!", 500),
						new Term("Am I hungry or what!", 9999)};
		
		Arrays.sort(terms);
		StdOut.println();
		StdOut.println("Array sorted in antural order: " + Arrays.toString(terms));
		
		//Testing indices of words that start with "hell"
		
		Term searchTerm =  new Term("Hello America!", 600);
		int firstHell = BinarySearchDeluxe.firstIndexOf(terms, searchTerm, Term.byPrefixOrder(4));
		int lastHell = BinarySearchDeluxe.lastIndexOf(terms, searchTerm, Term.byPrefixOrder(4));
		
		assert(firstHell == 2);
		assert(lastHell == 4);
		
		StdOut.println();
		StdOut.println("Searching for prefix \"Hell\"");
		StdOut.println("first index: " + firstHell);
		StdOut.println("last index: " + lastHell);
		
		StdOut.println();
		StdOut.println("All tests and assertions complete!");
	}
	
	private static class numberSorter implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	}

}
