package week3;

import java.io.*;
import java.util.*;

public class Q3CheckPoint {
	
	private static StringBuilder	sb;
	private static TreeSet<Integer>	tree;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		sol();
	}
	
	private static void sol() throws NumberFormatException, IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		
		int		N = Integer.parseInt(br.readLine());
		int[]	input = new int[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(input);
		
		int	gap = input[1] - input[0];
		for (int i = 2; i < N; i++) {
			gap = gcd(gap, input[i] - input[i-1]);
		}
		
		getFactors(gap);
		printTree();
	}
	
	private static int gcd(int a, int b) {
		if (b == 0) return a;
		
		return gcd(b, a % b);
	}
	
	// 중복 방지 + 정렬
	private static void getFactors(int n) {
		tree = new TreeSet<>();
		
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				tree.add(i);
				tree.add(n / i);
			}
		}
		tree.add(n);
	}
	
	private static void printTree() {
		sb = new StringBuilder();

		for (Integer i : tree) {
			sb.append(i).append(" ");
		}
		
		System.out.println(sb);
	}

}
