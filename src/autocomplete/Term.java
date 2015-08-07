package autocomplete;

import java.util.Comparator;

public class Term implements Comparable<Term> {
	
	private String query;
	private double weight = 0;
	
	public Term(String query, double weight){
		if(query == null)
			throw new NullPointerException("Query null");
		if(weight < 0)
			throw new IllegalArgumentException("Negative weight");
		
		this.query = query;
		this.weight = weight;
	}
	
	public Term(Term t){
		if(t.query == null)
			throw new NullPointerException("Query null");
		if(t.weight < 0)
			throw new IllegalArgumentException("Negative weight");
		
		this.query = t.query;
		this.weight = t.weight;
	}
	
	public static Comparator<Term> byPrefixOrder(int r){
		if(r < 0)
			throw new IllegalArgumentException("r is negative");
		ByPrefixOrder.r = r;
		return new ByPrefixOrder();
		
	}
	
	public static Comparator<Term> byReverseWeightOrder(){
		return new ByReverseWeightOrder();
		
	}
	
	@Override
	public int compareTo(Term o) {
		
		return this.query.compareToIgnoreCase(o.query);
	}
	
	public static class ByReverseWeightOrder implements Comparator<Term>{
		
		@Override
		public int compare(Term t1, Term t2) {
			double result = t1.weight - t2.weight;
			if(result < 0)
				return 1;
			if(result > 0)
				return -1;
			return 0;
		}	
	}
	
	public static class ByPrefixOrder implements Comparator<Term>{
		
		private static int r;
		
		@Override
		public int compare(Term t1 , Term t2) {
			if(t1.query.length() > t2.query.length()) {
				int length = t2.query.length();
				return t1.query.substring(0,r).compareToIgnoreCase(t2.query.substring(0,length)); 
			}
			return t1.query.substring(0,r).compareToIgnoreCase(t2.query.substring(0,r));
		}
		
	}
	
	public String toString(){
		return this.weight + "	" + this.query;
	}
	
}	
