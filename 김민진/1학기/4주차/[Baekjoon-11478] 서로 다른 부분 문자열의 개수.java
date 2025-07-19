package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Q5NumberOfUniqueSubStrings {
	
	private static int		len;
	private static String	input;

	private static HashSet<String>	set;
	
	public static void main(String[] args) throws IOException {
		init();
		sol();
	}
	
	private static void init() throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine().trim();
		len = input.length();
		
		set = new HashSet<>();
	}
	
	private static void sol() {
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				set.add(input.substring(i, j+1));
			}
		}
		System.out.println(set.size());
	}
}
