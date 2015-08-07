package autocomplete;

import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		String s1 = "aa";
		String s2 = "b";
		System.out.println(s1.compareTo(s2));
		
		String[] sa = {"a","bb","c","aaa","Shanghai","S"};
		Arrays.sort(sa);
		System.out.println(Arrays.toString(sa));

	}

}
