package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q3DisassembleNumbers {
	
	private static final int MOD = 10007;
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	private static int N;
	
	public static void main(String[] args) throws IOException {
		init();
		sol();
	}
	
	private static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		
		br.close();
	}
	
	private static void sol() {
		int ans = 0;
		
		if (N < 2) {
			ans = N;
		} else if (N % 3 == 0) {
			ans = mod(pow(3, N / 3));
		} else if (N % 3 == 1) {
			ans = mod(pow(3, (N / 3) - 1) * 4);
		} else {
			ans = mod(pow(3, N / 3) * 2);
		}
		
		System.out.println(ans);
	}
	
	private static int pow(int a, int b) {
		int res = 1;

		for (int i = 1; i <= b; i++) {
			res = mod(res * a);
		}
		
		return res;
	}
	
	private static int mod(int a) {
		return a % MOD;
	}
}
