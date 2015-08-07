package autocomplete;

import java.util.Arrays;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;


public class Autocomplete {

	private final Term[] terms;
	
	 // Initialize the data structure from the given array of terms.
	/**
	* Initizialize the data structure from the given array of terms.
	* Sorts terms lexographically.
	* @param terms
	*/
	public Autocomplete(Term[] terms){
		
		Term[] temp = new Term[terms.length];
		
		//Defensive copy to make it immutable
		for(int i = 0; i< terms.length ; i++){
			temp[i] = new Term(terms[i]);
		}
		
		this.terms = temp;
		Quick.sort(temp);
	}


	         // Return all terms that start with the given prefix, in descending order of weight.
	 public Term[] allMatches(String prefix){
	        
        int first = BinarySearchDeluxe.firstIndexOf(terms, new Term(prefix,0), Term.byPrefixOrder(prefix.length()));
        int last = BinarySearchDeluxe.lastIndexOf(terms, new Term(prefix,0), Term.byPrefixOrder(prefix.length()));
        
        int size = last-first + 1;
        Term[] temp = new Term[size];
        
       
        if(first != -1 && last != -1){
        	for(int i = 0; i < size; i++){
            temp[i] = new Term(terms[first + i]);    
        	}
        }
        
        if(first < 0) temp[0] = new Term("No results found",0);
        
       Arrays.sort(temp, Term.byReverseWeightOrder());
        
        return temp;
	        
	}
	
	public int numberOfMatches(String prefix){
		Term searchTerm = new Term(prefix, 0);
		int first = BinarySearchDeluxe.firstIndexOf(terms, searchTerm, Term.byPrefixOrder(prefix.length()));
		int last = BinarySearchDeluxe.lastIndexOf(terms, searchTerm, Term.byPrefixOrder(prefix.length()));
		return last - first + 1;
	}

	
	public static void main(String[] args) {

	    // read in the terms from a file
	    String filename = args[0];
	    In in = new In(filename);
	    int N = in.readInt();
	    Term[] terms = new Term[N];
	    for (int i = 0; i < N; i++) {
	        double weight = in.readDouble();       // read the next weight
	        in.readChar();                         // scan past the tab
	        String query = in.readLine();          // read the next query
	        terms[i] = new Term(query, weight);    // construct the term
	    }
		
//		Term[] terms = new Term[3];
//		terms[0] = new Term("a",500);
//		terms[1] = new Term("aa",500);
//		terms[2] = new Term("aaa",500);
//		int k = 1;
	    // read in queries from standard input and print out the top k matching terms
	   
	    int k = Integer.parseInt(args[1]);
	    Autocomplete autocomplete = new Autocomplete(terms);
	    while (StdIn.hasNextLine()) {
	        String prefix = StdIn.readLine();
	        Term[] results = autocomplete.allMatches(prefix);
	        for (int i = 0; i < Math.min(k, results.length); i++)
	            StdOut.println("results"+results[i]);
	    }
	}

	
}
